package com.smarttra.webservice.service;

import com.smarttra.webservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    public Product findById(BigDecimal id);

    public void saveProduct(Product product);

    public boolean updateProduct(Product product);

    public boolean deleteProduct(BigDecimal id);

    public boolean isProductExist(Product Product);
}
