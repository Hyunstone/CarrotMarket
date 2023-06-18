package com.umc.carrotmarket.src.product;

import com.umc.carrotmarket.src.product.domain.Product;
import com.umc.carrotmarket.src.product.dto.ProductCreateDto;
import com.umc.carrotmarket.src.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Override
    public void createProduct(ProductCreateDto productCreateDto) {
        productDao.createProduct(productCreateDto.toProduct());
    }

    @Override
    public Product getProductById(Long idx) {
        productDao.updateClicks(idx);
        return productDao.getProductById(idx);
    }

    @Override
    public List<Product> getProducts(int cursor, int size) {
        return productDao.getProducts(cursor, size);
    }

    @Override
    public void updateProduct(ProductUpdateDto productUpdateDto) {
        productDao.updateProduct(productUpdateDto);
    }

    @Override
    public void deleteProduct(Long idx) {
        productDao.delete(idx);
    }

}
