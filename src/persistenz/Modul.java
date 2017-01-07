package persistenz;

import javax.persistence.*;
import java.util.List;

@Entity
public class Modul {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mId;
	@Column(name = "modulbezeichnung")
	private String mBez;
	@ManyToOne
	private Dozent dozent;
	@ManyToOne
	private Studiengang studg;
	private int semester;
	@OneToMany(mappedBy = "modul")
	private List<Pruefungstermin> prueftermine;

	public Modul (){
	}

	public Modul (String mBez, Dozent dozent, Studiengang studg, int semester){
		this.mBez = mBez;
		this.dozent = dozent;
		this.studg = studg;
		this.semester = semester;
	}
	
	public int getMId(){
		return mId;
	}
	
	public String getMBez(){
		return mBez;
	}
	
	public Dozent getDozent(){
		return dozent;
	}
	
	public Studiengang getStudg(){
		return studg;
	}
	
	public int getSemester(){
		return semester;
	}
	
	public void setMBez(String mBez){
		this.mBez = mBez;
	}
	
	public void setDozent(Dozent dozent){
		this.dozent = dozent;
	}
	
	public void setStudg(Studiengang studg){
		this.studg = studg;
	}
	
	public void setSemester(int semester){
		this.semester = semester;
	}

	public List<Pruefungstermin> getPrueftermine(){
		return prueftermine;
	}

	public void setPrueftermine(List<Pruefungstermin> prueftermine){
		this.prueftermine = prueftermine;
	}

	public void add(Pruefungstermin p){
		prueftermine.add(p);
		p.setModul(this);
	}
}
