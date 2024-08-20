package mg.salary.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.salary.emp.model.History;
import mg.salary.emp.request.AskRequest;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

	@Query("SELECT h FROM History h WHERE h.employee.id = ?1 ORDER BY h.dateHistory DESC")
	List<History> findAllById(Long employeeId);
	
	@Query("SELECT new mg.salary.emp.request.AskRequest (COUNT(h.nature) as natures , SUM(h.amount)) as total FROM History h WHERE h.employee.id = :employee_id AND EXTRACT(MONTH FROM h.dateHistory) = :month AND EXTRACT(YEAR FROM h.dateHistory) = :year AND h.nature = 'ask'")
	AskRequest findAsk(@Param("employee_id") Long employeeId, @Param("month") int month, @Param("year") int year);
	
	@Query("SELECT new mg.salary.emp.request.AskRequest (COUNT(h.nature) as natures , SUM(h.amount)) as total FROM History h WHERE h.employee.id = :employee_id AND EXTRACT(MONTH FROM h.dateHistory) = :month AND EXTRACT(YEAR FROM h.dateHistory) = :year AND h.nature = 'promotion'")
	AskRequest findPromo(@Param("employee_id") Long employeeId, @Param("month") int month, @Param("year") int year);
	
	@Query("SELECT new mg.salary.emp.request.AskRequest (COUNT(h.nature) as natures , SUM(h.amount)) as total FROM History h WHERE h.employee.id = :employee_id AND EXTRACT(MONTH FROM h.dateHistory) = :month AND EXTRACT(YEAR FROM h.dateHistory) = :year AND h.nature = 'report'")
	AskRequest findReport(@Param("employee_id") Long employeeId, @Param("month") int month, @Param("year") int year);
	
	@Query("SELECT h.nature, EXTRACT(MONTH FROM h.dateHistory) as month, SUM(h.amount) as total FROM History h WHERE EXTRACT(YEAR FROM h.dateHistory) = :year AND h.nature = 'ask' GROUP BY h.nature, month")
	List<Object[]> findChartAskAmount(@Param("year") int year);
	
	@Query("SELECT h.nature, EXTRACT(MONTH FROM h.dateHistory) as month, SUM(h.amount) as total FROM History h WHERE EXTRACT(YEAR FROM h.dateHistory) = :year AND h.nature = 'promotion' GROUP BY h.nature, month")
	List<Object[]> findChartPromoAmount(@Param("year") int year);
	
	@Query("SELECT h.nature, EXTRACT(MONTH FROM h.dateHistory) as month, SUM(h.amount) as total FROM History h WHERE EXTRACT(YEAR FROM h.dateHistory) = :year AND h.nature = 'report' GROUP BY h.nature, month")
	List<Object[]> findChartReportAmount(@Param("year") int year);
}
