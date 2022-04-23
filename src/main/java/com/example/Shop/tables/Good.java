package com.example.Shop.tables;

import javax.persistence.*;
import com.example.Shop.types.tech_type;
import com.example.Shop.util.HashMapConverter;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings("JpaAttributeTypeInspection")
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="good_id", unique=true, nullable=false)
    private Long good_id;

    @Column (length = 64, nullable = false, name = "manufacturer")
    private String manufacturer;

    @Column (length = 64, nullable = false, name = "model")
    private String model;

    @Column (name = "kind", nullable = false)
    private String kind;

    @Column (name = "chars")
    private String chars;

    @Column (name = "price", nullable = false)
    private Integer price;

    @Column (name = "in_stock", nullable = false)
    private Integer in_stock;

    @Column (length = 64,name = "country")
    private String country;

    ///*
    public Long getGood_id() { return good_id; }
    public String getManufacturer() { return manufacturer; }
    public String getModel() { return model; }
    public String getKind() { return kind; }
    public String getChars() { return chars.toString(); }
    public Integer getPrice() { return price; }
    public Integer getIn_stock() { return in_stock; }
    public String getCountry() { return country; }

    public void setGood_id(Long good_id) { this.good_id = good_id; }
    public void setManufacturer(String man) { this.manufacturer = man; }
    public void setModel(String model) { this.model = model; }
    public void setKind(String kind) { this.kind = kind; }
    public void setChars(String chars) { this.chars = chars; }
    public void setPrice(Integer price) { this.price = price; }
    public void setIn_stock(Integer am) { this.in_stock = am; }
    public void setCountry(String country) { this.country = country; }
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

    public Good(String manufacturer, String model, String kind, Integer price, Integer in_stock){
        this.manufacturer = manufacturer;
        this.model = model;
        this.kind = kind;
        this.price = price;
        this.in_stock = in_stock;
    }

    public Good(){}


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
