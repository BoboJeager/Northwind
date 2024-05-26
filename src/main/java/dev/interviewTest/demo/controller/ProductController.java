package dev.interviewTest.demo.controller;

import dev.interviewTest.demo.model.Product;
import dev.interviewTest.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Object> getAllProducts(){
        List<Product> products = productService.getAllProducts();

        if(products.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Long id){
        Optional<Product> product = productService.getProductById(id);

        if(!product.isPresent()){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@RequestBody Map<String, Object> productPayload){
        try {
            if (productPayload == null || !productPayload.containsKey("productName") || !productPayload.containsKey("unitPrice")) {
                return ResponseEntity.badRequest().body("Missing required product fields.");
            }

            Product product = new Product();
            product.setProductName(productPayload.get("productName").toString());
            product.setSupplierId(Integer.parseInt(productPayload.get("supplierId").toString()));
            product.setCategoryId(Integer.parseInt(productPayload.get("categoryId").toString()));
            product.setQuantityPerUnit(productPayload.get("quantityPerUnit").toString());
            product.setUnitPrice(Double.parseDouble(productPayload.get("unitPrice").toString()));
            product.setUnitsInStock(Integer.parseInt(productPayload.get("unitsInStock").toString()));
            product.setUnitsOnOrder(Integer.parseInt(productPayload.get("unitsOnOrder").toString()));
            product.setReorderLevel(Integer.parseInt(productPayload.get("reorderLevel").toString()));
            product.setDiscontinued(Boolean.parseBoolean(productPayload.get("discontinued").toString()));

            Product savedProduct = productService.createProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid data format");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating product: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody Map<String,Object> productDetails) {
        try {
            Product productToUpdate = convertMapToProduct(productDetails);
            Product updatedProduct = productService.updateProduct(id, productToUpdate);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product: " + e.getMessage());
        }
    }

    private Product convertMapToProduct(Map<String, Object> productDetails) {
        Product product = new Product();

        Optional.ofNullable(productDetails.get("productName")).ifPresent(v -> product.setProductName((String) v));
        Optional.ofNullable(productDetails.get("supplierId")).ifPresent(v -> product.setSupplierId((Integer) v));
        Optional.ofNullable(productDetails.get("categoryId")).ifPresent(v -> product.setCategoryId((Integer) v));
        Optional.ofNullable(productDetails.get("quantityPerUnit")).ifPresent(v -> product.setQuantityPerUnit((String) v));
        Optional.ofNullable(productDetails.get("unitPrice")).ifPresent(v -> product.setUnitPrice((Double) v));
        Optional.ofNullable(productDetails.get("unitsInStock")).ifPresent(v -> product.setUnitsInStock((Integer) v));
        Optional.ofNullable(productDetails.get("unitsOnOrder")).ifPresent(v -> product.setUnitsOnOrder((Integer) v));
        Optional.ofNullable(productDetails.get("reorderLevel")).ifPresent(v -> product.setReorderLevel((Integer) v));
        Optional.ofNullable(productDetails.get("discontinued")).ifPresent(v -> product.setDiscontinued((Boolean) v));

        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product: " + e.getMessage());
        }

    }




}

