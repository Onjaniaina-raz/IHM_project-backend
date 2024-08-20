package mg.salary.emp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.salary.emp.repository.HistoryRepository;
import mg.salary.emp.request.AskRequest;
import mg.salary.emp.request.ChartDataAsk;

@Service
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	HistoryRepository historyRepository;

	@Override
	public AskRequest getAskByCurrentDate(Long employeeId) {

		LocalDate now = LocalDate.now();
		int currentMonth = now.getMonthValue();
		int currentYear = now.getYear();
		
		return historyRepository.findAsk(employeeId, currentMonth, currentYear);

	}

	@Override
	public AskRequest getPromotionByCurrentDate(Long employeeId) {

		LocalDate now = LocalDate.now();
		int currentMonth = now.getMonthValue();
		int currentYear = now.getYear();
		
		return historyRepository.findPromo(employeeId, currentMonth, currentYear);
	}

	@Override
	public AskRequest getReportByCurrentDate(Long employeeId) {

		LocalDate now = LocalDate.now();
		int currentMonth = now.getMonthValue();
		int currentYear = now.getYear();
		
		return historyRepository.findReport(employeeId, currentMonth, currentYear);	
	}

	@Override
	public List<ChartDataAsk> getAskAmount(int year) {
		
		List<Object[]> results = historyRepository.findChartAskAmount(year);
		List<ChartDataAsk> total = new ArrayList<>(); 
		
		for (Object[] row : results) {
			String nature = (String) row[0];
			int month = ((Number) row[1]).intValue();
			Long count = ((Number) row[2]).longValue();
			
			total.add(new ChartDataAsk(nature, month, count));
		}
		
		return total;
	}

	@Override
	public List<ChartDataAsk> getPromoAmount(int year) {

		List<Object[]> results = historyRepository.findChartPromoAmount(year);
		List<ChartDataAsk> total = new ArrayList<>(); 
		
		for (Object[] row : results) {
			String nature = (String) row[0];
			int month = ((Number) row[1]).intValue();
			Long count = ((Number) row[2]).longValue();
			
			total.add(new ChartDataAsk(nature, month, count));
		}
		
		return total;
	}

	@Override
	public List<ChartDataAsk> getReportAmount(int year) {

		List<Object[]> results = historyRepository.findChartReportAmount(year);
		List<ChartDataAsk> total = new ArrayList<>(); 
		
		for (Object[] row : results) {
			String nature = (String) row[0];
			int month = ((Number) row[1]).intValue();
			Long count = ((Number) row[2]).longValue();
			
			total.add(new ChartDataAsk(nature, month, count));
		}
		
		return total;
	}
}
