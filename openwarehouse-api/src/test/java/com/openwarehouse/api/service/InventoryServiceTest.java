package com.openwarehouse.api.service;

import com.openwarehouse.inventory.entity.Inventory;
import com.openwarehouse.inventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 库存服务单元测试
 */
@SpringBootTest
public class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testGetInventoryById() {
        Inventory inventory = inventoryService.getById(1L);
        assertNotNull(inventory);
        assertEquals(1L, inventory.getId());
    }

    @Test
    public void testUpdateInventoryQuantity() {
        Inventory inventory = inventoryService.getById(1L);
        if (inventory != null) {
            Double originalQuantity = inventory.getQuantity();
            inventory.setQuantity(originalQuantity + 10);
            boolean result = inventoryService.updateById(inventory);
            assertTrue(result);
        }
    }

    @Test
    public void testInventoryList() {
        inventoryService.list().forEach(inventory -> {
            assertNotNull(inventory.getWarehouseId());
            assertNotNull(inventory.getMaterialId());
        });
    }
}
