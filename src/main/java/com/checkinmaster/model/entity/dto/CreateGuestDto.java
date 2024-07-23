package com.checkinmaster.model.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGuestDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
}
