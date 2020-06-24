package src.model;

import src.interfaccia.UserInterface;

public class Admin implements src.interfaccia.UserInterface {
	
	private String email;
	private String name;
	private String surname;
	private char gender;
	private String password;
	private int userType;
	
	
	//constructor
	public Admin(String nome,String cognome,char sex,String email,String password,int userType) {
	    this.email = email;
	    this.name = nome;
	    this.surname = cognome;
	    this.gender = sex;
	    this.password = password;
	    this.userType = userType;
	  }
	
	//empty constructor
	public Admin() {}
	
	public int insert() {
		return UtenteDAO.doSave(this);
	}

	//getters and setters
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_Type() {
		return userType;
	}

	public void setUser_Type(int userType) {
		this.userType = userType;
	}
	
}
