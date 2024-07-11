package com.example.books.services;

import com.example.books.dtos.PublishingDto;
import com.example.books.exceptions.AlreadyExistsException;
import com.example.books.exceptions.NotFoundException;
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

    public Publishing register(PublishingDto publishingDto){
        Optional<Publishing> verification = publishingRepository.findByCompany(publishingDto.company());
        if(verification.isPresent()){
            throw new AlreadyExistsException("The company has already been registered");
        }

        var publishing = new Publishing();
        BeanUtils.copyProperties(publishingDto, publishing);
        publishingRepository.save(publishing);
        return publishing;
    }

    public List<Publishing> getAll(){
        return publishingRepository.findAll();
    }

    public Publishing getOne(Long id){
        Optional<Publishing> company = publishingRepository.findById(id);
        if (company.isEmpty()){
            throw new NotFoundException("Company not found");
        }
        return company.get();
    }

    public Publishing update(Long id, PublishingDto publishingDto){
        var publishing = getOne(id);
        BeanUtils.copyProperties(publishingDto, publishing);
        publishingRepository.save(publishing);
        return publishing;
    }

    public String delete(Long id){
        Publishing company = getOne(id);
        publishingRepository.delete(company);
        return "Deleted";
    }

    public Long ifCompanyExists(String company){
        Optional<Publishing> verification = publishingRepository.findByCompany(company);
        if(verification.isEmpty()){
            throw new NotFoundException("Company not found");
        } else{
            return verification.get().getId_pub();
        }
    }
}
