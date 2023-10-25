package com.example.demo.utility;

import com.example.demo.model.Hall;
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

    private HallDTO createHallDTO(Hall hall) {
        return HallDTO.builder()
                .id(hall.getId())
                .name(hall.getName())
                .price(hall.getPrice())
                .build();
    }
}
