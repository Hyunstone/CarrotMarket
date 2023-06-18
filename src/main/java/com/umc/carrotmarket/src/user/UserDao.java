package com.umc.carrotmarket.src.user;

import com.umc.carrotmarket.src.user.dto.PasswordUpdateDto;
import com.umc.carrotmarket.src.user.dto.UserGetIdDto;
import com.umc.carrotmarket.src.user.dto.UserUpdateDto;
import com.umc.carrotmarket.src.user.domain.User;
import com.umc.carrotmarket.src.user.dto.UserGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserGetDto findById(int idx) {
        String getUserQuery = "select * from User where idx = ?";
        int getUserParams = idx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new UserGetDto(
                        rs.getInt("idx"),
                        rs.getString("name"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("profile_image"),
                        rs.getString("home")),
                getUserParams);
    }

    public Integer insert(User user) {
        String createUserQuery = "insert into User (idx, userId, name, password, profile_image, home, createdAt, updatedAt, status) VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{user.getIdx(), user.getUserId(), user.getName(), user.getPassword(),
                null, user.getHome(), Timestamp.valueOf(LocalDateTime.now()), null, user.getStatus()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    @Transactional
    public int update(int idx, UserUpdateDto updateInfo) {
        String modifyUserQuery = "UPDATE User SET name = ?, profile_image = ?, home = ? WHERE idx = ?";
        Object[] modifyUserNameParams = new Object[]{updateInfo.getName(), updateInfo.getProfile_image(), updateInfo.getHome(), idx};

        return this.jdbcTemplate.update(modifyUserQuery,modifyUserNameParams);
    }

    public int delete(int idx) {
        String deleteUserQuery = "DELETE FROM User WHERE idx = ?";
        Object[] deleteUserParams = new Object[]{idx};

        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }

    public List<UserGetDto> selectAllUser() {
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new UserGetDto(
                        rs.getInt("idx"),
                        rs.getString("name"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("profile_image"),
                        rs.getString("home"))
                );
    }

    public Optional<User> findByUserId(String userId) {
        String getUsersQuery = "select * from User where userId = ?";
        String getUserParams = userId;
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(getUsersQuery,
                (rs, rowNum) -> new User(
                        rs.getLong("idx"),
                        rs.getString("name"),
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("profile_image"),
                        rs.getString("home"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getString("status")),
                getUserParams));

    }

    public int updatePassword(int idx, PasswordUpdateDto input) {
        String modifyUserPasswordQuery = "UPDATE User SET password = ? WHERE idx = ?";
        Object[] modifyUserPasswordParams = new Object[]{input.getChange(), idx};
        return this.jdbcTemplate.update(modifyUserPasswordQuery, modifyUserPasswordParams);
    }

    public String findIdByUserInput(UserGetIdDto request) {
        String getUsersQuery = "select userId from User where name = ?";
        Object[] getUserParams = new Object[]{request.getName()};

        try {
            return this.jdbcTemplate.queryForObject(getUsersQuery, getUserParams, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


//    private RowMapper<User> memberRowMapper() {
//        return (rs, rowNum) -> {
//            User member = new User();
//            member.setCreatedAt(rs.getTimestamp("createdAt"));
//            member.setId(rs.getString("id"));
//            member.setPassword(rs.getString("password"));
//            member.setName(rs.getString("name"));
//            return member;
//        };
//    }

}
