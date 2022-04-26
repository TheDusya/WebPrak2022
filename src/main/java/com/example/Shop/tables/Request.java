package com.example.Shop.tables;

import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.util.DAOFactory;
import com.example.Shop.util.HashMapConverter;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings("JpaAttributeTypeInspection")
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id", unique=true, nullable=false)
    private Long request_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (nullable = false, name = "client_id")
    private Client client;


    @Column (nullable = false, name = "cur_state")
    private String cur_state;

    @Column (name = "registration_time")
    private Date registration_time;

    @Column (name = "delivery_time")
    private Date delivery_time;

    @Column (name = "delivery_cost")
    private Integer delivery_cost;

    @Column (name = "delivery_address")
    private String delivery_address;

    ///*
    public Long getRequest_id() { return request_id; }
    public Client getClient() { return client; }
    public String getCur_state() { return cur_state; }
    public Date getRegistration_time() { return registration_time; }
    public Date getDelivery_time() { return delivery_time; }
    public Integer getDelivery_cost() { return delivery_cost; }
    public String getDelivery_address() { return delivery_address; }

    public void setRequest_id(Long request_id) { this.request_id=request_id; }
    public void setClient(Client client) { this.client =client; }
    public void setCur_state(String cur_state) { this.cur_state=cur_state; }
    public void setRegistration_time(Date registration_time) { this.registration_time = registration_time; }
    public void setDelivery_time(Date delivery_time) { this.delivery_time = delivery_time; }
    public void setDelivery_cost(Integer delivery_cost) { this.delivery_cost = delivery_cost; }
    public void setDelivery_address(String delivery_address) { this.delivery_address = delivery_address; }
    //*/

    @Override
    public String toString(){
        DAORequest rdao = DAOFactory.getInstance().getRDAO();
        DAOGoodBought gbdao = DAOFactory.getInstance().getGBDAO();
        HashMapConverter hashMapConverter = new HashMapConverter();
        String ans = "Request [ID: " + request_id +
                ", client login: " + client.getLogin() +
                ", client name: " + client.getReal_name() +
                ", sum: " + rdao.getCost(this) +
                ", " + gbdao.getGoodsBoughtByRequest(this) +
                ", current state: " + cur_state +
                ", registered: " + registration_time +
                ", delivered: " + delivery_time +
                ", delivery address: " + delivery_address + "]";
        return ans;
    }

    public Request(Client client, String state){
        this.client = client;
        this.cur_state = state;
    }
    public Request(){}

    @Override
    public int hashCode() {
        return request_id.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj== null || obj.getClass()!=this.getClass()){
            return false;
        }
        else return ((Request) obj).getRequest_id()==this.request_id;
    }

    public boolean regIsBetween (String dat1, String dat2){
        return !(registration_time).after(Date.valueOf(dat2)) &&
                !(registration_time).before(Date.valueOf(dat1));
    }
}
