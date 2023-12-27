package com.coursework.project.entity.Volunteer;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "concerns")
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concern_name", nullable = false)
    private String concernName;

//    @OneToMany(mappedBy = "concern")
//    private List<Volunteer> volunteers = new ArrayList<>();

//    @ManyToMany(mappedBy = "usedConcerns")
//    Set<Volunteer> volunteers;
}
