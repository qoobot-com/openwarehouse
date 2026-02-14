package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.Supplier;
import com.qoobot.openwarehouse.warehouse.service.ISupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商管理控制器
 */
@Slf4j
@Tag(name = "供应商管理", description = "供应商管理相关接口")
@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final ISupplierService supplierService;

    @Operation(summary = "分页查询供应商列表", description = "分页查询供应商列表")
    @GetMapping("/page")
    public Result<Page<Supplier>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "供应商名称") @RequestParam(required = false) String supplierName,
            @Parameter(description = "供应商编码") @RequestParam(required = false) String supplierCode) {

        Page<Supplier> page = new Page<>(current, size);
        Page<Supplier> result = supplierService.lambdaQuery()
                .like(supplierName != null, Supplier::getSupplierName, supplierName)
                .like(supplierCode != null, Supplier::getSupplierCode, supplierCode)
                .orderByDesc(Supplier::getCreateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "查询所有供应商", description = "查询所有供应商列表")
    @GetMapping("/list")
    public Result<java.util.List<Supplier>> list() {
        return Result.success(supplierService.lambdaQuery()
                .eq(Supplier::getStatus, 1)
                .list());
    }

    @Operation(summary = "根据ID查询供应商", description = "根据ID查询供应商详情")
    @GetMapping("/{id}")
    public Result<Supplier> getById(@Parameter(description = "供应商ID") @PathVariable Long id) {
        Supplier supplier = supplierService.getById(id);
        return supplier != null ? Result.success(supplier) : Result.error("供应商不存在");
    }

    @Operation(summary = "创建供应商", description = "创建新供应商")
    @PostMapping
    public Result<Void> create(@RequestBody Supplier supplier) {
        boolean success = supplierService.save(supplier);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新供应商", description = "更新供应商信息")
    @PutMapping
    public Result<Void> update(@RequestBody Supplier supplier) {
        boolean success = supplierService.updateById(supplier);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除供应商", description = "根据ID删除供应商")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "供应商ID") @PathVariable Long id) {
        boolean success = supplierService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
