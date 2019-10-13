package accessibilitatweb.accessibilitatweb.domain;

public class Contacte {
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private String companyia;
    private String assumpte;
    private String comentari;

    public Contacte( String nom,String cognoms, String email, String telefon, String companyia, String assumpte, String comentari){
        this.nom=nom;
        this.cognoms=cognoms;
        this.email=email;
        this.telefon=telefon;
        this.companyia=companyia;
        this.assumpte=assumpte;
        this.comentari=comentari;
    }
    public Contacte(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCompanyia() {
        return companyia;
    }

    public void setCompanyia(String companyia) {
        this.companyia = companyia;
    }

    public String getAssumpte() {
        return assumpte;
    }

    public void setAssumpte(String assumpte) {
        this.assumpte = assumpte;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }
}
