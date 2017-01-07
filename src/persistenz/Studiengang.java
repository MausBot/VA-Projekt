package persistenz;

import javax.persistence.*;

@Entity
public class Studiengang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sgId;
	@Column (name = "studiengangbezeichnung")
	private String sgBez;

	public Studiengang(){
	}

	public Studiengang(String sgBez){
		this.sgBez = sgBez;
	}
	
	public int getSgId(){
		return sgId;
	}
	
	public String getSgBez(){
		return sgBez;
	}
	
	public void setSgBez(String sgBez){
		this.sgBez = sgBez;
	}
}
