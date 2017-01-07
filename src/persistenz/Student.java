package persistenz;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	private String sName;
	private String sVorname;
	private int semester;
	private String sPasswort;
	@ManyToOne
	private Studiengang studg;
	@OneToMany(mappedBy = "student")
	private List<Pruefung> pruefungen;

	public Student(){
	}

	public Student (String sName, String sVorname, int semester, String sPasswort, Studiengang studg){
		this.sName = sName;
		this.sVorname = sVorname;
		this.semester = semester;
		this.sPasswort = sPasswort;
		this.studg = studg;
	}
	
	public int getSId(){
		return sId;
	}
	
	public String getSName(){
		return sName;
	}
	
	public String getSVorname(){
		return sVorname;
	}
	
	public int getSemester(){
		return semester;
	}
	
	public String getSPasswort(){
		return sPasswort;
	}
	
	public Studiengang getStudg(){
		return studg;
	}
	
	public void setSName(String sName){
		this.sName = sName;
	}
	
	public void setSVorname(String sVorname){
		this.sVorname = sVorname;
	}
	
	public void setSemester(int semester){
		this.semester = semester;
	}
	
	public void setSPasswort(String sPasswort){
		this.sPasswort = sPasswort;
	}
	
	public void setStudg(Studiengang studg){
		this.studg = studg;
	}

	public List<Pruefung> getPruefungen(){
		return pruefungen;
	}

	public void setPruefungen(List<Pruefung> pruefungen){
		this.pruefungen = pruefungen;
	}

	public void add(Pruefung p){
		pruefungen.add(p);
		p.setStudent(this);
	}
}