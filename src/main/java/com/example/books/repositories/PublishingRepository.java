package com.example.books.repositories;

import com.example.books.models.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PublishingRepository extends JpaRepository<Publishing, Long> {

    Optional<Publishing> findByCompany(String company);

}
