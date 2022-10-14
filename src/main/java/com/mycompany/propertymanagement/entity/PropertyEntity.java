package com.mycompany.propertymanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.propertymanagement.dto.PropertyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false)
    private Long id;
    @Column(nullable = false,name = "PROPERTY_TITLE")
    private String title;
    @Column(name = "PROPERTY_DESC")
    private String description;
    private String ownerName;
    private String ownerEmail;
    private Double price;
    private String address;

}
