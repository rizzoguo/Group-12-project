package com.gracyma.onlineshoppingproject;

import com.gracyma.onlineshoppingproject.db.dao.OnlineShoppingCommodityDao;
import com.gracyma.onlineshoppingproject.db.dao.OnlineShoppingOrderDao;
import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingCommodity;
import com.gracyma.onlineshoppingproject.db.po.OnlineShoppingOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OnlineShoppingCommodityDao commodityDao;

    @Mock
    private OnlineShoppingOrderDao orderDao;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testPlaceOrderOriginal_WithAvailableStock() {
        // Arrange
        String userId = "123";
        String commodityId = "1";
        OnlineShoppingCommodity commodity = new OnlineShoppingCommodity();
        commodity.setCommodityId(1L);
        commodity.setAvailableStock(10);
        commodity.setLockStock(0);

        when(commodityDao.getCommodityDetail(Long.parseLong(commodityId))).thenReturn(commodity);
        
        // Act
        OnlineShoppingOrder result = orderService.placeOrderOriginal(userId, commodityId);

        // Assert
        assertNotNull(result, "Order should not be null");
        assertEquals(1L, result.getCommodityId());
        assertEquals(Long.parseLong(userId), result.getUserId());
        verify(commodityDao).updateCommodity(commodity);
        verify(orderDao).insertOrder(any(OnlineShoppingOrder.class));
    }

    @Test
    public void testPlaceOrderOriginal_WithoutAvailableStock() {
        // Arrange
        String userId = "123";
        String commodityId = "1";
        OnlineShoppingCommodity commodity = new OnlineShoppingCommodity();
        commodity.setCommodityId(1L);
        commodity.setAvailableStock(0);
        commodity.setLockStock(0);

        when(commodityDao.getCommodityDetail(Long.parseLong(commodityId))).thenReturn(commodity);
        
        // Act
        OnlineShoppingOrder result = orderService.placeOrderOriginal(userId, commodityId);

        // Assert
        assertNull(result, "Order should be null due to no stock available");
        verify(commodityDao, never()).updateCommodity(any(OnlineShoppingCommodity.class));
        verify(orderDao, never()).insertOrder(any(OnlineShoppingOrder.class));
    }
}
