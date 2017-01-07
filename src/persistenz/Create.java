package persistenz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Create {
	private static Dozent[] dozenten = new Dozent[]{
			new Dozent ("Milch", "Müller", "passwort1"), new Dozent ("Brot", "Bananen", "passwortb")
	};
	
	public static void main (String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("nm-pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Dozent d : dozenten){
			em.persist(d);
			em.flush();
		}
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
