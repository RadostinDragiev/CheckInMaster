package com.checkinmaster.service;

import com.checkinmaster.model.entity.Guest;
import com.checkinmaster.model.entity.dto.CreateGuestDto;

public interface GuestService {

    Guest registerGuest(CreateGuestDto createGuestDto);

    Guest findGuest(CreateGuestDto createGuestDto);
}
