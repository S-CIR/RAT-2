package src.model;

public class Azienda {
	
	private int id;
	private String email;
	private String name;
	private String site;
	
	//constructor
	public Azienda(int id, String email, String name, String site) {
	    this.id = id;
	    this.email = email;
	    this.name = name;
	    this.site = site;
	  }
	
	//empty constructor
	public Azienda() {}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
}
