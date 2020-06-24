package src.model;

public class Attached {

	private int idAttached, fk_request;
	private String filename, fk_user;
	
	//constructor
	
	public Attached(int idAttached, String filename, int fk_request, String fk_user) {
		this.idAttached = idAttached;
		this.filename = filename;
		this.fk_request = fk_request;
		this.fk_user = fk_user;
	}
	
	public Attached(int fk_request, String filename, String fk_user) {
		this.fk_request = fk_request;
		this.filename = filename;
		this.fk_user = fk_user;
	}

	//empty constructor
	public Attached() {}

	
	//getters and setters
	public int getIdAttached() {
		return idAttached;
	}

	public void setIdAttached(int idAttached) {
		this.idAttached = idAttached;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFk_request() {
		return fk_request;
	}

	public void setFk_request(int fk_request) {
		this.fk_request = fk_request;
	}

	public String getFk_user() {
		return fk_user;
	}

	public void setFk_user(String fk_user) {
		this.fk_user = fk_user;
	}
	
}
