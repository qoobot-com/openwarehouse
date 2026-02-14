package com.qoobot.openwarehouse.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 编码生成工具类
 */
public class CodeUtil {

    private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(1, 1);

    /**
     * 生成物料编码
     */
    public static String generateMaterialCode() {
        return "MAT" + System.currentTimeMillis();
    }

    /**
     * 生成条码 (EAN13格式模拟)
     */
    public static String generateBarcode() {
        long id = SNOWFLAKE.nextId();
        return String.format("%013d", id % 1000000000000L);
    }

    /**
     * 生成二维码内容
     */
    public static String generateQrCode(String materialCode) {
        // 二维码包含物料编码和加密时间戳
        long timestamp = System.currentTimeMillis();
        return materialCode + "|" + timestamp;
    }

    /**
     * 解析二维码内容
     */
    public static String parseQrCode(String qrCode) {
        if (qrCode == null || !qrCode.contains("|")) {
            return qrCode;
        }
        return qrCode.split("\\|")[0];
    }

    /**
     * 生成仓库编码
     */
    public static String generateWarehouseCode() {
        return "WH" + System.currentTimeMillis();
    }

    /**
     * 生成库区编码
     */
    public static String generateZoneCode(String warehouseCode) {
        return warehouseCode + "-Z-" + System.currentTimeMillis();
    }

    /**
     * 生成货架编码
     */
    public static String generateShelfCode(String zoneCode) {
        return zoneCode + "-S-" + System.currentTimeMillis();
    }

    /**
     * 生成库位编码
     */
    public static String generateLocationCode(String shelfCode, int row, int column, int layer) {
        return shelfCode + "-" + row + "-" + column + "-" + layer;
    }

    /**
     * 生成供应商编码
     */
    public static String generateSupplierCode() {
        return "SUP" + System.currentTimeMillis();
    }

    /**
     * 生成分类编码
     */
    public static String generateCategoryCode(Long parentId) {
        String prefix = parentId == null ? "CAT" : "CAT-" + parentId;
        return prefix + "-" + System.currentTimeMillis();
    }
}
