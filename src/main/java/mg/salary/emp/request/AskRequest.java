package mg.salary.emp.request;

public class AskRequest {

	public Long nature;
	public Long total;
	
	public AskRequest(Long nature, Long total) {
		this.nature = nature;
		this.total = total;
	}
}
