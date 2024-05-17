package com.example.livros.controller;

import com.example.livros.dtos.LivroRecordDto;
import com.example.livros.models.LivroModel;
import com.example.livros.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @PostMapping("/livros")
    public ResponseEntity<LivroModel> createLivro(@RequestBody @Valid LivroRecordDto livroRecordDto){
        var livroModel = new LivroModel();
        BeanUtils.copyProperties(livroRecordDto, livroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }

    @GetMapping("/livros")
    public ResponseEntity<List<LivroModel>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Object> getLivro(@PathVariable(value = "id") UUID id){
        Optional<LivroModel> livroOne = livroRepository.findById(id);
        if(livroOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("livro não ecnontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(livroOne.get());
    }

    @PutMapping("livros/{id}")
    public ResponseEntity<Object> updateLivro(@PathVariable(value = "id") UUID id, @RequestBody @Valid LivroRecordDto livroRecordDto){
        Optional<LivroModel> livro = livroRepository.findById(id);
        if(livro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("livro não ecnontrado");
        }

        var livroModel = livro.get();
        BeanUtils.copyProperties(livroRecordDto, livroModel);
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livroModel));
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Object> deleteLivro(@PathVariable(value = "id") UUID id){
        Optional<LivroModel> livro = livroRepository.findById(id);
        if(livro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("livro não ecnontrado");
        }
        livroRepository.delete(livro.get());
        return  ResponseEntity.status(HttpStatus.OK).body("livro deletado com sucesso");
    }
}
