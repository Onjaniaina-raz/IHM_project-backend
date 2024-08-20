package mg.salary.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.salary.emp.model.Employee;
import mg.salary.emp.model.History;
import mg.salary.emp.repository.EmployeeRepository;
import mg.salary.emp.repository.HistoryRepository;
import mg.salary.emp.request.AskRequest;
import mg.salary.emp.request.ChartDataAsk;
import mg.salary.emp.request.HistoryRequest;
import mg.salary.emp.service.HistoryService;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/api/history")
public class HistoryController {

	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private HistoryService historyService;
	
	@PostMapping("/save")
	public ResponseEntity<History> saveHistory (@RequestBody HistoryRequest historyRequest){
		
		Employee existingEmployee = employeeRepository.findById(historyRequest.employeeId).orElse(null); 
		
		if(existingEmployee != null) {
			History history = new History();
			
			history.setNature(historyRequest.nature);
			history.setDescription(historyRequest.description);
			history.setAmount(historyRequest.amount);
			history.setEmployee(existingEmployee);
			
			History savedHistory = historyRepository.save(history);
			
			return ResponseEntity.ok(savedHistory);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/list/{employeeId}")
	public ResponseEntity<List<History>> fetchHistory(@PathVariable Long employeeId){
		
		List<History> history = historyRepository.findAllById(employeeId);
		
		return ResponseEntity.ok(history);
	}
	
	@GetMapping("list/ask/{employeeId}")
	public ResponseEntity<AskRequest> fetchAsk(@PathVariable Long employeeId){
		
		AskRequest ask = historyService.getAskByCurrentDate(employeeId);
		
		return ResponseEntity.ok(ask);
	}
	
	@GetMapping("list/promotion/{employeeId}")
	public ResponseEntity<AskRequest> fetchPromo(@PathVariable Long employeeId){
		
		AskRequest ask = historyService.getPromotionByCurrentDate(employeeId);
		
		return ResponseEntity.ok(ask);
	}
	
	@GetMapping("list/report/{employeeId}")
	public ResponseEntity<AskRequest> fetchReport(@PathVariable Long employeeId){
		
		AskRequest ask = historyService.getReportByCurrentDate(employeeId);
		
		return ResponseEntity.ok(ask);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<History> fetchEmployeeHistory(@PathVariable Long id){
		
		History history = historyRepository.findById(id).orElse(null); 
				
		return ResponseEntity.ok(history);
	}
	
	@GetMapping("/chart/ask/amount")
	public ResponseEntity<List<ChartDataAsk>> getAskAmount(){
		
		List<ChartDataAsk> askAmount = historyService.getAskAmount(2024);
		
		return ResponseEntity.ok(askAmount);
	}
	
	@GetMapping("/chart/promotion/amount")
	public ResponseEntity<List<ChartDataAsk>> getPromoAmount(){
		
		List<ChartDataAsk> askAmount = historyService.getPromoAmount(2024);
		
		return ResponseEntity.ok(askAmount);
	}
	
	@GetMapping("/chart/report/amount")
	public ResponseEntity<List<ChartDataAsk>> getReportAmount(){
		
		List<ChartDataAsk> askAmount = historyService.getReportAmount(2024);
		
		return ResponseEntity.ok(askAmount);
	}
}
