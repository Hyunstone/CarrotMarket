package com.umc.carrotmarket.wishitem;

import com.umc.carrotmarket.wishitem.domain.WishItem;
import com.umc.carrotmarket.wishitem.dto.CreateWishItemDto;

import java.util.List;

public interface WishItemService {
    void createWishItem(CreateWishItemDto createWishItemDto);

    List<WishItem> getWishItems(Long userIdx, int cursor, int size);

    void deleteWishItem(Long idx);
}
