package com.soft.microservices.model;

/**
 * Created by jcarlos on 07/02/2019.
 */
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import java.io.Serializable;

/*
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"economicActivity","billingMode","invoices"})
*/

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
