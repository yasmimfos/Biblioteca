package com.example.books.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publishing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pub;
    private String company;

}
