package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {

    protected Tuomari tuomari;
    
    public static Yksinpeli vaikeaYksinpeli() {
        return new Yksinpeli(new ParannettuTekoaly(25));
    }

    public static Kaksinpeli kaksinpeli() {
        return new Kaksinpeli();
    }

    public static Yksinpeli helppoYksinpeli() {
        return new Yksinpeli(new YksinkertainenTekoaly());
    }

    protected boolean onkoOkSiirto(String siirto) {
        return siirto.equals("k") || siirto.equals("p") || siirto.equals("s");
    }

    protected void loppuTulostukset() {
        System.out.println("Kiitos!");
        System.out.println(tuomari.tilanne());
    }
    
    protected abstract void pelaa(Scanner lukija);
}
