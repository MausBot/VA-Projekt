package persistenz;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Dozent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dId;
	private String dName;
	private String dVorname;
	private String dPasswort;
	@OneToMany(mappedBy = "dozent")
	private List<Modul> module;
	
	public Dozent(){
	}

	public Dozent(String dName, String dVorname, String dPasswort){
		this.dName = dName;
		this.dVorname = dVorname;
		this.dPasswort = dPasswort;
	}
	
	/*public void studentenlisteAnzeigen(Modul modul, Date datum){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("nm-pu");	//TODO evtl außerhalb der Methode
		EntityManager em = emf.createEntityManager();
		
		
		for (int i = 1; i <= 100; i++){		//TODO 100 evtl ersetzen
			Pruefungstermin pt = em.find(Pruefungstermin.class, i);
			if(pt.getModul() == modul && pt.getDatum() == datum){
				for (int j = 1; i <= 100; j++){		//TODO 100 evtl ersetzen
					Pruefung p = em.find(Pruefung.class, j);
					if (p.getPrueftermin() == pt){
						Student s = em.find(Student.class, p.getStudent());
						System.out.println(s.getSId() + ", " + s.getSName() + ", " + s.getSVorname() + ": " + p.getNote()); //TODO ersetzen durch anzeige in javafx
					}
				}	
			}
		}
		em.close();
		emf.close();
	}
	*/
	public void noteSpeichern(int pId, double note){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("nm-pu");
		EntityManager em = emf.createEntityManager();
		
		Pruefung p = em.find(Pruefung.class, pId);
		p.setNote(note);
	}
	
	public int getDId(){
		return dId;
	}
	
	public String getDName(){
		return dName;
	}
	
	public String getDVorname(){
		return dVorname;
	}
	
	public String getDPasswort(){
		return dPasswort;
	}
	
	public void setDName(String dName){
		this.dName = dName;
	}
	
	public void setDVorname(String dVorname){
		this.dVorname = dVorname;
	}
	
	public void setDPasswort(String dPasswort){
		this.dPasswort = dPasswort;
	}

	public List<Modul> getModule(){
	    return module;
    }

    public void setModule(List<Modul> module){
	    this.module = module;
    }

    public void add(Modul m){
        module.add(m);
        m.setDozent(this);
    }
}