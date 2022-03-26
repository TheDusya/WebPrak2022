package com.example.Shop.tables;

import com.example.Shop.jsons.JsonAddress;
import com.example.Shop.types.request_state;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "request")
public class TableRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id", unique=true, nullable=false)
    private Long request_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "client_id")
    @ToString.Exclude
    private Client client_id;

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
}
