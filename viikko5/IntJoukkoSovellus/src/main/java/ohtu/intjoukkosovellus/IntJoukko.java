package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int OLETUSKOKO = 5,
            OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] joukko;
    private int alkioidenMaara;

    public IntJoukko() {
        this(OLETUSKOKO, OLETUSKASVATUS);
    }

    public IntJoukko(int koko) {
        this(koko, OLETUSKASVATUS);

    }

    public IntJoukko(int koko, int kasvatuskoko) {
        if (koko < 0) {
            koko = OLETUSKOKO;
        }
        if (kasvatuskoko < 0) {
            kasvatuskoko = OLETUSKASVATUS;
        }
        joukko = new int[koko];
        alkioidenMaara = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaaJoukkoon(int luku) {
        if (loytyyJoukosta(luku)) {
            return false;
        }
        joukko[alkioidenMaara] = luku;
        alkioidenMaara++;
        if (alkioidenMaara == joukko.length) {
            kasvataJoukonKokoa();
        }
        return true;
    }

    public boolean poistaJoukosta(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == joukko[i]) {
                taytaPoistetunKohta(i);
                alkioidenMaara--;
                return true;
            }
        }
        return false;
    }

    public boolean loytyyJoukosta(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public int getAlkioidenMaara() {
        return alkioidenMaara;
    }

    public int[] getJoukkoIlmanNolliaJoukonPeralla() {
        return Arrays.copyOf(joukko, alkioidenMaara);
    }

    public static IntJoukko yhdiste(IntJoukko eka, IntJoukko toka) {
        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < eka.alkioidenMaara; i++) {
            yhdiste.lisaaJoukkoon(eka.joukko[i]);
        }
        for (int i = 0; i < toka.alkioidenMaara; i++) {
            yhdiste.lisaaJoukkoon(toka.joukko[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko eka, IntJoukko toka) {
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < eka.alkioidenMaara; i++) {
            for (int j = 0; j < toka.alkioidenMaara; j++) {
                if (eka.joukko[i] == toka.joukko[j]) {
                    leikkaus.lisaaJoukkoon(toka.joukko[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko eka, IntJoukko toka) {
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < eka.alkioidenMaara; i++) {
            erotus.lisaaJoukkoon(eka.joukko[i]);
        }
        for (int i = 0; i < toka.alkioidenMaara; i++) {
            erotus.poistaJoukosta(toka.joukko[i]);
        }
        return erotus;
    }

    private void kasvataJoukonKokoa() {
        joukko = Arrays.copyOf(joukko, alkioidenMaara + kasvatuskoko);
    }

    private void taytaPoistetunKohta(int poistetunKohta) {
        for (int i = poistetunKohta; i < alkioidenMaara - 1; i++) {
            joukko[i] = joukko[i + 1];
        }
    }

    @Override
    public String toString() {
        if (alkioidenMaara == 0) {
            return "{}";
        }
        String s = "{";
        for (int i = 0; i < alkioidenMaara; i++) {
            s += joukko[i] + ", ";
        }
        return s.substring(0, s.length() - 2) + "}";
    }
}
