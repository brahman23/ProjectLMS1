package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import peaksoft.enums.StudyFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;

    @Column(length = 100000, name = "first_name")
    private String firstName;

    @Column(length = 100000, name = "last_name")
    private String lastName;

    @Column(length = 100000, name = "phone_number")
    private String phoneNumber;


    @Column(length = 100000, name = "email")
    private String email;

    @Column(length = 100000, name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {MERGE,DETACH, REFRESH, PERSIST}, fetch = FetchType.EAGER)
    private Group groups;
}
