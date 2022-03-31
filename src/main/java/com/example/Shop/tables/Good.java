package com.example.Shop.tables;

import javax.persistence.*;
import com.example.Shop.types.tech_type;
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
@RequiredArgsConstructor
@SuppressWarnings("JpaAttributeTypeInspection")
@Table(name = "good")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="good_id", unique=true, nullable=false)
    private Long good_id;

    @Column (length = 64, nullable = false, name = "manufacturer")
    private String manufacturer;

    @Column (length = 64, nullable = false, name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column (name = "kind")
    private tech_type kind;

    @Column (name = "chars")
    private String chars;

    @Column (name = "price")
    private Integer price;

    @Column (name = "in_stock")
    private Integer in_stock;

    @Column (length = 64,name = "country")
    private String country;

    ///*
    public Long getGood_id() { return good_id; }
    public String getManufacturer() { return manufacturer; }
    public String getModel() { return model; }
    public tech_type getKind() { return kind; }
    public String getChars() { return chars.toString(); }
    public Integer getPrice() { return price; }
    public Integer getIn_stock() { return in_stock; }
    public String getCountry() { return country; }
    //*/

    @Override
    public String toString() {
        return "Good [ID=" + good_id +
                ", manufacturer=" + manufacturer +
                ", model=" + model +
                ", kind=" + kind +
                ", characteristics=" + chars +
                ", price=" + price +
                ", in_stock=" + in_stock +
                ", country=" + country + "]";

    }

    @Override
    public int hashCode() {
        return good_id.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj== null || obj.getClass()!=this.getClass()){
            return false;
        }
        else return ((Good) obj).getGood_id()==this.good_id;
    }

}
