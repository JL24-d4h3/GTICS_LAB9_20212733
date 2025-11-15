package com.lab9.cliente_web.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Supplier {

    private Integer id;
    private String supplierName;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private String phone;
}
