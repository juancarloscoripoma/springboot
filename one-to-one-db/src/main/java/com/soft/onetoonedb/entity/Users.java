package com.soft.onetoonedb.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "firstname")
    @NonNull
    private String firstname;

    //@OneToOne/*(cascade = CascadeType.ALL)*/ // fetch = FetchType.LAZY
    /*@JoinColumn(unique = true) *///(name = "address_id"/*, referencedColumnName = "id"*/)

    @OneToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NonNull
    private Address address;

}
