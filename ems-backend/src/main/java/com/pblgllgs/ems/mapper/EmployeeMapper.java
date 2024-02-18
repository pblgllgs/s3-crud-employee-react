package com.pblgllgs.ems.mapper;
/*
 *
 * @author pblgl
 * Created on 18-02-2024
 *
 */

import com.pblgllgs.ems.dto.EmployeeDto;
import com.pblgllgs.ems.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {

    public static EmployeeDto employeeToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;
    }

    public static Employee employeeDtoToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        return employee;
    }

}
