package com.umc.carrotmarket.wishitem;

import com.umc.carrotmarket.wishitem.domain.WishItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WishItemDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createWishItem(WishItem wishItem) {
        String createWishItemQuery = "INSERT INTO " +
                "WishItem (productIdx, userIdx, create_at, update_at, status) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] createWishItemParams = new Object[]{
                wishItem.getProductIdx(), wishItem.getUserIdx(), wishItem.getCreateAt(),
                wishItem.getUpdateAt(), wishItem.getStatus()
        };
        this.jdbcTemplate.update(createWishItemQuery, createWishItemParams);

        String lastInsertIdQuery = "select last_insert_id()";
        this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }


    public List<WishItem> getWishItems(Long userIdx, int cursor, int size) {
        String getWishItemQuery = "SELECT * FROM WishItem LIMIT ? OFFSET ? WHERE userIdx = ?";
        Object[] getWishItemParams = new Object[]{ size, cursor, userIdx };
        return this.jdbcTemplate.query(getWishItemQuery,
                (rs, rowNum) -> new WishItem(
                        rs.getLong("idx"),
                        rs.getLong("productIdx"),
                        rs.getLong("userIdx"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status")
                ),
                getWishItemParams);
    }

    public void deleteWishItem(Long idx) {
        String deleteWishItemQuery = "DELETE FROM WishItem WHERE idx = ?";
        this.jdbcTemplate.update(deleteWishItemQuery, idx);
    }
}
