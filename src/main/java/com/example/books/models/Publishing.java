package com.example.books.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publishing implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_pub;
    private String company;
}
