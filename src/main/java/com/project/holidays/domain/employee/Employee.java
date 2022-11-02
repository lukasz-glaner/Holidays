package com.project.holidays.domain.employee;

import com.project.holidays.domain.holiday.Holiday;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany
    @JoinColumn(name = "HOLIDAY_ID", referencedColumnName = "ID")
    private Set<Holiday> holidays  = new HashSet<Holiday>();

}
