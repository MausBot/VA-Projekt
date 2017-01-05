package GUI;
public class StudentenID {

    private String nachname;
    private String vorname;
    private Double note;

    public StudentenID(){
        this.nachname = "";
        this.vorname = "";
        this.note = 0.0;
    }

    public StudentenID(String nachname, String vorname, Double note){
        this.nachname = nachname;
        this.vorname = vorname;
        this.note = note;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
