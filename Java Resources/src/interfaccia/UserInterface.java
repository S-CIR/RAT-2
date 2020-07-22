package src.interfaccia;

public interface UserInterface {
	
	int insert();
	
//Getters
	
	public String getName();
	
	public String getSurname();
	
	public char getGender();
	
	public String getEmail();
	
	public String getPassword();
	
	public int getUser_Type();

//Setters
	
	public void setName(String name);
	
	public void setSurname(String surname);
	
	public void setGender(char gender);
	
	public void setEmail(String email);
	
	public void setPassword(String password);
	
	public void setUser_Type(int user_Type);

}
