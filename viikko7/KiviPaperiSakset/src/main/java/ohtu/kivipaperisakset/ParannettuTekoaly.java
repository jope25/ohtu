package ohtu.kivipaperisakset;

import java.util.Arrays;

public class ParannettuTekoaly implements Tekoaly {

    private String[] muisti;
    private int vapaaMuistiIndeksi;
    int kivi;
    int paperi;
    int sakset;

    public ParannettuTekoaly(int muistinKoko) {
        muisti = new String[muistinKoko];
        vapaaMuistiIndeksi = 0;
    }

    @Override
    public void asetaSiirto(String siirto) {
        lisaaMuistiaJosTarpeellista();

        muisti[vapaaMuistiIndeksi] = siirto;
        vapaaMuistiIndeksi++;
    }

    @Override
    public String annaSiirto() {
        if (vapaaMuistiIndeksi == 0) {
            return "k";
        }
        laskeSiirto();
        return siirto();
    }

    private void laskeSiirto() {
        String viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];

        asetaLaskuritNollaan();

        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto.equals(muisti[i])) {
                String seuraava = muisti[i + 1];
                lisaaSiirto(seuraava);
            }
        }
    }

    private String siirto() {
        if (kivi > paperi && kivi > sakset) {
            return "p";
        } else if (paperi > kivi && paperi > sakset) {
            return "s";
        } else {
            return "k";
        }
    }

    private void lisaaMuistiaJosTarpeellista() {
        if (vapaaMuistiIndeksi == muisti.length) {
            muisti = Arrays.copyOf(muisti, muisti.length * 2);
        }
    }

    private void asetaLaskuritNollaan() {
        kivi = 0;
        paperi = 0;
        sakset = 0;
    }

    private void lisaaSiirto(String siirto) {
        switch (siirto) {
            case "k":
                kivi++;
                break;
            case "p":
                paperi++;
                break;
            default:
                sakset++;
                break;
        }
    }
}
