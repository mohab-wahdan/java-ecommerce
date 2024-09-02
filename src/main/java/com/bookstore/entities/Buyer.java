package com.bookstore.entities;// default package
// Generated Aug 24, 2024, 4:07:45 PM by Hibernate Tools 6.5.2.Final


import jakarta.persistence.*;

import java.util.Optional;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Buyer generated by hbm2java
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Buyer.listAll" , query = "select b from Buyer b order by name"),
                @NamedQuery(name = "Buyer.findByEmail" , query = " select b from Buyer b where  b.email = :email"),
                @NamedQuery(name = "Buyer.checkLogin" , query = "select b from Buyer b where b.email = :email and b.password = :password" )
        }
)
@Table(name="buyer"
    ,catalog="bookstore"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class Buyer  implements java.io.Serializable {


     private int id;
     private String name;
     private String email;
     private String password;
     private Date birthday;
     private String building;
     private String street;
     private String city;
     private String phone;
     private BigDecimal cardLimit;
     private String job;
     private Set<BuyerChosenProducts> buyerChosenProductses = new HashSet<BuyerChosenProducts>(0);
     private Set<Order> order = new HashSet<Order>(0);
     private Set<BuyerInterests> buyerInterestses = new HashSet<BuyerInterests>(0);

    public Buyer() {
    }

	
    public Buyer(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Buyer(int id, String name, String email, String password, Date birthday, String building, String street, String city, String phone, BigDecimal cardLimit, String job, Set<BuyerChosenProducts> buyerChosenProductses, Set<Order> orders, Set<BuyerInterests> buyerInterestses) {
       this.id = id;
       this.name = name;
       this.email = email;
       this.password = password;
       this.birthday = birthday;
       this.building = building;
       this.street = street;
       this.city = city;
       this.phone = phone;
       this.cardLimit = cardLimit;
       this.job = job;
       this.buyerChosenProductses = buyerChosenProductses;
       this.order = order;
       this.buyerInterestses = buyerInterestses;
    }
   
     @Id

     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false, length=255)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="email", unique=true, nullable=false, length=255)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="password", nullable=false, length=255)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="birthday", length=10)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    
    @Column(name="building", length=255)
    public String getBuilding() {
        return this.building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }

    
    @Column(name="street", length=255)
    public String getStreet() {
        return this.street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    
    @Column(name="city", length=255)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    
    @Column(name="phone", length=20)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="card_limit", precision=10, scale=2)
    public BigDecimal getCardLimit() {
        return this.cardLimit;
    }
    
    public void setCardLimit(BigDecimal cardLimit) {
        this.cardLimit = cardLimit;
    }

    
    @Column(name="job", length=255)
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="buyer")
    public Set<BuyerChosenProducts> getBuyerChosenProductses() {
        return this.buyerChosenProductses;
    }
    
    public void setBuyerChosenProductses(Set<BuyerChosenProducts> buyerChosenProductses) {
        this.buyerChosenProductses = buyerChosenProductses;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="buyer")
    public Set<Order> getOrders() {
        return this.order;
    }
    
    public void setOrders(Set<Order> orders) {
        this.order = orders;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="buyer")
    public Set<BuyerInterests> getBuyerInterestses() {
        return this.buyerInterestses;
    }
    
    public void setBuyerInterestses(Set<BuyerInterests> buyerInterestses) {
        this.buyerInterestses = buyerInterestses;
    }




}


