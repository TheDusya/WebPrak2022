package com.example.Shop.tables;

import javax.persistence.*;
import com.example.Shop.util.HashMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Map<String, Object> address;

    @Column (length = 16, name = "phone")
    private String phone;

    ///*
    public Long getClient_id() { return client_id; }
    public String getLogin() { return login; }
    public String getPass() { return pass; }
    public String getReal_name() { return real_name; }
    public Boolean getIs_admin() { return is_admin; }
    public String getAddress() { return address.toString(); }
    public String getMail() { return mail; }
    public String getPhone() { return phone; }
    //*/

    @Override
    public String toString() {
        if (is_admin) return "Admin [ID=" + client_id +
                ", login=" + login +
                ", name=" + real_name +
                ", mail=" + mail +
                ", address=" + address +
                ", phone=" + phone + "]";
        else return "Client [ID=" + client_id +
                ", login=" + login +
                ", name=" + real_name +
                ", mail=" + mail +
                ", address=" + address +
                ", phone=" + phone + "]";
    }
}

