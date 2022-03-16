package com.example.springbootdataredisdemo.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String firstName;
    private String lastName;
}
