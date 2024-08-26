package org.example;

//import entities.*;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Create an EntityManagerFactory with your persistence unit name
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mohab");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Begin transaction
        entityManager.getTransaction().begin();

        // Insert a record into the Admin table
        Admin admin = new Admin();
        admin.setId(2);
        admin.setName("Admin Name");
        admin.setEmail("admin2@example.com");
        admin.setPassword("admin123");
        admin.setBirthday(Date.valueOf("1985-08-25"));
        admin.setJob("Manager");
        admin.setCity("Admin City");
        admin.setStreet("Admin Street");
        admin.setPhone("1234567890");
        entityManager.persist(admin);

        // Insert a record into the Buyer table
        Buyer buyer = new Buyer();
        //buyer.setId(1);
        buyer.setName("Buyer Name");
        buyer.setEmail("buyer2@example.com");
        buyer.setPassword("buyer123");
        buyer.setBirthday(Date.valueOf("1990-01-15"));
        buyer.setBuilding(String.valueOf(123));
        buyer.setStreet("Buyer Street");
        buyer.setCity("Buyer City");
        buyer.setPhone("0987654321");
        buyer.setCardLimit(BigDecimal.valueOf(5000));
        buyer.setJob("Engineer");

        // Insert related entities for Buyer
        Set<BuyerChosenProducts> chosenProducts = new HashSet<>();
        Set<BuyerInterests> interests = new HashSet<>();
        buyer.setBuyerChosenProductses(chosenProducts);
        buyer.setBuyerInterestses(interests);

        entityManager.persist(buyer);

        // Insert a record into the Product table
        Product product = new Product();
        //product.setProductId(1);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(BigDecimal.valueOf(199.99));
        product.setImagesPath("/images/product.png");
        product.setStockQuantity(50);
        product.setAuthor("Author Name");
        product.setPublishDate(Date.valueOf("2023-07-15"));
        product.setPages(300);

        // Insert related entities for Product
        Set<OrderHasProducts> orderHasProducts = new HashSet<>();
        Set<Category> categories = new HashSet<>();
        product.setOrderHasProductses(orderHasProducts);
        product.setCategories(categories);

        entityManager.persist(product);

//        OrderHasProductsId ohbId = new OrderHasProductsId(1,1);
        Set<OrderHasProducts> order_products = new HashSet<>();
        Order order = new Order(buyer,Date.valueOf("2023-07-15"),order_products);
//        OrderHasProducts ohp = new OrderHasProducts(ohbId,order, product,5,120);
        entityManager.persist(order);
        // Insert a record into the OrderHasProducts table
//        OrderHasProductsId orderHasProductsId = new OrderHasProductsId(1, 1);
//        OrderHasProducts orderProduct = new OrderHasProducts();
//        orderProduct.setId(orderHasProductsId);
//        orderProduct.setOrder(/* Assume Order entity is already created and persisted */ null);
//        orderProduct.setProduct(product);
//        orderProduct.setQuantity(2);
//        orderProduct.setPrice(BigDecimal.valueOf(199.99));
//        entityManager.persist(orderProduct);

        // Commit transaction
        entityManager.getTransaction().commit();

        // Close EntityManager and EntityManagerFactory
        entityManager.close();
        //entityManagerFactory.close();
    }
}
