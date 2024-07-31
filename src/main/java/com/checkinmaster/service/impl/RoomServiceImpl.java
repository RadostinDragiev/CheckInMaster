package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Reservation;
import com.checkinmaster.model.entity.Room;
import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.dto.FindRoomDto;
import com.checkinmaster.model.entity.enums.RoomType;
import com.checkinmaster.model.entity.view.CreateRoomView;
import com.checkinmaster.model.entity.view.DetailsRoomView;
import com.checkinmaster.model.entity.view.ReservationRoomView;
import com.checkinmaster.repository.RoomRepository;
import com.checkinmaster.service.ImageService;
import com.checkinmaster.service.RoomService;
import com.checkinmaster.util.CloudinaryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    @Override
    @Transactional
    public CreateRoomView createRoom(CreateRoomDto createRoomDto, List<MultipartFile> multipartFiles) throws IOException {
        Room room = this.modelMapper.map(createRoomDto, Room.class);
        Room newlyCreatedRoom = this.roomRepository.saveAndFlush(room);

        if (!multipartFiles.isEmpty()) {
            this.cloudinaryService.uploadFiles(multipartFiles, newlyCreatedRoom);
        }

        return this.modelMapper.map(newlyCreatedRoom, CreateRoomView.class);
    }

    @Override
    public DetailsRoomView getRoomViewById(UUID uuid) {
        return this.modelMapper.map(this.roomRepository.findById(uuid), DetailsRoomView.class);
    }

    @Override
    public Room getRoomById(UUID uuid) {
        return this.roomRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CreateRoomView> getAll() {
        List<Room> rooms = this.roomRepository.findAll(Sort.by("uuid").descending());
        return Arrays.asList(this.modelMapper.map(rooms, CreateRoomView[].class));
    }

    @Override
    @Transactional
    public void deleteRoomById(UUID uuid) throws Exception {
        this.cloudinaryService.deleteFiles(imageService.getImagePublicIds(uuid));
        this.roomRepository.deleteById(uuid);
    }

    @Override
    public List<ReservationRoomView> findAllAvailableRooms(FindRoomDto findRoomDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> roomRoot = criteriaQuery.from(Room.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!findRoomDto.getRoomType().isEmpty()) {
            RoomType roomType = RoomType.valueOf(findRoomDto.getRoomType());
            predicates.add(criteriaBuilder.equal(roomRoot.get("roomType"), roomType));
        }

        int capacity = findRoomDto.getCapacity();
        if (capacity != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(roomRoot.get("capacity"), capacity));
        }

        BigDecimal minPricePerNight = findRoomDto.getMinPricePerNight();
        if (minPricePerNight != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(roomRoot.get("pricePerNight"), minPricePerNight));
        }

        BigDecimal maxPricePerNight = findRoomDto.getMaxPricePerNight();
        if (maxPricePerNight != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(roomRoot.get("pricePerNight"), maxPricePerNight));
        }

        LocalDate checkInDate = findRoomDto.getCheckInDate();
        LocalDate checkOutDate = findRoomDto.getCheckOutDate();
        if (checkInDate != null && checkOutDate != null) {
            Subquery<UUID> subquery = criteriaQuery.subquery(UUID.class);
            Root<Room> subRoom = subquery.from(Room.class);
            Join<Room, Reservation> reservation = subRoom.join("reservations", JoinType.LEFT);

            Predicate overlappingReservations = criteriaBuilder.and(
                    criteriaBuilder.greaterThan(reservation.get("checkOutDate"), checkInDate),
                    criteriaBuilder.lessThan(reservation.get("checkInDate"), checkOutDate)
            );

            subquery.select(subRoom.get("uuid")).where(overlappingReservations);

            Predicate noOverlappingReservations = criteriaBuilder.not(roomRoot.get("uuid").in(subquery));
            predicates.add(noOverlappingReservations);
        }

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        List<Room> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return Arrays.asList(this.modelMapper.map(resultList, ReservationRoomView[].class));
    }
}
