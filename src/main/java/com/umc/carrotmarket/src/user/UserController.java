package com.umc.carrotmarket.src.user;

import com.umc.carrotmarket.src.user.domain.User;
import com.umc.carrotmarket.src.user.dto.*;
import com.umc.carrotmarket.src.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    UserServiceImpl userService;

    /**
     * 회원가입 API
     * [POST] /users
     */
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody UserCreateDto userInfo) {
        userService.createUser(userInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원가입 정보 유효성 검사 API
     * [POST] /users/validation
     */
    @PostMapping("/users/validation")
    public ResponseEntity<Boolean> inputValidation(@RequestBody UserCreateDto userInput) {
        return new ResponseEntity<>(userService.inputValidation(userInput), HttpStatus.OK);
    }

    /**
     * 회원 조회 API
     * [GET] /users/all
     */
    @GetMapping("/users/all")
    public ResponseEntity<List<UserGetDto>> getAllUser() {
        List<UserGetDto> users = userService.selectAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/{idx}
     */
    @GetMapping("/users/{idx}")
    public ResponseEntity<UserGetDto> getUser(@PathVariable int idx) {
        return new ResponseEntity<>(userService.findById(idx), HttpStatus.OK);
    }

    /**
     * 회원 아이디 찾기 API
     * [POST] /users/id
     */
    @PostMapping({"/users/id"})
    public ResponseEntity<String> getUserId(@RequestBody UserGetIdDto request) {
        return new ResponseEntity<>(userService.findUserId(request), HttpStatus.OK);
    }

    /**
     * 회원 정보 수정 API
     * [POST] /users/{idx}/update
     */
    @PostMapping("/users/{idx}/update")
    public ResponseEntity<Object> updateUserInfo(@PathVariable int idx, @RequestBody UserUpdateDto updateInfo) {
        userService.updateUserById(idx, updateInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원 비밀번호 수정 API
     * [POST] /users/{idx}/password
     */
    @PostMapping("/users/{idx}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable int idx, @RequestBody PasswordUpdateDto request) {
        userService.updatePassword(idx, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원 삭제 API
     * [DELETE] /users/{idx}/delete
     */
    @DeleteMapping({"/users/{idx}/delete"})
    public ResponseEntity<Object> delete(@PathVariable int idx) {
        userService.deleteUser(idx);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원 로그인 API
     * [POST] /users/login
     */
    @PostMapping("/users/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginInput) {
        try {
            User loginRes = userService.login(loginInput);
            return new ResponseEntity<>(loginRes, HttpStatus.OK);
//            return new ResponseEntity<>(loginRes, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원 로그아웃 API
     * [POST] /users/login
     */
    @PostMapping("/users/logout")
    public ResponseEntity<Object> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
