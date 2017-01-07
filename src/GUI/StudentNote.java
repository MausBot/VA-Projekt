package GUI;
public class StudentNote {

    private String nachname;
    private String vorname;
    private Double note;
    private Integer id;

    public StudentNote(){
        this.id = 0;
        this.nachname = "";
        this.vorname = "";
        this.note = 0.0;
    }

    public StudentNote(Integer id, String nachname, String vorname, Double note){
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
