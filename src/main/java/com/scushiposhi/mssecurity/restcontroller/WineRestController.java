package com.scushiposhi.mssecurity.restcontroller;

import com.scushiposhi.mssecurity.entities.Wine;
import com.scushiposhi.mssecurity.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WineRestController {
    @Autowired
    private  WineService wineService;
    @GetMapping(value = "/wines/{wineId}", produces = "application/json")
    public ResponseEntity<Wine> getWineById(@PathVariable(name = "wineId") Long wineId){
        return wineService.getWineById(wineId);
    }
    @GetMapping(value = "/wines")
    public ResponseEntity<List<Wine>> getWines(){
        return wineService.getWines();
    }

}
