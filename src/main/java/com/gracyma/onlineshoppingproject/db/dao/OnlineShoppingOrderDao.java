package com.gracyma.onlineshoppingproject.db.dao;


import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingOrder;

public interface OnlineShoppingOrderDao {
    int insertOrder(OnlineShoppingOrder order);

    OnlineShoppingOrder getOrderDetailByOrderNo(String orderNo);

    int updateOrder(OnlineShoppingOrder order);
}
