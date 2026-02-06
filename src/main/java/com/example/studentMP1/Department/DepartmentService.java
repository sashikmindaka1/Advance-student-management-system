package com.example.studentMP1.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // 1. අලුත් Department එකක් හදනවා (උදා: Software Engineering, IT)
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // 2. තියෙන ඔක්කොම Departments ටික ගන්නවා (Dropdown එකට ඕන වෙනවා)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}