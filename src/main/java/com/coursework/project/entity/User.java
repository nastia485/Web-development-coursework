package com.coursework.project.entity;

import com.coursework.project.entity.Volunteer.Volunteer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", First name='" + firstName + '\'' +
                '}';
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Volunteer volunteer;

}
