package com.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.warehouse.entity.Customer;
import com.openwarehouse.warehouse.mapper.CustomerMapper;
import com.openwarehouse.warehouse.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * 客户Service实现类
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
        implements CustomerService {
}
