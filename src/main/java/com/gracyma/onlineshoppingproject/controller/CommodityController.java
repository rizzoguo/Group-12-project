package com.gracyma.onlineshoppingproject.controller;


import com.gracyma.onlineshoppingproject.db.dao.OnlineShoppingCommodityDao;
import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingCommodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class CommodityController {
    @Resource OnlineShoppingCommodityDao dao;
    //查看商品列表
    @RequestMapping("/listItems/{sellerId}")
    public String listItems( @PathVariable("sellerId") String sellerId,
                             Map<String, Object> resultMap ) {
        List<OnlineShoppingCommodity> onlineShoppingCommodities = dao.listCommoditiesByUserId(Long.parseLong(sellerId));
        resultMap.put("itemList", onlineShoppingCommodities);
        return "list_items";
    }

    //查看商品详情
    @RequestMapping("/item/{commodityId}")
    public String itemPage( @PathVariable("commodityId") long commodityId,
                            Map<String, Object> resultMap ) {
        OnlineShoppingCommodity onlineShoppingCommodity = dao.getCommodityDetail(commodityId);
        resultMap.put("commodity", onlineShoppingCommodity);
        return "item_detail";
    }

    @RequestMapping("/addItem")
    public String addCommodity() {
        return "add_commodity";
    }
//    @RequestMapping("/addItemAction")
//    public String addItemAction(
//            @RequestParam("commodityId") long commodityId,
//            @RequestParam("commodityName") String commodityName,
//            @RequestParam("commodityDesc") String commodityDesc,
//            @RequestParam("price") int price,
//            @RequestParam("availableStock") int availableStock,
//            @RequestParam("creatorUserId") long creatorUserId,
//            Map<String, Object> resultMap ) {
//        OnlineShoppingCommodity onlineShoppingCommodity =
//                OnlineShoppingCommodity.builder()
//                        .commodityId(commodityId)
//                        .price(price)
//                        .commodityDesc(commodityDesc)
//                        .commodityName(commodityName)
//                        .creatorUserId(creatorUserId)
//                        .availableStock(availableStock)
//                        .build();
//        dao.insertCommodity(onlineShoppingCommodity);
//        resultMap.put("Item", onlineShoppingCommodity);
//        return "add_commodity_success"; }
}

