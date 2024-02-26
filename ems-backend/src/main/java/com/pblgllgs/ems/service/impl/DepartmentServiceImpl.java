package com.pblgllgs.ems.service.impl;
/*
 *
 * @author pblgl
 * Created on 25-02-2024
 *
 */

import com.pblgllgs.ems.dto.DepartmentDto;
import com.pblgllgs.ems.entity.Department;
import com.pblgllgs.ems.exception.ResourceNotFoundException;
import com.pblgllgs.ems.mapper.DepartmentMapper;
import com.pblgllgs.ems.repository.DepartmentRepository;
import com.pblgllgs.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Value("${message.exceptions.department.not-found}")
    private String message;

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.departmentDtoToDepartment(departmentDto);
        Department departmentSaved = departmentRepository.save(department);
        return DepartmentMapper.departmentToDepartmentDto(departmentSaved);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException(message + departmentId)
        );
        return DepartmentMapper.departmentToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository
                .findAll()
                .stream()
                .map(DepartmentMapper::departmentToDepartmentDto)
                .toList();
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException((message + departmentId))
        );
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department departmentUpdated = departmentRepository.save(department);
        return DepartmentMapper.departmentToDepartmentDto(departmentUpdated);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException((message + departmentId))
        );
        departmentRepository.deleteById(department.getId());
    }
}
