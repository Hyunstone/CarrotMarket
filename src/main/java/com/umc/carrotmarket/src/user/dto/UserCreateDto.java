package com.umc.carrotmarket.src.user.dto;

import com.umc.carrotmarket.src.user.domain.User;
import lombok.Data;

@Data
public class UserCreateDto {
    private String userId;
    private String name;
    private String password;
    private String profile_image;
    private String home;

    public User toUser() {
        return User.builder()
                .userId(userId)
                .name(name)
                .password(password)
                .profile_image(profile_image)
                .home(home)
                .status("VALID")
                .build();
    }
}

