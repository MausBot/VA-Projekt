package persistenz;

import javax.persistence.*;

@Entity
public class Pruefung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	@ManyToOne
	private Pruefungstermin prueftermin;
	@ManyToOne
	private Student student;
	private double note;

	public Pruefung(){
	}

	public Pruefung(Pruefungstermin prueftermin, Student student){
		this.prueftermin = prueftermin;
		this.student = student;
	}
	
	public int getPId(){
		return pId;
	}
	
	public Pruefungstermin getPrueftermin(){
		return prueftermin;
	}
	
	public Student getStudent(){
		return student;
	}
	
	public double getNote(){
		return note;
	}
	
	public void setNote(double note){
		this.note = note;
	}

	public void setPrueftermin(Pruefungstermin prueftermin){
		this.prueftermin = prueftermin;
	}

	public void setStudent(Student student){
		this.student = student;
	}
}
