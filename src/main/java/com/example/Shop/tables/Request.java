package com.example.Shop.tables;

import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.jsons.JsonAddress;
import com.example.Shop.types.request_state;
import com.example.Shop.tables.Client;
import com.example.Shop.types.tech_type;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id", unique=true, nullable=false)
    private Long request_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    @JoinColumn (name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column (name = "cur_state")
    private request_state cur_state;

    @Column (name = "registration_time")
    private Timestamp registration_time;

    @Column (name = "delivery_time")
    private Timestamp delivery_time;

    @Column (name = "delivery_cost")
    private Integer delivery_cost;

    @Column (name = "delivery_address")
    @Type(type = "jsonb")
    private JsonAddress delivery_address;

    ///*
    public Long getRequest_id() { return request_id; }
    public Client getClient() { return client; }
    public request_state getCur_state() { return cur_state; }
    public Timestamp getRegistration_time() { return registration_time; }
    public Timestamp getDelivery_time() { return delivery_time; }
    public Integer getDelivery_cost() { return delivery_cost; }
    public String getDelivery_address() { return delivery_address.toString(); }
    //*/

    @Override
    public String toString(){
        DAORequest rdao = DAOFactory.getInstance().getRDAO();
        DAOGoodBought gbdao = DAOFactory.getInstance().getGBDAO();
        String ans = "Request [ID: " + request_id +
                ", client login: " + client.getLogin() +
                ", client name: " + client.getReal_name() +
                ", sum: " + rdao.getCost(this) +
                ", " + gbdao.getGoodsBoughtByRequestID(request_id) +
                ", current state: " + cur_state +
                ", registered: " + registration_time +
                ", delivered: " + delivery_time +
                ", delivery address: " + delivery_address + "]";
        return ans;
    }
}
