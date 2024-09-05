package com.sbp.userservice.userservice.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sbp.userservice.userservice.entity.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable String hotelId);

}
