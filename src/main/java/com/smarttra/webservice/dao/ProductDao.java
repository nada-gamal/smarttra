package com.smarttra.webservice.dao;

import com.smarttra.webservice.model.Product;

public interface ProductDao {

    public void save(Product p);

    public boolean updateProduct(Product p);

    public Product findById(BigDecimal id);

    public boolean delete(BigDecimal id);

}
