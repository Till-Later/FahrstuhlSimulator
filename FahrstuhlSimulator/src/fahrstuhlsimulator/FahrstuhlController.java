/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahrstuhlsimulator;

import java.util.ArrayList;

/**
 *
 * @author Lehmann
 */
public class FahrstuhlController implements tick {
    private ArrayList <Fahrstuhl> fahrstuehle = new ArrayList<>();
    
    public boolean istBereitsAngefordert(int etagennummer){
        for (Fahrstuhl f : fahrstuehle) {
            if (f.getZielRanking(etagennummer) == -1) {
                return true;
            }
        }
        return false;
    }
    
    
    public void fordereFahrstuhlAn(int etagennummer){
        if (!istBereitsAngefordert(etagennummer)) {
            int besterFahrstuhlIndex = 0;
            for (int i = 0; i < fahrstuehle.size(); i++) {
                
            }
        }
    }
    
    public void entferneFahrziel(){
        
    }
    /*
    public ArrayList<Person> lassePersonenAussteigen(int etagennummer){
        return
    }
    
    public void getAngekommeneFahrstuehle(){
        return 
    }
    
    public boolean steigeEin(person){
        return
    }
    
    */
    
    public void tick(){
        
    }
    
    
}
