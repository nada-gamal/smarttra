package com.smarttra.webservice.controller;


import com.smarttra.webservice.model.Product;
import com.smarttra.webservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;  //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Products--------------------------------------------------------

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.findAllProducts();
        if(products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    //-------------------Retrieve Single Product--------------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.findById(BigDecimal.valueOf(id));
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //-------------------Retrieve Single Product With Services--------------------------------------------------------

    @RequestMapping(value = "/product/{id}/services", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductServices(@PathVariable("id") long id) {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.findByIdWithService(BigDecimal.valueOf(id));
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }




    //-------------------Create a Product--------------------------------------------------------

    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, 	UriComponentsBuilder ucBuilder) {


        if (productService.isProductExist(product)) {
            System.out.println("A Product with name " + product.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //System.out.println("Creating Product " + product.toString());
        productService.saveProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Product --------------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        System.out.println("Updating Product " + id);
        product.setId(BigDecimal.valueOf(id));

        if(productService.updateProduct(product))
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    //------------------- Delete a Product --------------------------------------------------------

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Product with id " + id);

        if(productService.deleteProduct(BigDecimal.valueOf(id)))
            return new ResponseEntity<String>("Deleted Operator ["+id+"]",HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<String>("Unable to delete Operator with id [" + id + "] not found",HttpStatus.NO_CONTENT);
    }



}
