package GUI;


import persistenz.Dozent;
import persistenz.Modul;
import persistenz.Pruefung;
import persistenz.Pruefungstermin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DozentDialogFunktionalität {

    public static List<Modul> dropDownModul(Dozent d){
        return d.getModule();
    }

    public static List<Modul> dropDownModulInt(int id){

        EntityManager em = emf.createEntityManager();
        Dozent d = em.find(Dozent.class, id);
        em.close();

        return d.getModule();
    }

    public static List<Date> dropDownDatum(Modul m){
        List<Date> datumListe = new ArrayList<Date>();
        for (Pruefungstermin pt: m.getPrueftermine()){
            datumListe.add(pt.getDatum());
        }
        return datumListe;
    }

    //TODO Transferklasse für Tabellenobjekete: StudentNote
    //TODO List durch ObservableList ersetzen
    public List<StudentNote> pruefungAnzeigen(Pruefungstermin pt){
        List<StudentNote> studentNoteListe = new ArrayList<StudentNote>();
        for (Pruefung p: pt.getPruefungen()){
            studentNoteListe.add(new StudentNote(p.getStudent().getSId(), p.getStudent().getSName(), p.getStudent().getSVorname(), p.getNote()));
        }
        return studentNoteListe;
    }

    public void noteSpeichern(Pruefung p, double note){ //TODO evtl mit sudent
        p.setNote(note);
    }

    public double durchschnittBerechnen(Pruefungstermin pt){
        double durchschnitt = 0.0;
        for (Pruefung p: pt.getPruefungen()){
            durchschnitt =+ p.getNote();
        }
        return durchschnitt = durchschnitt/ (double) pt.getPruefungen().size();
    }
}
