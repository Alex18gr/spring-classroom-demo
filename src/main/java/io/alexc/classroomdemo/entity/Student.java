package io.alexc.classroomdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "grade")
    private float grade;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

}
