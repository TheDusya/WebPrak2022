package com.example.Shop.tables;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
    @JoinColumn (nullable = false, name = "request_id")
    @NonNull
    @ToString.Exclude
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (nullable = false, name = "good_id")
    @NonNull
    @ToString.Exclude
    private Good good;

    @Column(name = "amount")
    private Integer amount;

    private Integer actualPrice;

    //*
    public Long getGood_bought_id() { return good_bought_id; }
    public Request getRequest() { return request; }
    public Good getGood() { return good; }
    public Integer getAmount() { return amount; }
    public Integer getActualPrice() { return actualPrice; }

    public void setRequest(Request request) { this.request=request; }
    public void setGood(Good good) { this.good=good; }
    public void setAmount(int amount) { this.amount = amount; }
    //*/

    public GoodBought(Request request, Good good, Integer amount){
        this.request=request;
        this.good = good;
        this.amount = amount;
        if (good.getPrice()!=null && amount!=null)this.actualPrice = good.getPrice()*amount;
        else  this.actualPrice = null;
    }
    public GoodBought(){}

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

