package com.spring.swagger.service;


import com.spring.swagger.entity.Employee;
import com.spring.swagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Override
	public boolean saveEmployee(Employee employee) throws IOException {
		try {
			if (employee != null) {
				//employeeRepository.saveAndFlush(employee);
				employeeRepository.save(employee);
				//employeeRepository.save(employee);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public boolean deleteFile(Long id, String file) {
		boolean status = false;
		try {
			if (id != 0 && file != null) {
				employeeRepository.deleteEmployeeWithFile(id, file);
				System.out.println(this.getClass().getSimpleName() + ":deleting employee... " + id);
				String path = uploadDirectory + "/" + file;
				System.out.println("Path=" + path);
				File fileToDelete = new File(path);
				status = fileToDelete.delete();
				System.out.println(this.getClass().getSimpleName() + ":deleting file... " + file);
				System.out.println("Success: " + status + " fileToDelete: " + fileToDelete);
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		return status;
	}

}
