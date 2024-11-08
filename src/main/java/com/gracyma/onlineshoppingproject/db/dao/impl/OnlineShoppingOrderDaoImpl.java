package com.gracyma.onlineshoppingproject.db.dao.impl;

import com.gracyma.onlineshoppingproject.db.dao.OnlineShoppingOrderDao;
import com.gracyma.onlineshoppingproject.db.mappers.OnlineShoppingOrderMapper;
import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OnlineShoppingOrderDaoImpl implements OnlineShoppingOrderDao {
    @Resource
    OnlineShoppingOrderMapper onlineShoppingOrderMapper;

    @Override
    public int insertOrder(OnlineShoppingOrder order) {
        return onlineShoppingOrderMapper.insert(order);

    }

    @Override
    public OnlineShoppingOrder getOrderDetailByOrderNo(String orderNo) {
        return onlineShoppingOrderMapper.selectByOrderNo(orderNo);
    }

    @Override
    public int updateOrder(OnlineShoppingOrder order) {
        return onlineShoppingOrderMapper.updateByPrimaryKey(order);
    }
}

