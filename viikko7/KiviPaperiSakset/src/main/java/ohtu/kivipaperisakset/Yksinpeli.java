package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Yksinpeli extends KPS {

    private Tekoaly tekoaly;

    public Yksinpeli(Tekoaly tekoaly) {
        this.tekoaly = tekoaly;
    }

    @Override
    public void pelaa(Scanner lukija) {
        tuomari = new Tuomari();
        while (true) {
            System.out.println("Siirtosi:");
            String pelaajanSiirto = lukija.nextLine();

            if (!onkoOkSiirto(pelaajanSiirto)) {
                break;
            }
            String tekoalynSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tekoalynSiirto);
            
            tuomari.tuomarinToimet(pelaajanSiirto, tekoalynSiirto);
            tekoaly.asetaSiirto(pelaajanSiirto);
        }
        loppuTulostukset();
    }

}
