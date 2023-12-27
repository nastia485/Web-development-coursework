package com.coursework.project.entity.Volunteer;

import com.coursework.project.entity.User;
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
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "qualification_id", nullable = false)
    private Qualification qualification;

    @ManyToOne(optional = false)
    @JoinColumn(name = "experience_id", nullable = false)
    private Experience experience;

    @ManyToOne(optional = false)
    @JoinColumn(name = "concern_id", nullable = false)
    private Concern concern;

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }

    //    @ManyToMany
//    private List<Concern> usedConcerns;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "volunteers_concerns",
////            joinColumns = {@JoinColumn(name = "volunteer_id", referencedColumnName="id")},
////            inverseJoinColumns = {@JoinColumn(name = "concern_id", referencedColumnName="id")})
//    Set<Concern> usedConcerns;


}
