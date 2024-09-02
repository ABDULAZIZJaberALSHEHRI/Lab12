package com.example.lab12security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title should not be empty!")
    @Size(max = 30,message = "Title length should be less than '30'!")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "Body should not be empty!")
    @Size(max = 200,message = "Body length should be less than '200'!")
    @Column(columnDefinition = "varchar(200) not null")
    private String body;

    @ManyToOne
    @JsonIgnore
    private User user;
}
