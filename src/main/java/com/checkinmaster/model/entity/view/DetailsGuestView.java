package com.checkinmaster.model.entity.view;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsGuestView {

    private UUID uuid;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
}
