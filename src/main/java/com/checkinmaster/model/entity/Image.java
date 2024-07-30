package com.checkinmaster.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {

    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Column(name = "public_id", nullable = false)
    private String publicId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Room room;
}
