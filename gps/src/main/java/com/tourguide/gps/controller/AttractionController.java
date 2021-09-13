package com.tourguide.gps.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.gps.controller.dto.AttractionDto;
import com.tourguide.gps.service.AttractionService;

@RestController
public class AttractionController {

	@Autowired
	private AttractionService attractionService;

	@RequestMapping("/getAttractions")
	public List<AttractionDto> getAttractions() {
		List<AttractionDto> AttractionDTOList =
				attractionService.getAttractions().stream()
						.map(attraction -> AttractionDto.convertToDto(attraction))
						.collect(Collectors.toList());

		return AttractionDTOList;
	}

}
