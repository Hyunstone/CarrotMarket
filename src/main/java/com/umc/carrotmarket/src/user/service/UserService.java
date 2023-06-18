package com.umc.carrotmarket.src.user.service;

import com.umc.carrotmarket.src.user.dto.*;
import com.umc.carrotmarket.src.user.domain.User;

import java.util.List;

public interface UserService {

    List<UserGetDto> selectAllUser();
    UserGetDto findById(int userId);
    void createUser(UserCreateDto userInfo);

    void updateUserById(int idx, UserUpdateDto updateInfo);

    void deleteUser(int idx);
    User findByUserId(String userId);

    void updatePassword(int idx, PasswordUpdateDto input);

    User login(LoginDto loginInput);

    String findUserId(UserGetIdDto request);

    Boolean inputValidation(UserCreateDto userInput);

}
