package com.sbp.hotelservice.hotelservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbp.hotelservice.hotelservice.entity.Hotel;
import com.sbp.hotelservice.hotelservice.exceptions.ResourceNotFoundException;
import com.sbp.hotelservice.hotelservice.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel createHotel(Hotel hotel) {
		hotel.setId(UUID.randomUUID().toString());
		return hotelRepository.save(hotel);
	}
	
	public Hotel getHotelById(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with id "+ id + " not found!"));
	}
	
	public List<Hotel> getAllHotels(){
		return hotelRepository.findAll();
	}
}
