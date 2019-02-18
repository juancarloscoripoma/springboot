package com.soft.onetoonedb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "firstName")
    private String firstName;

    //@OneToOne/*(cascade = CascadeType.ALL)*/ // fetch = FetchType.LAZY
    /*@JoinColumn(unique = true) *///(name = "address_id"/*, referencedColumnName = "id"*/)
    /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    */

}
