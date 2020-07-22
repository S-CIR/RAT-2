package src.model;

public class State {

// VARIABILI LOCALI:
	
	private int id_State;
	private String description;
	
// COSTRUTTORI:
	
	 public State() {}
	 
	  public State(int idState,String description) {
		    this.id_State = idState;
		    this.description = description;
	}


// GETTERS AND SETTERS:
	  
		public int getId_State() {
			return id_State;
		}

		public void setId_State(int id_State) {
			this.id_State = id_State;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		  
	
}
