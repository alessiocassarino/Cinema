package com.example.demo.service;

import com.example.demo.exception.HallAlreadyExistException;
import com.example.demo.model.Hall;
import com.example.demo.model.dto.AddHallDTO;
import com.example.demo.model.dto.HallDTO;
import com.example.demo.repository.HallRepository;
import com.example.demo.utility.HallUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private HallUtility hallUtility;
    @Autowired
    private ValidationUtility validationUtility;

    public ResponseEntity<List<HallDTO>> getAll() {
        List<Hall> hallList = hallRepository.findAllByIsActiveTrue();
        List<HallDTO> hallDTOList = hallUtility.createHallDTOList(hallList);
        return ResponseEntity.status(HttpStatus.OK).body(hallDTOList);
    }

    public ResponseEntity<String> addHall(AddHallDTO addHallDTO) {
        validationUtility.validateAddHallDTO(addHallDTO);
        Long countHallByName = hallRepository.countByName(addHallDTO.getName());
        if (countHallByName > 0) {
            throw new HallAlreadyExistException("La sala : " + addHallDTO.getName() + " è già presente");
        }

        Hall hall = hallUtility.createHallFromAddHallDTO(addHallDTO);
        hallRepository.save(hall);
        return ResponseEntity.status(HttpStatus.OK).body("Sala aggiunta con successo");
    }
}
