package com.coursework.project.entity;

import com.coursework.project.entity.Volunteer.Branch;
import com.coursework.project.entity.Volunteer.Experience;
import com.coursework.project.entity.Volunteer.Volunteer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", client=" + client +
                ", volunteer=" + volunteer +
                ", status=" + status +
                '}';
    }


    //    @ManyToOne(optional = false)
//    @JoinColumn(name = "branch_id", nullable = false)
//    private Branch branch;

//    @Column(nullable=false)
//    private String text;
}
