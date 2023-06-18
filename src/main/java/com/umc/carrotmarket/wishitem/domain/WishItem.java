package com.umc.carrotmarket.wishitem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WishItem {
    private Long idx;
    private Long productIdx;
    private Long userIdx;
    private String createAt;
    private String updateAt; // nullable
    private String status;
}
