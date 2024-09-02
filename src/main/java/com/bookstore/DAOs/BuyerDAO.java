package com.bookstore.DAOs;

import com.bookstore.entities.Buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerDAO extends JpaDAO<Buyer> implements GenericDAO<Buyer>{
    public BuyerDAO(){
    }

    @Override
    public Buyer create (Buyer buyer){
        return super.create(buyer);
    }

    @Override
    public Buyer update(Buyer buyer){
        return super.update(buyer);
    }

    @Override
    public Buyer get(Object BuyerId){
        return super.find(Buyer.class , BuyerId);
    }

    @Override
    public void delete(Object userId) {
        super.delete(Buyer.class, userId);
    }

    @Override
    public List<Buyer> listAll() {
        return super.findWithNameQuery("Buyer.listAll");
    }

    public Buyer findByEmail(String email){
        List<Buyer> result =  super.findWithNameQuery("Buyer.findByEmail" , "email" , email);

        if (result.size()>0){
            return result.get(0);
        }
        return null;

    }

    public Buyer checkLogin(String email , String password){
        Map<String, Object> params = new HashMap<>();
        params.put("email" , email);
        params.put("password" , password);
        List<Buyer> result =  super.findWithNameQuery("Buyer.checkLogin" , params);
        if (result.size()>0){
            return result.get(0);
        }
        return null;
    }



}
