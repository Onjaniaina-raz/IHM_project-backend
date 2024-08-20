package mg.salary.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.salary.emp.model.Jobs;
import mg.salary.emp.repository.JobsRepository;
import mg.salary.emp.request.JobsRequest;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/api/jobs")
public class JobsController {

	@Autowired
	private JobsRepository jobsRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Jobs> saveJobs(@RequestBody JobsRequest jobsRequest){
		
		Jobs jobs = new Jobs();
		
		jobs.setDuty(jobsRequest.duty);
		jobs.setAmount(jobsRequest.amount);
		
		Jobs savedJobs = jobsRepository.save(jobs);
		
		return ResponseEntity.ok(savedJobs);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Jobs>> fetchJobs(){
		
		List<Jobs> jobs = jobsRepository.findAll();
		
		return ResponseEntity.ok(jobs);
	}
}
