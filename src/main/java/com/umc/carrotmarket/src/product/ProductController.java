package com.umc.carrotmarket.src.product;

import com.umc.carrotmarket.src.product.domain.Product;
import com.umc.carrotmarket.src.product.dto.ProductCreateDto;
import com.umc.carrotmarket.src.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        productService.createProduct(productCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/{idx}")
    public ResponseEntity<Product> getProductById(@PathVariable Long idx) {
        Product product = productService.getProductById(idx);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("cursor") int cursor, @RequestParam int size) {
        List<Product> productsList = productService.getProducts(cursor, size);
        return ResponseEntity.ok().body(productsList);
    }

    @PostMapping("/products/{idx}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        productService.updateProduct(productUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products/{idx}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long idx) {
        productService.deleteProduct(idx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
