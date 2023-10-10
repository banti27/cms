package com.cms.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long addressId;

    private String street1;
    private String street2;
    private String street3;
    private String city;
    private String state;
    private Integer zipCode;
    private String country;

    @ManyToMany(mappedBy = "addresses")
    private List<Client> clients = new ArrayList<>();

}
