package com.openwarehouse.api.service;

import com.openwarehouse.warehouse.entity.Material;
import com.openwarehouse.warehouse.service.MaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 物料服务单元测试
 */
@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @Test
    public void testCreateMaterial() {
        Material material = new Material();
        material.setMaterialCode("TEST001");
        material.setMaterialName("测试物料");
        material.setUnit("个");
        material.setStatus(1);

        boolean result = materialService.save(material);
        assertTrue(result);
        assertNotNull(material.getId());
    }

    @Test
    public void testUpdateMaterial() {
        Material material = materialService.lambdaQuery()
                .eq(Material::getMaterialCode, "TEST001")
                .one();

        if (material != null) {
            material.setMaterialName("测试物料-更新");
            boolean result = materialService.updateById(material);
            assertTrue(result);
        }
    }

    @Test
    public void testDeleteMaterial() {
        Material material = materialService.lambdaQuery()
                .eq(Material::getMaterialCode, "TEST001")
                .one();

        if (material != null) {
            boolean result = materialService.removeById(material.getId());
            assertTrue(result);
        }
    }
}
