package com.example.books.repositories;

import com.example.books.models.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishingRepository extends JpaRepository<Publishing, Long> {

    Object findByCompany(String company);


}
