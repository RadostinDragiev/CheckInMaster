package com.checkinmaster.repository;

import com.checkinmaster.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    List<Image> findAllByRoom_Uuid(UUID roomUUID);
}
