package com.example.demo.service;

import com.example.demo.model.Hall;
import com.example.demo.model.dto.HallDTO;
import com.example.demo.repository.HallRepository;
import com.example.demo.utility.HallUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private  HallRepository hallRepository;

    @Autowired
    private HallUtility hallUtility;

    public ResponseEntity<List<HallDTO>> getAll() {

        List<Hall> hallList = hallRepository.findAllByIsActiveTrue();

        List<HallDTO> hallDTOList = hallUtility.createHallDTOList(hallList);
        return ResponseEntity.status(HttpStatus.OK).body(hallDTOList);
    }
}
