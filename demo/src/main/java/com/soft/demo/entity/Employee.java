package com.soft.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @NonNull
    private String firstname;

    @Column(name = "lastname")
    @NonNull
    private String lastname;

    @Column(name = "salary")
    @NonNull
    private BigDecimal salary;

    @OneToMany(mappedBy = "employee")
    private Set<Phone> phoneSet;
}
