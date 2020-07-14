package src.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import src.controller.DBConnection;
import src.interfaccia.UserInterface;
import src.model.Attached;
import src.model.Request;

public class Test {

	public static void main(String[] args) {
		List<Attached> lst = new ArrayList<>();
		Request re = new Request (01, 60, 6, new Date(), new Date(), 0, lst, 1, 1, "prova@studenti.unisa.it");
		
		System.out.println(re.toString());
		
		
	}

}
