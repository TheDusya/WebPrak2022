package com.example.Shop.tables;


import com.example.Shop.types.tech_type;
import com.example.Shop.util.HashMapConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings("JpaAttributeTypeInspection")
@Table(name = "good")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Good {
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
    private String chars;

    @Column
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> charsAttributes;

    @Column (name = "price")
    private Integer price;

    @Column (name = "in_stock")
    private Integer in_stock;

    @Column (length = 64,name = "country")
    private String country;
}
