package mg.salary.emp.service;

import java.util.List;

import mg.salary.emp.request.AskRequest;
import mg.salary.emp.request.ChartDataAsk;

public interface HistoryService {

	public AskRequest getAskByCurrentDate(Long employeeId);
	public AskRequest getPromotionByCurrentDate(Long employeeId);
	public AskRequest getReportByCurrentDate(Long employeeId);
	public List<ChartDataAsk> getAskAmount(int year);
	public List<ChartDataAsk> getPromoAmount(int year);
	public List<ChartDataAsk> getReportAmount(int year);
}
