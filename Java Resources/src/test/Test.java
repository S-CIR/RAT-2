package src.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.controller.DBConnection;
import src.interfaccia.UserInterface;
import src.model.Student;
import src.model.UtenteDAO;

public class Test {

	public static void main(String[] args) {
		UserInterface user = new Student("a", "a", 'M', "c","a", 0);
		UtenteDAO.doSave(user);
	}

}
