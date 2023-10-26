package com.example.demo.utility;

import com.example.demo.model.Hall;
import com.example.demo.model.dto.AddHallDTO;
import com.example.demo.model.dto.HallDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HallUtility {
    public List<HallDTO> createHallDTOList(List<Hall> hallList) {
       return hallList.stream()
                .map(this::createHallDTO)
                .toList();
    }

    public Hall createHallFromAddHallDTO(AddHallDTO addHallDTO) {
        return Hall.builder()
                .name(addHallDTO.getName())
                .seats(addHallDTO.getSeats())
                .quality(addHallDTO.getQuality())
                .price(addHallDTO.getPrice())
                .isActive(true).build();
    }

    private HallDTO createHallDTO(Hall hall) {
        return HallDTO.builder()
                .id(hall.getId())
                .name(hall.getName())
                .price(hall.getPrice())
                .build();
    }
}
