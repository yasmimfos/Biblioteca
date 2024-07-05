package com.example.books.controller;

import com.example.books.dtos.PublishingDto;
import com.example.books.services.PublishingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.books.models.Publishing;

import java.util.List;

@RestController
public class PublishingController {

    private final PublishingService publishingService;

    @Autowired
    public PublishingController(PublishingService publishingService) {
        this.publishingService = publishingService;
    }


    @PostMapping("/publishing")
    public ResponseEntity<Object> addPublishing(@RequestBody @Valid PublishingDto publishingDto){
        var creation = publishingService.register(publishingDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creation);

    }

    @GetMapping("/publishing")
    public ResponseEntity<List<Publishing>> getAllPublishing(){
        var allRegisters = publishingService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allRegisters);
    }

    @GetMapping("/publishing/{id}")
    public ResponseEntity<Object> GetOnePublishing(@PathVariable(value =  "id") Long id){
        var company = publishingService.getOne(id);

        if(company.equals("Company not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(company);
        }
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @PutMapping("/publishing/{id}")
    public ResponseEntity<Object> updatePublishing(@PathVariable(value = "id") Long id, @RequestBody @Valid PublishingDto publishingDto){
        var company = publishingService.update(id, publishingDto);

        if(company.equals("Company not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(company);
        }
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @DeleteMapping("/publishing/{id}")
    public ResponseEntity<Object> deletePublish(@PathVariable(value = "id") Long id){
        var company = publishingService.delete(id);

        if(company){
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");

    }
}
