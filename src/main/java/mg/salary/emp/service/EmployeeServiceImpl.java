package mg.salary.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mg.salary.emp.model.Employee;
import mg.salary.emp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public Employee updateJobs(Employee employee) {
		
		return employeeRepository.save(employee);
	}

}
