package com.umc.carrotmarket.src.review;

import com.umc.carrotmarket.src.review.domain.Review;
import com.umc.carrotmarket.src.review.dto.UpdateReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createReview(Review review) {
        String createReviewQuery = "insert into " +
                "Review (buyerId, sellerId, `contents`, create_at, update_at, status) " +
                "VALUES (?,?,?,?,?,?)";
        Object[] createReviewParams = new Object[]{
                review.getBuyerId(), review.getSellerId(), review.getContents(),
                review.getCreateAt(), review.getUpdateAt(), review.getStatus()
        };
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public List<Review> getReviewList(Long productIdx) {
        String getReviewQuery = "SELECT * FROM Review WHERE idx = ?";
        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new Review(
                        rs.getLong("idx"),
                        rs.getString("seller_id"),
                        rs.getString("buyer_id"),
                        rs.getString("contents"),
                        rs.getString("create_at"),
                        rs.getString("update_at"),
                        rs.getString("status")),
                productIdx);
    }

    public void updateReview(UpdateReviewDto updateReviewDto) {
        String updateReviewQuery = "UPDATE Review SET contents = ? where idx = ?";
        this.jdbcTemplate.update(updateReviewQuery, updateReviewDto.getContents());
    }

    public void deleteReview(Long productIdx) {
        String deleteReviewQuery = "DELETE FROM Review WHERE idx = ?";
        this.jdbcTemplate.update(deleteReviewQuery, productIdx);
    }
}
