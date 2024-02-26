package com.pblgllgs.ems.mapper;
/*
 *
 * @author pblgl
 * Created on 18-02-2024
 *
 */

import com.pblgllgs.ems.dto.DepartmentDto;
import com.pblgllgs.ems.entity.Department;
import org.springframework.beans.BeanUtils;

public class DepartmentMapper {

    public static DepartmentDto departmentToDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        BeanUtils.copyProperties(department, departmentDto);
        return departmentDto;
    }

    public static Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        return department;
    }

}
