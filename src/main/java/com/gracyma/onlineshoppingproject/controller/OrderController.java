package com.gracyma.onlineshoppingproject.controller;


import com.gracyma.onlineshoppingproject.db.dao.OnlineShoppingCommodityDao;
import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingCommodity;
import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingOrder;
import com.gracyma.onlineshoppingproject.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    OnlineShoppingCommodityDao onlineShoppingCommodityDao;

    @GetMapping("/commodity/buy/{userId}/{commodityId}")
    public String createOrder(@PathVariable("userId") String userId,
                              @PathVariable("commodityId") String commodityId,
                              HashMap<String, Object> resultMap) {
        // check if stock >0, put check logic in service class
        // create a new order
              OnlineShoppingOrder order = orderService.placeOrderOriginal(userId, commodityId);
        if (order != null) {
            resultMap.put("resultInfo", "Create Order successfully! OrderNum:" + order.getOrderNo());
            resultMap.put("orderNo", order.getOrderNo());
        } else {
            resultMap.put("resultInfo", "Order create failed, check log for detail");
            resultMap.put("orderNo", "");
        }
        return "order_result";
    }
    @GetMapping("/commodity/orderQuery/{orderNo}") // get order detail
    public String orderQuery(@PathVariable("orderNo") String orderNo,
                             HashMap<String, Object> resultMap) {
        // according to orderNo to get order detail
        OnlineShoppingOrder order = orderService.getOrder(orderNo);
        OnlineShoppingCommodity commodity = onlineShoppingCommodityDao.getCommodityDetail(order.getCommodityId());
        resultMap.put("commodity", commodity);
        resultMap.put("order", order);
        return "order_check";

    }

}
