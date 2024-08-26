package dao;

import entities.Product;

public class ProductDAO extends BaseDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }
}
