package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.Shelf;
import com.qoobot.openwarehouse.warehouse.service.IShelfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 货架管理控制器
 */
@Slf4j
@Tag(name = "货架管理", description = "货架管理相关接口")
@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
public class ShelfController {

    private final IShelfService shelfService;

    @Operation(summary = "分页查询货架列表", description = "分页查询货架列表")
    @GetMapping("/page")
    public Result<Page<Shelf>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId,
            @Parameter(description = "库区ID") @RequestParam(required = false) Long zoneId,
            @Parameter(description = "货架名称") @RequestParam(required = false) String shelfName) {

        Page<Shelf> page = new Page<>(current, size);
        Page<Shelf> result = shelfService.lambdaQuery()
                .eq(warehouseId != null, Shelf::getWarehouseId, warehouseId)
                .eq(zoneId != null, Shelf::getZoneId, zoneId)
                .like(shelfName != null, Shelf::getShelfName, shelfName)
                .orderByAsc(Shelf::getSort)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "根据库区查询货架", description = "根据库区ID查询所有货架")
    @GetMapping("/list")
    public Result<java.util.List<Shelf>> list(
            @Parameter(description = "库区ID") @RequestParam(required = false) Long zoneId) {

        return Result.success(shelfService.lambdaQuery()
                .eq(zoneId != null, Shelf::getZoneId, zoneId)
                .eq(Shelf::getStatus, 1)
                .orderByAsc(Shelf::getSort)
                .list());
    }

    @Operation(summary = "根据ID查询货架", description = "根据ID查询货架详情")
    @GetMapping("/{id}")
    public Result<Shelf> getById(@Parameter(description = "货架ID") @PathVariable Long id) {
        Shelf shelf = shelfService.getById(id);
        return shelf != null ? Result.success(shelf) : Result.error("货架不存在");
    }

    @Operation(summary = "创建货架", description = "创建新货架")
    @PostMapping
    public Result<Void> create(@RequestBody Shelf shelf) {
        boolean success = shelfService.save(shelf);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新货架", description = "更新货架信息")
    @PutMapping
    public Result<Void> update(@RequestBody Shelf shelf) {
        boolean success = shelfService.updateById(shelf);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除货架", description = "根据ID删除货架")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "货架ID") @PathVariable Long id) {
        boolean success = shelfService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
