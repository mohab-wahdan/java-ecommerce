// default package
// Generated Aug 25, 2024, 10:44:58 PM by Hibernate Tools 6.5.2.Final
package entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * OrderHasProducts generated by hbm2java
 */
@Entity
@Table(name="order_has_products"
    ,catalog="bookstore"
)
public class OrderHasProducts  implements java.io.Serializable {


     private OrderHasProductsId id;
     private Product product;
     private Order order;
     private Integer quantity;
     private BigDecimal price;

    public OrderHasProducts() {
    }

	
    public OrderHasProducts(OrderHasProductsId id, Product product, Order order) {
        this.id = id;
        this.product = product;
        this.order = order;
    }
    public OrderHasProducts(OrderHasProductsId id, Product product, Order order, Integer quantity, BigDecimal price) {
       this.id = id;
       this.product = product;
       this.order = order;
       this.quantity = quantity;
       this.price = price;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="orderId", column=@Column(name="order_id", nullable=false) ), 
        @AttributeOverride(name="productId", column=@Column(name="product_id", nullable=false) ) } )
    public OrderHasProductsId getId() {
        return this.id;
    }
    
    public void setId(OrderHasProductsId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false, insertable=false, updatable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    
    @Column(name="quantity")
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="price", precision=10, scale=2)
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }




}


