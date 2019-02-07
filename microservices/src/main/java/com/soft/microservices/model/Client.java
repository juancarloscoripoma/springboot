package com.soft.microservices.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nit")
    private Integer nit;

    @Column(name = "reasonsocial")
    private String reasonsocial;
}
