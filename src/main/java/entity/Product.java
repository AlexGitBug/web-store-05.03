package entity;

import entity.enums.Brand;
import entity.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"catalog", "orders"})
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    private String model;
    private LocalDate dateOfRelease;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

}
