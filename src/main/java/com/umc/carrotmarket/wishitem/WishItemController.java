package com.umc.carrotmarket.wishitem;

import com.umc.carrotmarket.wishitem.domain.WishItem;
import com.umc.carrotmarket.wishitem.dto.CreateWishItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishItemController {

    private WishItemService wishItemService;

    @PostMapping("/wish-item")
    public ResponseEntity<Object> createWishItem(@RequestBody CreateWishItemDto createWishItemDto) {
        wishItemService.createWishItem(createWishItemDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/wish-item")
    public ResponseEntity<List<WishItem>> getWishItemList(@RequestParam Long userIdx, @RequestParam("cursor") int cursor, @RequestParam int size) {
        List<WishItem> wishItemList = wishItemService.getWishItems(userIdx, cursor, size);
        return ResponseEntity.ok().body(wishItemList);
    }

    @DeleteMapping("/wish-item/{idx}")
    public ResponseEntity<Object> deleteWishItem(@RequestParam Long idx) {
        wishItemService.deleteWishItem(idx);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
