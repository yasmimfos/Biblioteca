package com.example.books.controller;

import com.example.books.dtos.PublishingDto;
import com.example.books.repositories.PublishingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.books.models.Publishing;

import java.util.List;
import java.util.Optional;

@RestController
public class PublishingController {

    @Autowired
    PublishingRepository publishingRepository;


    @PostMapping("/publishing")
    public ResponseEntity<Object> addPublishing(@RequestBody @Valid PublishingDto publishingDto){
        var verification = publishingRepository.findByCompany(publishingDto.company());
        if(verification==null){
            var publishing = new Publishing();
            BeanUtils.copyProperties(publishingDto, publishing);
            return ResponseEntity.status(HttpStatus.CREATED).body(publishingRepository.save(publishing));
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The company has already being on the system");
        }
    }

    @GetMapping("/publishing")
    public ResponseEntity<List<Publishing>> getAllPublishing(){
        return ResponseEntity.status(HttpStatus.OK).body(publishingRepository.findAll());
    }

    @GetMapping("/publishing/{id}")
    public ResponseEntity<Object> GetOnePublishing(@PathVariable(value =  "id") Long id){
        Optional<Publishing> company = publishingRepository.findById(id);

        return company.<ResponseEntity<Object>>map(publishing -> ResponseEntity.status(HttpStatus.OK).body(publishing)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found"));
    }

    @PutMapping("/publishing/{id}")
    public ResponseEntity<Object> updatePublishing(@PathVariable(value = "id") Long id, @RequestBody @Valid PublishingDto publishingDto){
        Optional<Publishing> company = publishingRepository.findById(id);

        if (company.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }

        var publishing = company.get();
        BeanUtils.copyProperties(publishingDto, publishing);
        return ResponseEntity.status(HttpStatus.OK).body(publishingRepository.save(publishing));
    }

    @DeleteMapping("/publishing/{id}")
    public ResponseEntity<Object> deletePublish(@PathVariable(value = "id") Long id){
        Optional<Publishing> company = publishingRepository.findById(id);

        if (company.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        publishingRepository.delete(company.get());
        return ResponseEntity.status(HttpStatus.OK).body("The publishing has been deleted successfully");
    }
}
