package mg.salary.emp.request;

public class ChartDataAsk {

	public String nature;
	public int month;
	public Long amount;
	
	public ChartDataAsk(String nature, int month, Long amount) {
		this.nature = nature;
		this.month = month;
		this.amount = amount;
	}
}
