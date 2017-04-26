package ohtu;

import java.util.Stack;

public class Sovelluslogiikka {
 
    private int tulos;
    private Stack<Integer> tulokset;
    int edellinen;
    
    public Sovelluslogiikka() {
        tulokset = new Stack<>();
    }
 
    public void plus(int luku) {
        tulos += luku;
        tulokset.push(edellinen);
        edellinen = tulos;
    }
     
    public void miinus(int luku) {
        tulos -= luku;
        tulokset.push(edellinen);
        edellinen = tulos;
    }
 
    public void nollaa() {
        tulos = 0;
        edellinen = 0;
        tulokset.clear();
    }
 
    public int tulos() {
        return tulos;
    }
    
    public void peru() {
        tulos = tulokset.pop();
    }
    
    public Stack<Integer> getTulokset() {
        return tulokset;
    }
}