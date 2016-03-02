/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahrstuhlsimulator;

/**
 *
 * @author Lehmann
 */
public class Aufenthalt {
    
    
    private int etagennummer;
    private long aufenthaltsdauer;
    Aufenthalt (int etagennummer, long aufenthaltsdauer){
        this.etagennummer = etagennummer;
        this.aufenthaltsdauer = aufenthaltsdauer;
    }
    
    public int getEtagennummer(){
        return etagennummer;
    }
    
    public long getAufenthaltsdauer(){
        return aufenthaltsdauer;
    }
    
    public void setAufenthaltsdauer(long aufenthaltsdauer){
        aufenthaltsdauer = this.aufenthaltsdauer;
    }
    
    public void verkleinereAufenthaltsdauer(){
        aufenthaltsdauer = aufenthaltsdauer - 1;
        
    }
}
