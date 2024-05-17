package com.example.livros.repositories;

import com.example.livros.models.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface LivroRepository extends JpaRepository<LivroModel, UUID> {
}
