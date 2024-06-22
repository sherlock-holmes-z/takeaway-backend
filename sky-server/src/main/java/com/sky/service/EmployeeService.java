package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void saveEmployee(EmployeeDTO employeeDTO);

    PageResult queryPage(EmployeePageQueryDTO pageQueryDTO);

    void updateStatus(Long id, Integer status);

    Employee getEmployee(Long id);

    void updateById(Employee employee);



//    PageResult<Employee> queryPage(EmployeePageQueryDTO pageQueryDTO);
//
//    void updateStatus(Long id, Integer status);

}
