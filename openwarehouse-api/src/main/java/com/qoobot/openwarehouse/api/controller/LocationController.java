package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.Location;
import com.qoobot.openwarehouse.warehouse.service.ILocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 库位管理控制器
 */
@Slf4j
@Tag(name = "库位管理", description = "库位管理相关接口")
@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final ILocationService locationService;

    @Operation(summary = "分页查询库位列表", description = "分页查询库位列表")
    @GetMapping("/page")
    public Result<Page<Location>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId,
            @Parameter(description = "库区ID") @RequestParam(required = false) Long zoneId,
            @Parameter(description = "货架ID") @RequestParam(required = false) Long shelfId,
            @Parameter(description = "库位编码") @RequestParam(required = false) String locationCode) {

        Page<Location> page = new Page<>(current, size);
        Page<Location> result = locationService.lambdaQuery()
                .eq(warehouseId != null, Location::getWarehouseId, warehouseId)
                .eq(zoneId != null, Location::getZoneId, zoneId)
                .eq(shelfId != null, Location::getShelfId, shelfId)
                .like(locationCode != null, Location::getLocationCode, locationCode)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "根据货架查询库位", description = "根据货架ID查询所有库位")
    @GetMapping("/list")
    public Result<java.util.List<Location>> list(
            @Parameter(description = "货架ID") @RequestParam(required = false) Long shelfId) {

        return Result.success(locationService.lambdaQuery()
                .eq(shelfId != null, Location::getShelfId, shelfId)
                .eq(Location::getStatus, 1)
                .list());
    }

    @Operation(summary = "根据ID查询库位", description = "根据ID查询库位详情")
    @GetMapping("/{id}")
    public Result<Location> getById(@Parameter(description = "库位ID") @PathVariable Long id) {
        Location location = locationService.getById(id);
        return location != null ? Result.success(location) : Result.error("库位不存在");
    }

    @Operation(summary = "创建库位", description = "创建新库位")
    @PostMapping
    public Result<Void> create(@RequestBody Location location) {
        boolean success = locationService.save(location);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新库位", description = "更新库位信息")
    @PutMapping
    public Result<Void> update(@RequestBody Location location) {
        boolean success = locationService.updateById(location);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除库位", description = "根据ID删除库位")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "库位ID") @PathVariable Long id) {
        boolean success = locationService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
