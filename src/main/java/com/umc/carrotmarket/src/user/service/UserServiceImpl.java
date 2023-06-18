package com.umc.carrotmarket.src.user.service;

import com.umc.carrotmarket.src.user.dto.*;
import com.umc.carrotmarket.src.user.UserDao;
import com.umc.carrotmarket.src.user.domain.User;
import com.umc.carrotmarket.util.JwtService;
import com.umc.carrotmarket.util.SHA256;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final JwtService jwtService;

    @Override
    public List<UserGetDto> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public UserGetDto findById(int idx) {
        try {
            UserGetDto getUserRes = userDao.findById(idx);
            return getUserRes;
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void createUser(UserCreateDto userInfo) {
        userDao.insert(userInfo.toUser());
    }

    @Override
    public void updateUserById(int idx, UserUpdateDto updateInfo) {
//        UserGetDto userGetDto = userDao.selectById(idx);

        if (updateInfo != null) {
            // 업데이트 내용
            userDao.update(idx, updateInfo);
        } else {
            throw new RuntimeException("수정사항이 없습니다");
        }
    }

    @Transactional
    @Override
    public void deleteUser(int idx) {
        if (userDao.findById(idx) != null) {
            userDao.delete(idx);
        } else {
            throw new RuntimeException("유저가 존재하지 않습니다");
        }
    }

    @Override
    public User findByUserId(String userId) {
        return userDao.findByUserId(userId).orElseThrow(() -> {
            throw new RuntimeException();
        });
    }

    @Transactional
    @Override
    public void updatePassword(int idx, PasswordUpdateDto input) {
        if (input.equals(input.getChange())) {
            throw new IllegalArgumentException();
        }
        userDao.updatePassword(idx, input);
    }

    @Override
    public User login(LoginDto loginInput) {
//        String encryptPwd;
//        try {
//            encryptPwd = new SHA256().encrypt(loginInput.getPassword());
//        } catch (Exception ignored) {
//            throw new RuntimeException("비밀번호 복호화에 실패하였습니다.");
//        }

        User user = userDao.findByUserId(loginInput.getUserId()).orElseThrow(() -> {
            throw new IllegalArgumentException("없는 아이디거나 비밀번호가 틀렸습니다.");
        });

//        Long userIdx = user.getIdx();
//        String jwt = jwtService.createJwt(userIdx);
//        return new PostLoginReq(userIdx, jwt);
        return user;
    }

    @Override
    public String findUserId(UserGetIdDto request) {
        return userDao.findIdByUserInput(request);
    }

    @Override
    public Boolean inputValidation(UserCreateDto userInput) {
        String userIdRegex = "^[a-zA-Z0-9]*$";
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{1,}$";

        return userInput.getUserId().matches(userIdRegex) &&
                userInput.getPassword().matches(passwordRegex);
    }
}
