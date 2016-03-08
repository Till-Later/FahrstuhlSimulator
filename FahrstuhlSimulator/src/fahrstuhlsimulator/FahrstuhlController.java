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
            int besterFahrstuhlRanking = fahrstuehle.get(0).getZielRanking(etagennummer);
            for (int i = 1; i < fahrstuehle.size(); i++) {
                if (besterFahrstuhlRanking > fahrstuehle.get(i).getZielRanking(etagennummer)) {
                    besterFahrstuhlIndex = i;
                    besterFahrstuhlRanking = fahrstuehle.get(i).getZielRanking(etagennummer);
                }                 
            }
            fahrstuehle.get(besterFahrstuhlIndex).addNeuesZiel(etagennummer);
        }
    }       

    
    public ArrayList<Person> lassePersonenAussteigen(int etagennummer){
        for (int i = 0; i < fahrstuehle.size(); i++) {
            if (fahrstuehle.get(i).getZielEtage() == etagennummer) {
                return fahrstuehle.get(i).getAussteigendePersonen();
            }
        }
        return null;
    }
    
    
    public ArrayList<Integer> getAngekommeneFahrstuehle() {
        ArrayList<Integer> etagen = new ArrayList<Integer>();
        for (int i = 0; i < fahrstuehle.size(); i++) {
            if (fahrstuehle.get(i).getStehtGerade()) {
                etagen.add(fahrstuehle.get(i).getZielEtage());
            }
        }
        
        return etagen;
    }
    
    
    public boolean steigeEin(Person person){
        for (int i = 0; i < fahrstuehle.size(); i++) {
            if (fahrstuehle.get(i).getZielEtage() == person.getAktuellenAufenthalt().getEtagennummer()) {
                return fahrstuehle.get(i).steigeEin(person);
            }
        }
        return false;
    }    
    
    
    public void tick(){
        
    }
    
    
}
