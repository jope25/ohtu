package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Peli {

    public static void main(String[] args) {
        tulostaOhjeet();
        
        Scanner lukija = new Scanner(System.in);
        String vastaus = lukija.nextLine();
        
        if (vastaus.endsWith("a")) {
            KPS.kaksinpeli().pelaa(lukija);
        } else if (vastaus.endsWith("b")) {
            KPS.helppoYksinpeli().pelaa(lukija);
        } else if (vastaus.endsWith("c")) {
            KPS.vaikeaYksinpeli().pelaa(lukija);
        }
    }
    
    private static void tulostaOhjeet() {
        System.out.println("\nValitse pelataanko"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\nmuilla valinnoilla lopetataan");
        
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
    }
}
