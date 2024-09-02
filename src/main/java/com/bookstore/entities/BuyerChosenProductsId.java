package com.bookstore.entities;// default package
// Generated Aug 24, 2024, 4:07:45 PM by Hibernate Tools 6.5.2.Final


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * BuyerChosenProductsId generated by hbm2java
 */
@Embeddable
public class BuyerChosenProductsId  implements java.io.Serializable {


     private int id;
     private int productId;

    public BuyerChosenProductsId() {
    }

    public BuyerChosenProductsId(int id, int productId) {
       this.id = id;
       this.productId = productId;
    }
   


    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }


    @Column(name="product_id", nullable=false)
    public int getProductId() {
        return this.productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BuyerChosenProductsId) ) return false;
		 BuyerChosenProductsId castOther = ( BuyerChosenProductsId ) other; 
         
		 return (this.getId()==castOther.getId())
 && (this.getProductId()==castOther.getProductId());
   }
   
   public int hashCode() {
         int result = 17;
         
        result = 37 * result + this.getId();
        result = 37 * result + this.getProductId();
         return result;
   }   


}


