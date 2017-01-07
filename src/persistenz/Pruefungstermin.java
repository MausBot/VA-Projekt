package persistenz;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Pruefungstermin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ptId;
	private Date datum;
	@OneToMany(mappedBy = "prueftermin")
	private List<Pruefung> pruefungen;
	@ManyToOne
	private Modul modul;

	public Pruefungstermin(){
	}

	public Pruefungstermin(Date datum, Modul modul){
		this.datum = datum;
		this.modul = modul;
	}
	
	public void ptDurchschnitt(Modul modul, Date datum){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("nm-pu");	//TODO evtl außerhalb der Methode
		EntityManager em = emf.createEntityManager();
		
		double durchschnitt = 0;
		
		for (int i = 1; i <= 100; i++){		//TODO 100 evtl ersetzen
			Pruefungstermin pt = em.find(Pruefungstermin.class, i);
			if(pt.getModul() == modul && pt.getDatum() == datum){
				for (int j = 1; i <= 100; j++){		//TODO 100 evtl. ersetzen
					Pruefung p = em.find(Pruefung.class, j);
					if (p.getPrueftermin() == pt){
						durchschnitt =+ p.getNote();	
					}
				}	
			}
		}
		System.out.println("Durchschnittsnote: " + durchschnitt); //TODO ersetzen durch anzeige in javafx
		
		em.close();
		emf.close();
	}
	
	public int getPtId(){
		return ptId;
	}
	
	public Date getDatum(){
		return datum;
	}
	
	public Modul getModul(){
		return modul;
	}
	
	public void setDatum(Date datum){
		this.datum = datum;
	}
	
	public void setModul(Modul modul){
		this.modul = modul;
	}

	public List<Pruefung> getPruefungen(){
		return pruefungen;
	}

	public void setPruefungen(List<Pruefung> pruefungen){
		this.pruefungen = pruefungen;
	}

	public void add(Pruefung p){
		pruefungen.add(p);
		p.setPrueftermin(this);
	}
	
	public int getPtIdPerMIdDatum(int mId, Date datum){
		return ptId;
	}
}
