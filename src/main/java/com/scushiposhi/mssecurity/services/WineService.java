package com.scushiposhi.mssecurity.services;

import com.scushiposhi.mssecurity.entities.Wine;
import com.scushiposhi.mssecurity.repositories.WineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WineService {
    private final WineRepository wineRepository;

    public ResponseEntity<Wine> getWineById(Long wineId){
        Optional<Wine> byId = wineRepository.findById(wineId);
        return new ResponseEntity<>(byId.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Wine>> getWines() {
        List<Wine> all = wineRepository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
