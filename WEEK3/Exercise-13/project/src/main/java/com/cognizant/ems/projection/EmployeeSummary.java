package com.cognizant.ems.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeSummary {
    
    Long getId();
    
    String getName();
    
    String getEmail();
    
    // Closed projection: simply gets the department name via nested property
    // We can also use Open Projection with SpEL:
    @Value("#{target.department != null ? target.department.name : 'N/A'}")
    String getDepartmentName();
    
    Double getSalary();
}
