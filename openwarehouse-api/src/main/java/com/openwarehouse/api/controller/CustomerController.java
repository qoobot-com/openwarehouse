package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.warehouse.entity.Customer;
import com.openwarehouse.warehouse.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 客户Controller
 */
@Tag(name = "客户管理")
@RestController
@RequestMapping("/api/warehouse/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 分页查询客户列表
     */
    @Operation(summary = "分页查询客户列表")
    @GetMapping("/page")
    public Result<Page<Customer>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            Customer customer) {
        Page<Customer> page = new Page<>(current, size);
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        if (customer.getCustomerCode() != null) {
            queryWrapper.like("customer_code", customer.getCustomerCode());
        }
        if (customer.getCustomerName() != null) {
            queryWrapper.like("customer_name", customer.getCustomerName());
        }
        if (customer.getCustomerType() != null) {
            queryWrapper.eq("customer_type", customer.getCustomerType());
        }
        if (customer.getStatus() != null) {
            queryWrapper.eq("status", customer.getStatus());
        }
        queryWrapper.orderByDesc("create_time");
        return Result.success(customerService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询客户
     */
    @Operation(summary = "根据ID查询客户")
    @GetMapping("/{id}")
    public Result<Customer> getById(@PathVariable Long id) {
        return Result.success(customerService.getById(id));
    }

    /**
     * 新增客户
     */
    @Operation(summary = "新增客户")
    @PostMapping
    public Result<String> add(@RequestBody Customer customer) {
        customerService.save(customer);
        return Result.success("新增成功");
    }

    /**
     * 修改客户
     */
    @Operation(summary = "修改客户")
    @PutMapping
    public Result<String> update(@RequestBody Customer customer) {
        customerService.updateById(customer);
        return Result.success("修改成功");
    }

    /**
     * 删除客户
     */
    @Operation(summary = "删除客户")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        customerService.removeById(id);
        return Result.success("删除成功");
    }
}
