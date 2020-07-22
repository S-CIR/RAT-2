package src.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Request {
	
	//COSTRUTTORI--------------------------------
	
	public Request () {}
	
	public Request(int idRequest, int hours, int requestCfu, Date startDate, Date endDate, int validatedCfu,
			int fk_state, int fk_azienda, String fk_user, String matricola) {
		super();
		this.idRequest = idRequest;
		this.hours = hours;
		this.requestCfu = requestCfu;
		this.startDate = startDate;
		this.endDate = endDate;
		this.validatedCfu = validatedCfu;
		this.fk_state = fk_state;
		this.fk_azienda = fk_azienda;
		this.fk_user = fk_user;
		this.matricola = matricola;
	}

	public Request(int idRequest, int hours, int requestCfu, Date startDate, Date endDate, int validatedCfu,
			List<Attached> attached, int fk_state, int fk_azienda, String fk_user, String matricola) {
		super();
		this.idRequest = idRequest;
		this.hours = hours;
		this.requestCfu = requestCfu;
		this.startDate = startDate;
		this.endDate = endDate;
		this.validatedCfu = validatedCfu;
		this.attached = attached;
		this.fk_state = fk_state;
		this.fk_azienda = fk_azienda;
		this.fk_user = fk_user;
		this.matricola = matricola;
	}
	
	public Request(int hours, int requestCfu, Date startDate, Date endDate, int validatedCfu,
			int fk_state, int fk_azienda, String fk_user, String matricola) {
		super();
		this.hours = hours;
		this.requestCfu = requestCfu;
		this.startDate = startDate;
		this.endDate = endDate;
		this.validatedCfu = validatedCfu;
		this.fk_state = fk_state;
		this.fk_azienda = fk_azienda;
		this.fk_user = fk_user;
		this.matricola = matricola;
	}

	public Request(int hours, int requestCfu, Date startDate, Date endDate, int validatedCfu,
		 int fk_state, int fk_azienda, String fk_user) {
		super();
		this.hours = hours;
		this.requestCfu = requestCfu;
		this.startDate = startDate;
		this.endDate = endDate;
		this.validatedCfu = validatedCfu;
		this.attached=null;
		this.fk_state = fk_state;
		this.fk_azienda = fk_azienda;
		this.fk_user = fk_user;
	}



	public Request (int idRequest, int hours, int requestCfu, Date startDate,
		            Date endDate, int validatedCfu,List<Attached> attached,
		            int fk_state, int fk_azienda, String fk_user) {
		
		this.idRequest = idRequest;
	    this.requestCfu = requestCfu;
	    this.hours = hours;
	    this.startDate = startDate;
	    this.endDate = endDate;
	    this.validatedCfu = validatedCfu;
	    this.attached = attached;
	    this.fk_state = fk_state;
	    this.fk_azienda = fk_azienda;
	    this.fk_user = fk_user;
	}

	//GETTERS-------------------------------------
	
	public String getMatricola() {
		return matricola;
	}

	public int getIdRequest () {
		return idRequest;
	}

	public int getHours () {
		return hours;
	}

	public int getRequestCfu () {
		return requestCfu;
	}

	public Date getStartDate () {
		return startDate;
	}

	public Date getEndDate () {
		return endDate;
	}

	public int getValidatedCfu () {
		return validatedCfu;
	}

	public List<Attached> getAttached () {
		return attached;
	}

	public int getState () {
		return fk_state;
	}

	public int getAzienda () {
		return fk_azienda;
	}

	public String getUser () {
		return fk_user;
	}
	
	//SETTERS-------------------------------------
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public void setIdRequest (int idRequest) {
		this.idRequest = idRequest;
	}

	public void setHours (int hours) {
		this.hours = hours;
	}

	public void setRequestCfu (int requestCfu) {
		this.requestCfu = requestCfu;
	}

	public void setStartDate (Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate (Date endDate) {
		this.endDate = endDate;
	}

	public void setValidatedCfu (int validatedCfu) {
		this.validatedCfu = validatedCfu;
	}

	public void setAttached (List<Attached> attached) {
		this.attached = attached;
	}

	public void setState (int fk_state) {
		this.fk_state = fk_state;
	}

	public void setAzienda (int fk_azienda) {
		this.fk_azienda = fk_azienda;
	}

	public void setUser (String fk_user) {
		this.fk_user = fk_user;
	}
	
	@Override
	public String toString() {
		return "Request [idRequest=" + idRequest + ", hours=" + hours + ", requestCfu=" + requestCfu + ", startDate="
				+ startDate + ", endDate=" + endDate + ", validatedCfu=" + validatedCfu + ", attached=" + attached
				+ ", fk_state=" + fk_state + ", fk_azienda=" + fk_azienda + ", fk_user=" + fk_user + "]";
	}
	
	//VARIABILI LOCALI---------------------------



	private int idRequest;
	private int hours;
	private int requestCfu;
	private Date startDate;
	private Date endDate;
	private int validatedCfu;
	private List<Attached> attached;
	private int fk_state;
	private int fk_azienda;
	private String fk_user;
	private String matricola;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy - MM - dd");
	
}
