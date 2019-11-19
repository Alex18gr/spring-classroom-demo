package io.alexc.classroomdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
public class Classroom {

    @Id
    @Column(name = "classroom_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "classroom_name")
    private String name;

    @OneToMany(mappedBy = "classroom")
    private Set<Student> students;

}
