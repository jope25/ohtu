package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class StaticsticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void hakuPalauttaaNullKunPelaajaEiLoydy() {
        assertNull(stats.search("Tikkanen"));
    }
    
    @Test
    public void hakuPalauttaaOikeanPelaajan() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void joukkuehakuPalauttaaTyhjanListanJosJoukkueEiLoydy() {
        assertTrue(stats.team("SJS").isEmpty());
    }
    
    @Test
    public void joukkuehakuToimii() {
        assertEquals(3, stats.team("EDM").size());
        assertEquals(1, stats.team("PIT").size());
    }
    
    @Test
    public void pisteporssiPalauttaaOikeanMaaranPelaajia() {
        assertEquals(3, stats.topScorers(3).size());
    }
    
    @Test
    public void pisteporssiToimii() {
        List<Player> porssi = stats.topScorers(3);
        assertEquals("Gretzky", porssi.get(0).getName());
        assertEquals("Lemieux", porssi.get(1).getName());
        assertEquals("Yzerman", porssi.get(2).getName());
    }
    
    @Test
    public void tyhjaSyotePalauttaaTyhjanJoukkueen() {
        assertTrue(stats.team("").isEmpty());
    }
}
