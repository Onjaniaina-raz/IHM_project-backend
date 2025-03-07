package mg.salary.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.salary.emp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
