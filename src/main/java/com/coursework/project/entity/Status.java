package com.coursework.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }

    public boolean isSelected(Status status) {
        return this.equals(status);
    }
}
