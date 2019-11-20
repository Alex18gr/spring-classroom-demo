package io.alexc.classroomdemo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="_id")
// @JsonIgnoreProperties(ignoreUnknown = true)
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class Classroom {

    @Id
    @Column(name = "classroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "classroom_name")
    private String name;

    @OneToMany(mappedBy = "classroom")
    @JsonManagedReference
//    @Getter(value=AccessLevel.NONE)
//    @Setter(value= AccessLevel.NONE)
    private Collection<Student> students;

}
