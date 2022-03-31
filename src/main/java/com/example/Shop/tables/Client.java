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
@AllArgsConstructor
@RequiredArgsConstructor
@SuppressWarnings("JpaAttributeTypeInspection")
@Table(name = "client")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id", unique=true, nullable=false)
    private Long client_id;

    @NonNull
    @Column (length = 128, nullable = false, name = "login")
    private String login;

    @NonNull
    @Column (length = 128, nullable = false, name = "pass")
    private String pass;

    @Column (length = 128, name = "real_name")
    private String real_name;

    @NonNull
    @Column (name = "is_admin", nullable = false)
    private Boolean is_admin;

    @Column (length = 320, name = "mail")
    private String mail;

    @Column (name = "address")
    private String address;

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
    ///*
    public void setClient_id(Long id) { client_id = id; }
    public void setPass(String pass) { this.pass = pass; }
    public void setReal_name(String name) { real_name = name; }
    public void setIs_admin(boolean is) { is_admin = is; }
    public void setAddress(String address) { this.address = address;}
    public void setMail(String mail) { this.mail = mail; }
    public void setPhone(String phone) { this.phone = phone; }
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

    @Override
    public int hashCode() {
        return client_id.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || obj.getClass()!=this.getClass()){
            return false;
        }
        else return ((Client) obj).getClient_id()==this.client_id;
    }

    public Client(String l, String p, boolean a){
        login = l;
        pass = p;
        is_admin = a;
        address = null;
    }
    public Client(){}
}


