package com.example.Shop.tables;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "good")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class TableGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="good_id", unique=true, nullable=false)
    private Long good_id;

    @Column (length = 64, nullable = false, name = "manufacturer")
    private String manufacturer;

    @Column (length = 64, nullable = false, name = "model")
    private String model;

    @Column (name = "kind")
    private tech_type kind;

    @Column (name = "chars")
    @Type(type = "jsonb")
    private JsonTech chars;

    @Column (name = "price")
    private Integer price;

    @Column (name = "in_stock")
    private Integer in_stock;

    @Column (length = 64,name = "country")
    private String country;
}
