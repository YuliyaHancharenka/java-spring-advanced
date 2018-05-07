package com.epam.cdp.springbootlab.good;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
}
