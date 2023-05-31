package com.example.animeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "anime")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    @NotBlank(message = "Please Enter the Anime Name")
    private String name;

    @Column(name = "brand")
    @NotBlank(message = "Please Enter the Brand Name")
    private String brand;

    @Column(name = "description")
    @JsonIgnore
    @NotBlank(message = "Please Enter the Anime Description")
    private String description;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "category_id")
    @JsonIgnore
    private int categoryId;

    @Column(name = "purchases")
    private int purchases;

    @Column(name = "views")
    private int views;

    @Transient
    private MultipartFile file;

    public Anime() {
        this.code = "PRO" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

}
