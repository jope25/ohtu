package ohtu;

import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Kauppa kauppa;
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kalja", 3));
        
        kauppa.aloitaAsiointi();
    }

    @Test
    public void tilisiirrossaKaytetaanOikeaaAsiakastaTilinumeroaJaSummaa() {
        when(viite.uusi()).thenReturn(1);
        
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("arto", "12345");

        verify(pankki).tilisiirto(eq("arto"), anyInt(), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void kahdenTuotteenTilisiirrossaKaytetaanOikeaaAsiakastaTilinumeroaJaSummaa() {
        when(viite.uusi()).thenReturn(1);
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("arto", "12345");

        verify(pankki).tilisiirto(eq("arto"), anyInt(), eq("12345"), anyString(), eq(8));
    }

    @Test
    public void kahdenSamanTuotteenTilisiirrossaKaytetaanOikeaaAsiakastaTilinumeroaJaSummaa() {
        when(viite.uusi()).thenReturn(1);
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("arto", "12345");

        verify(pankki).tilisiirto(eq("arto"), anyInt(), eq("12345"), anyString(), eq(10));
    }

    @Test
    public void kahdenTuotteenJoistaToinenLoppuTilisiirrossaKaytetaanOikeaaAsiakastaTilinumeroaJaSummaa() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "bisse", 4));
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("arto", "12345");

        verify(varasto, times(1)).haeTuote(anyInt());
        verify(pankki).tilisiirto(eq("arto"), anyInt(), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenAsioinnin() {
        when(viite.uusi()).thenReturn(1);
        
        kauppa.lisaaKoriin(1);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("arto", "12345");

        verify(pankki).tilisiirto(eq("arto"), anyInt(), eq("12345"), anyString(), eq(3));
    }

    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiseenMaksuun() {
        when(viite.uusi()).
                thenReturn(12345).
                thenReturn(6789);
        
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("arto", "FI69");
        verify(pankki).tilisiirto(anyString(), eq(12345), anyString(), anyString(), anyInt());
        verify(viite, times(1)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("arto", "FI69");
        verify(pankki).tilisiirto(anyString(), eq(6789), anyString(), anyString(), anyInt());
        verify(viite, times(2)).uusi();
    }
    
    @Test
    public void tuoteVoidaanPoistaaKorista() {
        Tuote tuote = new Tuote(3, "bisse", 4);
        when(varasto.saldo(3)).thenReturn(10);
        when(varasto.haeTuote(3)).thenReturn(tuote);
        
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(3);
        
        verify(varasto, times(1)).palautaVarastoon(tuote);
    }
}
