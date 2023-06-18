package com.umc.carrotmarket.wishitem;

import com.umc.carrotmarket.wishitem.domain.WishItem;
import com.umc.carrotmarket.wishitem.dto.CreateWishItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishItemServiceImpl implements WishItemService{

    WishItemDao wishItemDao;

    @Override
    public void createWishItem(CreateWishItemDto createWishItemDto) {
        wishItemDao.createWishItem(createWishItemDto.toWishItem());
    }

    @Override
    public List<WishItem> getWishItems(Long userIdx, int cursor, int size) {
        return wishItemDao.getWishItems(userIdx, cursor, size);
    }

    @Override
    public void deleteWishItem(Long idx) {
        wishItemDao.deleteWishItem(idx);
    }
}
