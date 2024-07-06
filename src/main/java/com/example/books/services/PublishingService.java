package com.example.books.services;

import com.example.books.dtos.PublishingDto;
import com.example.books.models.Publishing;
import com.example.books.repositories.PublishingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingService {

    private final PublishingRepository publishingRepository;

    @Autowired
    public PublishingService(PublishingRepository publishingRepository) {
        this.publishingRepository = publishingRepository;
    }

    public Object register(PublishingDto publishingDto){
        var verification = publishingRepository.findByCompany(publishingDto.company());

        if(verification.isEmpty()){
            var publishing = new Publishing();
            BeanUtils.copyProperties(publishingDto, publishing);
            publishingRepository.save(publishing);
            return publishing;
        } else{
            throw new RuntimeException("The company has already being on the system");
        }
    }

    public List<Publishing> getAll(){
        return publishingRepository.findAll();
    }

    public Object getOne(Long id){
        Optional<Publishing> company = publishingRepository.findById(id);

        if (company.isEmpty()){
            return "Company not found";
        }
        return company;
    }

    public Object update(Long id, PublishingDto publishingDto){
        Optional<Publishing> company = publishingRepository.findById(id);

        if (company.isEmpty()){
            return "Company not found";
        }

        var publishing = company.get();
        BeanUtils.copyProperties(publishingDto, publishing);
        publishingRepository.save(publishing);
        return publishing;
    }

    public boolean delete(Long id){
        Optional<Publishing> company = publishingRepository.findById(id);

        if (company.isEmpty()){
            return false;
        }
        publishingRepository.delete(company.get());
        return true;
    }

}
