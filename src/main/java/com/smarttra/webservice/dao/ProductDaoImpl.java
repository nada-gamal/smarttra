package com.smarttra.webservice.dao;

public class ProductDaoImpl implements ProductDao {

    public void save(Product p) {
        persist(p);
    }

    public boolean updateProduct(Product p) {
        Product currentProduct = (Product) getByKey(p.getId());

        if (currentProduct==null) {
            //System.out.println("Product with id " + id + " not found");
            return false;
        }

        currentProduct.setName(p.getName());
        currentProduct.setDescription(p.getDescription());
        currentProduct.setMinPrice(p.getMinPrice());
        currentProduct.setMaxPrice(p.getMaxPrice());


        update(currentProduct);
        return true;
    }

    public Product findById(BigDecimal id) {
        Product p = (Product) getByKey(id);
        return p;
    }

    public boolean delete(BigDecimal id) {
        Product p = (Product) getByKey(id);
        if (p != null){
            delete(p);
            return true;
        }
        else
            return false;
    }


}
