package com.pblgllgs.ems.service.impl;
/*
 *
 * @author pblgl
 * Created on 18-02-2024
 *
 */

import com.pblgllgs.ems.dto.EmployeeDto;
import com.pblgllgs.ems.entity.Employee;
import com.pblgllgs.ems.exception.ResourceNotFoundException;
import com.pblgllgs.ems.mapper.EmployeeMapper;
import com.pblgllgs.ems.repository.EmployeeRepository;
import com.pblgllgs.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.employeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(
                employeeId).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " + employeeId + " not found")
        );
        return EmployeeMapper.employeeToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(
                        EmployeeMapper::employeeToEmployeeDto
                ).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(
                employeeId).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " + employeeId + " not found")
        );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(
                employeeId).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " + employeeId + " not found")
        );
        employeeRepository.deleteById(employee.getId());
    }
}
