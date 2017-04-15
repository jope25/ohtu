package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    IntJoukko eka;
    IntJoukko toka;
    
    @Before
    public void setUp() {
        eka = teeJoukko(1, 2, 3);
        toka = teeJoukko(3, 4, 5);
    }
    
    @Test
    public void yhdisteToimii() {
        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.getJoukkoIlmanNolliaJoukonPeralla();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4, 5};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void leikkausToimii() {
        IntJoukko tulos = IntJoukko.leikkaus(eka, toka);
        int[] vastauksenLuvut = tulos.getJoukkoIlmanNolliaJoukonPeralla();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {3};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void erotusToimii() {
        IntJoukko tulos = IntJoukko.erotus(eka, toka);
        int[] vastauksenLuvut = tulos.getJoukkoIlmanNolliaJoukonPeralla();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaaJoukkoon(luku);
        }

        return joukko;
    }
}
