package ohtu.kivipaperisakset;

import java.util.Random;

public class YksinkertainenTekoaly implements Tekoaly {
    
    private Random arpoja;
    
    public YksinkertainenTekoaly() {
        this.arpoja = new Random();
    }
    
    @Override
    public String annaSiirto() {
        int siirto = arpoja.nextInt(3);
        switch (siirto) {
            case 0:
                return "k";
            case 1:
                return "p";
            default:
                return "s";
        }
    }

    @Override
    public void asetaSiirto(String siirto) {
    }
}
