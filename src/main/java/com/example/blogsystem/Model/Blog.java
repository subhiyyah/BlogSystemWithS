package com.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
//id - title - body
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title can not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    @NotEmpty(message = "body can not be empty")
    @Size(max = 1000,message = "max size is 1000 character")
    @Column(columnDefinition = "varchar(1000) not null")
    private String body;






    ////////////Relation

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

}