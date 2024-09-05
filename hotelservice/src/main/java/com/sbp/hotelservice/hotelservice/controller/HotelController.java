package com.sbp.hotelservice.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbp.hotelservice.hotelservice.entity.Hotel;
import com.sbp.hotelservice.hotelservice.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> create(@RequestBody Hotel hotel){
		Hotel hotel1 = hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
		Hotel hotel1 = hotelService.getHotelById(hotelId);
		return ResponseEntity.ok(hotel1);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> hotels = hotelService.getAllHotels();
		return ResponseEntity.ok(hotels);
	}
	

}
