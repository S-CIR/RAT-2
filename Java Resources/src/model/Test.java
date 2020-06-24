package src.model;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import src.controller.DBConnection;

public class Test {

	public static void main(String[] args) 
	{
		
		Azienda az=new Azienda(1, "prova@azienda1.com", "Azienda1", "www.azienda1.com");
		State sta=new State(1, "da approvare");
		Admin adm=new Admin("Mario", "Rossi", 'm', "mariorossi@unisa.it", "1234", 2);
		Secretary sec=new Secretary("Anna", "Verdi", 'f', "annaverdi@unisa.it", "pass", 1);
		Student stu=new Student("Luca", "Bianchi", 'm', "l.bianchi@studenti.unisa.it", "pass1234", 0);
		Attached att=new Attached(01, "att01.pdf", 01, "l.bianchi@studenti.unisa.it");
		ArrayList<Attached> lst = new ArrayList<Attached>();
		lst.add(att);
		GregorianCalendar start = new GregorianCalendar(2020, 01, 01);
		GregorianCalendar end = new GregorianCalendar(2020, 05, 01);
		
		AziendaDAO.doSave(az);
		StateDAO.doSave(sta);
		UtenteDAO.doSave(adm);
		UtenteDAO.doSave(sec);
		UtenteDAO.doSave(stu);
		AttachedDAO.doSave(att);
		
	}

}
