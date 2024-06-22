package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> select(Employee employee);


    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    default Employee getByUsername(String username) {
        Employee employee = new Employee();
        employee.setUsername(username);
        List<Employee> list = select(employee);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    default Employee getById(Long id){
        Employee employee = new Employee();
        employee.setId(id);
        List<Employee> list = select(employee);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO pageQueryDTO);

    void updateById(Employee employee);
}
