package com.pblgllgs.ems.controller;

import com.pblgllgs.ems.dto.DepartmentDto;
import com.pblgllgs.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *
 * @author pblgl
 * Created on 25-02-2024
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Value("${message.controller.department.delete.success}")
    private String messageDeleteSuccess;

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> findDepartmentById(@PathVariable("departmentId")  Long departmentId){
        return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAllDepartments(){
        return new ResponseEntity<>(departmentService.getAllDepartments(),HttpStatus.OK);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable("departmentId") Long departmentId,
            @RequestBody DepartmentDto departmentDto ){
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId,departmentDto),HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> delete(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(messageDeleteSuccess,HttpStatus.OK);
    }
}
