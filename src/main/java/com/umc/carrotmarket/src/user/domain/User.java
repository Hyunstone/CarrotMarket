package com.umc.carrotmarket.src.user.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    private Long idx;
    private String name;
    private String userId;
    private String password;
    private String profile_image;
    private String home;
    private String createdAt;
    private String updatedAt;
    private String status;

}
