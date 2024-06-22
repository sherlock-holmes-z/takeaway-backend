package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    default Employee getByUsername(String username){
        Employee employee = new Employee();
        employee.setUsername(username);
        return select(employee).get(0);
    };

    List<Employee> select(Employee employee);

    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO pageQueryDTO);
}
