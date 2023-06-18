package com.umc.carrotmarket.wishitem.dto;

import com.umc.carrotmarket.wishitem.domain.WishItem;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateWishItemDto {
    private Long productIdx;
    private Long userIdx;

    public WishItem toWishItem() {
        return WishItem.builder()
                .productIdx(productIdx)
                .userIdx(userIdx)
                .createAt(String.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .status("VALID")
                .build();
    }
}
