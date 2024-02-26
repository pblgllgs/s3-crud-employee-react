package com.pblgllgs.ems.dto;
/*
 *
 * @author pblgl
 * Created on 25-02-2024
 *
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String departmentDescription;
}
