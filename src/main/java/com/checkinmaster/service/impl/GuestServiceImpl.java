package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Guest;
import com.checkinmaster.model.entity.dto.CreateGuestDto;
import com.checkinmaster.repository.GuestRepository;
import com.checkinmaster.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    @Override
    public Guest registerGuest(CreateGuestDto createGuestDto) {
        Guest guest = this.modelMapper.map(createGuestDto, Guest.class);
        return this.guestRepository.saveAndFlush(guest);
    }

    @Override
    public Guest findGuest(CreateGuestDto createGuestDto) {
        return this.guestRepository.findByEmail(createGuestDto.getEmail())
                .orElseGet(() -> registerGuest(createGuestDto));
    }
}
