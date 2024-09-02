package com.bookstore.DAOs;

import com.bookstore.entities.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAO extends JpaDAO<Admin> implements GenericDAO<Admin>{
    public AdminDAO(){
    }

    @Override
    public Admin create (Admin admin){
        return super.create(admin);
    }

    @Override
    public Admin update(Admin admin){
        return super.update(admin);
    }

    @Override
    public Admin get(Object AdminId){
        return super.find(Admin.class , AdminId);
    }

    @Override
    public void delete(Object userId) {
        super.delete(Admin.class, userId);
    }

    @Override
    public List<Admin> listAll() {
        return super.findWithNameQuery("Admin.listAll");
    }

    public Admin findByEmail(String email){
        List<Admin> result =  super.findWithNameQuery("Admin.findByEmail" , "email" , email);

        if (result.size()>0){
            return result.get(0);
        }
        return null;

    }

    public Admin checkLogin(String email , String password){
        Map<String, Object> params = new HashMap<>();
        params.put("email" , email);
        params.put("password" , password);
        List<Admin> result =  super.findWithNameQuery("Admin.checkLogin" , params);
        if (result.size()>0){
            return result.get(0);
        }
        return null;
    }

}
