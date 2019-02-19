package com.soft.onetoonedb.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "addressline1")
    @NonNull
    private String addressline1;


    //@OneToOne(mappedBy = "address")
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Users users;

}
