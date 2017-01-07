package GUI;


import persistenz.Dozent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EinloggDialogFunktionalität {

    public boolean einloggen(int id, String passwort){  //TODO mitgeben der id an das Dozentenfenster evtl auch begrüßung
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nm-pu"); //TODO an den ANFANG des Programms
        EntityManager em = emf.createEntityManager();

        Dozent d = em.find(Dozent.class, id);
        try {
            if (passwort == d.getDPasswort()) {
                return true;
            } else {
                return false;
            }
        }finally {
                em.close();
                emf.close();    //TODO an das ENDE des Programms
        }
    }
}
