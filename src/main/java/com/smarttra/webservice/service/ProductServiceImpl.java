package com.smarttra.webservice.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    public Product findById(BigDecimal id) {
        return productDao.findById(id);
    }

    public void saveProduct(Product product) {
        productDao.save(product);
    }

    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public boolean deleteProduct(BigDecimal id) {
        return productDao.delete(id);
    }

    public boolean isProductExist(Product product) {
        return findById(product.getName())!=null;
    }

}
