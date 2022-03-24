package com.example.Shop.tables;

import javax.persistence.*;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "client")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class TableClient {
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
    @Type(type = "jsonb")
    private JsonAddress address;

    @Column (length = 16, name = "phone")
    private String phone;
}

