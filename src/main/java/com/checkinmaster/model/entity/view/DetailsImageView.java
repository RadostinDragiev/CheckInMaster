package com.checkinmaster.model.entity.view;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsImageView {

    private UUID uuid;
    private String publicId;
    private String url;
    private LocalDateTime createdDateTime;
}
