package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
    }

    public void tuomarinToimet(String ekanSiirto, String tokanSiirto) {
        kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tilanne());
    }
    
    public String tilanne() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
    }
    
    private void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else {
            voittajallePiste(ekanSiirto, tokanSiirto);
        }
    }

    // sisäinen metodi, jolla tarkastetaan tuliko tasapeli
    private boolean tasapeli(String ekanSiirto, String tokanSiirto) {
        return ekanSiirto.equals(tokanSiirto);
    }

    private void voittajallePiste(String ekanSiirto, String tokanSiirto) {
        if (ekanSiirto.equals("k") && tokanSiirto.equals("s")) {
            ekanPisteet++;
        } else if (ekanSiirto.equals("s") && tokanSiirto.equals("p")) {
            ekanPisteet++;
        } else if (ekanSiirto.equals("p") && tokanSiirto.equals("k")) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }
}
