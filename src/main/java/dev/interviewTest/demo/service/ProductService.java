package dev.interviewTest.demo.service;

import dev.interviewTest.demo.model.Product;
import dev.interviewTest.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }

    public Optional<Product>getProductById(Long id){
        return  productRepository.findById(id);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (productDetails.getProductName() != null) {
            existingProduct.setProductName(productDetails.getProductName());
        }
        if (productDetails.getSupplierId() != null) {
            existingProduct.setSupplierId(productDetails.getSupplierId());
        }
        if (productDetails.getCategoryId() != null) {
            existingProduct.setCategoryId(productDetails.getCategoryId());
        }
        if (productDetails.getQuantityPerUnit() != null) {
            existingProduct.setQuantityPerUnit(productDetails.getQuantityPerUnit());
        }
        if (productDetails.getUnitPrice() != null) {
            existingProduct.setUnitPrice(productDetails.getUnitPrice());
        }
        if (productDetails.getUnitsInStock() != null) {
            existingProduct.setUnitsInStock(productDetails.getUnitsInStock());
        }
        if (productDetails.getUnitsOnOrder() != null) {
            existingProduct.setUnitsOnOrder(productDetails.getUnitsOnOrder());
        }
        if (productDetails.getReorderLevel() != null) {
            existingProduct.setReorderLevel(productDetails.getReorderLevel());
        }
        if (productDetails.getDiscontinued() != null) {
            existingProduct.setDiscontinued(productDetails.getDiscontinued());
        }

        return productRepository.save(existingProduct);
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
