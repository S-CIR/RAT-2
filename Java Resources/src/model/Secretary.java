package src.model;

public class Secretary implements src.interfaccia.UserInterface {
	
// VARIABILI LOCALI:
	
	private String name;
	private String surname;
	private char gender;
	private String email;
	private String password;
	private int user_Type;
	
	public int insert() {
		return UtenteDAO.doSave(this);
	}
	
// COSTRUTTORI:
	
	public Secretary() { }
	
	public Secretary(String nome,String cognome,char sex,String email,String password,int userType) {
		this.name=nome;
		this.surname=cognome;
		this.gender=sex;
		this.email=email;
		this.password=password;
		this.user_Type=userType;
	}
	
// GETTERS AND SETTERS:

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_Type() {
		return user_Type;
	}

	public void setUser_Type(int user_Type) {
		this.user_Type = user_Type;
	}
	
}

