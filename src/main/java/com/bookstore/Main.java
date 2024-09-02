package com.bookstore;

import com.bookstore.entities.*;
import com.bookstore.dao.*;

import java.util.Optional;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        BuyerDAO dao = new BuyerDAO();


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("omar");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Buyer b = em.find(Buyer.class, 1);
        Product p = em.find(Product.class, 2);


        System.out.println("hi");

        em.getTransaction().commit();



        //deleteCartItem(b, p);
        clearCart(b);


    }

    public static void addProductToCart(Buyer buyer, Product product, int quantity) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("omar");
        EntityManager entityManager = emf.createEntityManager();

        int productId = product.getProductId();

        Optional<BuyerChosenProducts> existingEntry = buyer.getBuyerChosenProductses().stream()
                .filter(bcp -> bcp.getProduct().getProductId() == productId)
                .findFirst();


        if (existingEntry.isPresent()) {
            BuyerChosenProducts entry = existingEntry.get();
            entry.setQuantity(entry.getQuantity() + quantity); // Replace quantity with the desired quantity to add
            entityManager.merge(entry);
        } else {
            // 4. If the product is not in the cart, create a new BuyerChosenProducts entry
            BuyerChosenProductsId id = new BuyerChosenProductsId(buyer.getId(), productId);
            BuyerChosenProducts newEntry = new BuyerChosenProducts(id, buyer, product, quantity);
            entityManager.persist(newEntry);
        }


        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.close();
        emf.close();
    }

    public static void deleteCartItem(Buyer buyer, Product product) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("omar");

        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Find the BuyerChosenProducts entity within the transaction
            Optional<BuyerChosenProducts> existingEntry = buyer.getBuyerChosenProductses().stream()
                    .filter(bcp -> bcp.getProduct().getProductId() == product.getProductId())
                    .findFirst();

            if (existingEntry.isPresent()) {
                BuyerChosenProducts entry = existingEntry.get();
                entry = entityManager.merge(entry);
                entityManager.remove(entry);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();

        }
    }


    public static void clearCart(Buyer buyer) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("omar");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            buyer.getBuyerChosenProductses().forEach(entry -> {
                entry = entityManager.merge(entry);
                entityManager.remove(entry);
            });
            buyer.getBuyerChosenProductses().clear();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();

        }

    }
}
