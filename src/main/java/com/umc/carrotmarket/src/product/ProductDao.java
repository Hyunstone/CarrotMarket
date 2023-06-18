package com.umc.carrotmarket.src.product;

import com.umc.carrotmarket.src.product.domain.Product;
import com.umc.carrotmarket.src.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createProduct(Product product) {
        String createProductQuery = "insert into " +
                "Product (seller_id, buyer_id, `name`, create_at, update_at, clicks, contents, status, home) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] createProductParams = new Object[]{
                 product.getSellerId(), product.getBuyerId(), product.getName(),product.getCreateAt(), product.getUpdateAt(),
                product.getClicks(), product.getContents(), product.getStatus(), product.getHome()
        };
        this.jdbcTemplate.update(createProductQuery, createProductParams);

        String lastInsertIdQuery = "select last_insert_id()";
        this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public Product getProductById(Long productId) {
        String getProductQuery = "SELECT * from Product where idx = ?";
        return this.jdbcTemplate.queryForObject(getProductQuery,
                (rs, rowNum) -> new Product(
                        rs.getLong("idx"),
                        rs.getString("seller_id"),
                        rs.getString("buyer_id"),
                        rs.getString("name"),
                        rs.getString("create_at"),
                        rs.getString("update_at"),
                        rs.getInt("clicks"),
                        rs.getString("contents"),
                        rs.getString("status"),
                        rs.getString("home")),
                productId);
    }

    public List<Product> getProducts(int cursor, int size) {
        String getProductsQuery = "SELECT * FROM Product LIMIT ? OFFSET ?";
        Object[] getProductsParams = new Object[]{ size, cursor };

        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new Product(
                        rs.getLong("idx"),
                        rs.getString("seller_id"),
                        rs.getString("buyer_id"),
                        rs.getString("name"),
                        rs.getString("create_at"),
                        rs.getString("update_at"),
                        rs.getInt("clicks"),
                        rs.getString("contents"),
                        rs.getString("status"),
                        rs.getString("home")),
                getProductsParams);
    }

    public void delete(Long idx) {
        String deleteProductQuery = "DELETE FROM PRODUCT WHERE idx = ?";
        this.jdbcTemplate.update(deleteProductQuery, idx);
    }

    public void updateProduct(ProductUpdateDto productUpdateDto) {
        String updateProductQuery = "UPDATE Product SET name = ? contents = ? WHERE idx = ?";
        Object updateProductParams = new Object[]{productUpdateDto.getName(), productUpdateDto.getContents(), productUpdateDto.getIdx()};
        this.jdbcTemplate.update(updateProductQuery, updateProductParams);
    }

    public void updateClicks(Long idx) {
        String updateProductQuery = "UPDATE Product SET clicks = ? WHERE idx = ?";
        String productClickQuery = "SELECT clicks = ? FROM Product WHERE idx = ?";
        this.jdbcTemplate.queryForObject(productClickQuery, int.class);
        Object[] updateProductParams = new Object[] {
                this.jdbcTemplate.queryForObject(productClickQuery, int.class) + 1, idx
        };
        this.jdbcTemplate.update(updateProductQuery, updateProductParams);
    }
}
