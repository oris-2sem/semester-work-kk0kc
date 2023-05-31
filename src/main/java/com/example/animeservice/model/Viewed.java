package com.example.animeservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "viewed")
public class Viewed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "viewed", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ViewedUnit> viewedUnitList;

    @Column(name = "viewed_unit")
    private int viewedUnit;
}
