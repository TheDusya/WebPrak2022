package com.example.Shop.tables;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "good_bought")
public class GoodBought {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="good_bought_id", unique=true, nullable=false)
    private Long good_bought_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "request_id")
    @NonNull
    @ToString.Exclude
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "good_id")
    @NonNull
    @ToString.Exclude
    private Good good;

    @Column(name = "amount")
    private Integer amount;

    //*
    public Long getGood_bought_id() { return good_bought_id; }
    public Request getRequest() { return request; }
    public Good getGood() { return good; }
    public Integer getAmount() { return amount; }
    //*/


    @Override
    public int hashCode() {
        return good_bought_id.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj== null || obj.getClass()!=this.getClass()){
            return false;
        }
        else return ((GoodBought) obj).getGood_bought_id()==this.good_bought_id;

    }
}

