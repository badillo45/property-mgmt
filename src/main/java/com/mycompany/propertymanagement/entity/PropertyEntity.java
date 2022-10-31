package com.mycompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "T_PROPERTY")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    @Column(nullable = false,name = "PROPERTY_TITLE")
    private String title;
    @Column(name = "PROPERTY_DESC")
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private AddressEntity address;

}
