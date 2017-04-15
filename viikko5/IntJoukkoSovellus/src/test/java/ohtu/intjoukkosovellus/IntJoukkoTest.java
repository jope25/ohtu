package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaaJoukkoon(10);
        joukko.lisaaJoukkoon(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaaJoukkoon(4);
        assertEquals(3, joukko.getAlkioidenMaara());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaaJoukkoon(10);
        joukko.lisaaJoukkoon(3);
        assertEquals(2, joukko.getAlkioidenMaara());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.loytyyJoukosta(10));
        assertFalse(joukko.loytyyJoukosta(5));
        assertTrue(joukko.loytyyJoukosta(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poistaJoukosta(3);
        assertFalse(joukko.loytyyJoukosta(3));
        assertEquals(1, joukko.getAlkioidenMaara());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaaJoukkoon(55);
        joukko.poistaJoukosta(10);
        joukko.lisaaJoukkoon(99);

        int[] vastaus = joukko.getJoukkoIlmanNolliaJoukonPeralla();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaaJoukkoon(luku);
        }
        assertEquals(14, joukko.getAlkioidenMaara());
        assertTrue(joukko.loytyyJoukosta(11));
        joukko.poistaJoukosta(11);
        assertFalse(joukko.loytyyJoukosta(11));
        assertEquals(13, joukko.getAlkioidenMaara());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
    
    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla(){
        joukko = new IntJoukko();
        joukko.lisaaJoukkoon(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla(){
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }     
}
