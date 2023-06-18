package com.umc.carrotmarket.src.product;

import com.umc.carrotmarket.src.product.domain.Product;
import com.umc.carrotmarket.src.product.dto.ProductCreateDto;
import com.umc.carrotmarket.src.product.dto.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductCreateDto productCreateDto);

    Product getProductById(Long idx);

    List<Product> getProducts(int cursor, int size);

    void deleteProduct(Long idx);

    void updateProduct(ProductUpdateDto productUpdateDto);
}
