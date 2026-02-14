package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.Zone;
import com.qoobot.openwarehouse.warehouse.service.IZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 库区管理控制器
 */
@Slf4j
@Tag(name = "库区管理", description = "库区管理相关接口")
@RestController
@RequestMapping("/zone")
@RequiredArgsConstructor
public class ZoneController {

    private final IZoneService zoneService;

    @Operation(summary = "分页查询库区列表", description = "分页查询库区列表")
    @GetMapping("/page")
    public Result<Page<Zone>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId,
            @Parameter(description = "库区名称") @RequestParam(required = false) String zoneName) {

        Page<Zone> page = new Page<>(current, size);
        Page<Zone> result = zoneService.lambdaQuery()
                .eq(warehouseId != null, Zone::getWarehouseId, warehouseId)
                .like(zoneName != null, Zone::getZoneName, zoneName)
                .orderByAsc(Zone::getSort)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "根据仓库查询库区", description = "根据仓库ID查询所有库区")
    @GetMapping("/list")
    public Result<java.util.List<Zone>> list(
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId) {

        return Result.success(zoneService.lambdaQuery()
                .eq(warehouseId != null, Zone::getWarehouseId, warehouseId)
                .eq(Zone::getStatus, 1)
                .orderByAsc(Zone::getSort)
                .list());
    }

    @Operation(summary = "根据ID查询库区", description = "根据ID查询库区详情")
    @GetMapping("/{id}")
    public Result<Zone> getById(@Parameter(description = "库区ID") @PathVariable Long id) {
        Zone zone = zoneService.getById(id);
        return zone != null ? Result.success(zone) : Result.error("库区不存在");
    }

    @Operation(summary = "创建库区", description = "创建新库区")
    @PostMapping
    public Result<Void> create(@RequestBody Zone zone) {
        boolean success = zoneService.save(zone);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新库区", description = "更新库区信息")
    @PutMapping
    public Result<Void> update(@RequestBody Zone zone) {
        boolean success = zoneService.updateById(zone);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除库区", description = "根据ID删除库区")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "库区ID") @PathVariable Long id) {
        boolean success = zoneService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
