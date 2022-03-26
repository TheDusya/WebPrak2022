package com.example.Shop.tables;

import javax.persistence.*;

import com.example.Shop.util.HashMapConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.TypeDef;

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
@Table(name = "client")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id", unique=true, nullable=false)
    private Long client_id;

    @Column (length = 128, nullable = false, name = "login")
    private String login;

    @Column (length = 128, nullable = false, name = "pass")
    private String pass;

    @Column (length = 128, name = "real_name")
    private String real_name;

    @Column (name = "is_admin")
    private Boolean is_admin;

    @Column (length = 320, name = "mail")
    private String mail;

    @Column (name = "address")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> Attributes;

    @Column (length = 16, name = "phone")
    private String phone;

}

