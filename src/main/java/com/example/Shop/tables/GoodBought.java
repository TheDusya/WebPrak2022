package com.example.Shop.tables;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "good_bought")
public class GoodBought {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="good_bought_id", unique=true, nullable=false)
    private Long good_bought_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "request_id")
    @ToString.Exclude
    private TableRequest request_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "good_id")
    @ToString.Exclude
    private Good good_id;

    @Column(name = "amount")
    private Integer amount;
}

