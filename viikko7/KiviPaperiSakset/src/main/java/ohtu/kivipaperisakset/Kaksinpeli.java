package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Kaksinpeli extends KPS {

    @Override
    public void pelaa(Scanner lukija) {
        tuomari = new Tuomari();

        while (true) {
            System.out.println("Ensimmäisen pelaajan siirto:");
            String ekanSiirto = lukija.nextLine();

            System.out.println("Toisen pelaajan siirto:");
            String tokanSiirto = lukija.nextLine();

            if (!onkoOkSiirto(ekanSiirto) || !onkoOkSiirto(tokanSiirto)) {
                break;
            }
            tuomari.tuomarinToimet(ekanSiirto, tokanSiirto);
        }
        loppuTulostukset();
    }

}
