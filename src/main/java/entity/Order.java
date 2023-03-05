package entity;

import entity.embeddable.DeliveryAdress;
import entity.enums.PaymentCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "products"})
@Builder
@Entity
@Table(name = "orders", schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private DeliveryAdress deliveryAdress;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "payment_condition")
    @Enumerated(EnumType.STRING)
    private PaymentCondition paymentCondition;

    @ManyToOne
    private User user;

    public void setUser(User user) {
        this.user = user;
        this.user.getOrders().add(this);
    }

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "shopping_cart",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        products.add(product);
//        product.getOrders().add(this);
    }
}
