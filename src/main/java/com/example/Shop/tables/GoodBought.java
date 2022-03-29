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
    public Long getGoodBought_id() { return good_bought_id; }
    public Request getRequest() { return request; }
    public Good getGood() { return good; }
    public Integer getAmount() { return amount; }
    //*/
}

