package mg.salary.emp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mg.salary.emp.model.Employee;
import mg.salary.emp.model.Jobs;
import mg.salary.emp.repository.EmployeeRepository;
import mg.salary.emp.repository.JobsRepository;
import mg.salary.emp.request.EmployeeRequest;
import mg.salary.emp.service.EmployeeService;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	private static final String UPLOAD_DIR = "C:/Users/Raz/Documents/react_ts/ihm_project/public/assets/uploaded/";
	
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("image") MultipartFile file) {
		try {
			if(file.isEmpty()) {
				return "File is empty";
			}
			
			String filePath = UPLOAD_DIR + file.getOriginalFilename();
			file.transferTo(new File(filePath));
			
			return "Image uploaded successfully";
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
			return "Error while uploading your image";
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequest employeeRequest){
		
		Employee employee = new Employee();
		
		employee.setFname(employeeRequest.fname);
		employee.setLname(employeeRequest.lname);
		employee.setEmail(employeeRequest.email);
		employee.setBirthDate(employeeRequest.birthDate);
		employee.setCardNumber(employeeRequest.cardNumber);
		employee.setCardPass(employeeRequest.cardPass);
		employee.setImageUrl(employeeRequest.imageUrl);
		employee.setCard(employeeRequest.card);
		
		Employee savedEmployee = employeeRepository.save(employee); 
		
		return ResponseEntity.ok(savedEmployee);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Employee>> fetchEmployee(){
		
		List<Employee> employee = employeeRepository.findAll();
		
		return ResponseEntity.ok(employee);
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<Employee> fetchEmployeeById(@PathVariable Long id){
		
		Employee employee = employeeRepository.findById(id).orElse(null);
		
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/setJob/{id}")
	public ResponseEntity<Employee> setJob(@PathVariable Long id, @RequestBody Long jobId){
		
		Employee existingEmployee = employeeRepository.findById(id).orElse(null);
		
		Jobs job = jobsRepository.findById(jobId).orElse(null);
		
		if(existingEmployee != null) {
			
			Employee employee = new Employee();
			
			employee.setId(existingEmployee.getId());
			employee.setFname(existingEmployee.getFname());
			employee.setLname(existingEmployee.getLname());
			employee.setEmail(existingEmployee.getEmail());
			employee.setBirthDate(existingEmployee.getBirthDate());
			employee.setCardNumber(existingEmployee.getCardNumber());
			employee.setCardPass(existingEmployee.getCardPass());
			employee.setImageUrl(existingEmployee.getImageUrl());
			employee.setCard(existingEmployee.getCard());
			employee.setJobs(job);
			employee.setHistories(new ArrayList<>(existingEmployee.getHistories()));
			
			Employee updatedEmployee = employeeService.updateJobs(employee);
			return ResponseEntity.ok(updatedEmployee);
			
		}
		
		return ResponseEntity.notFound().build();
	}
}
