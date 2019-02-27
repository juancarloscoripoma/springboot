package com.soft.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @NonNull
    private String type;

    @Column(name = "areacode")
    @NonNull
    private Integer areacode;

    @Column(name = "number")
    @NonNull
    private Integer number;

    @ManyToOne
    @NonNull
    private Employee employee;
}
