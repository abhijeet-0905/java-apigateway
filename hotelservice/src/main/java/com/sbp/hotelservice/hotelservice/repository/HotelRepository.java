package com.sbp.hotelservice.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbp.hotelservice.hotelservice.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
