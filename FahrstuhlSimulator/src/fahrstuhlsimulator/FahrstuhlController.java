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
    public ArrayList <Fahrstuhl> fahrstuehle;
    
    public FahrstuhlController () {
        fahrstuehle = new ArrayList<Fahrstuhl>();
        fahrstuehle.add(new Fahrstuhl(1000));
        fahrstuehle.add(new Fahrstuhl(1000));
        fahrstuehle.add(new Fahrstuhl(2000));
    }
    /**
     * Prüft ob Fahrstuhl gerade angefordert wird
     */
    public boolean istBereitsAngefordert(int etagennummer){
        for (Fahrstuhl f : fahrstuehle) {
            if (f.getZielRanking(etagennummer) == -1) {
                //System.out.println("Fahrstuhl: " + f.getZielRanking(etagennummer));
                return true;
            }
        }
        return false;
    }
    
    /**
     * freier Fahrstuhl ohne Auftrag kann angefordet werden und bekommt neues Ziel
     */
    public void fordereFahrstuhlAn(int etagennummer){
        //System.out.println("Fahrstuhl wird angefordert");
        if (!this.istBereitsAngefordert(etagennummer)) {
            int besterFahrstuhlIndex = 0;
            int besterFahrstuhlRanking = fahrstuehle.get(0).getZielRanking(etagennummer);
            for (int i = 1; i < fahrstuehle.size(); i++) {
                if (besterFahrstuhlRanking > fahrstuehle.get(i).getZielRanking(etagennummer)) {
                    besterFahrstuhlIndex = i;
                    besterFahrstuhlRanking = fahrstuehle.get(i).getZielRanking(etagennummer);
                }                 
            }
            fahrstuehle.get(besterFahrstuhlIndex).addNeuesZiel(etagennummer);
        } else {
            //System.out.println("Fahrstuhl ist bereits angefordert");
        }
    }       

    /**
     * wenn der Fahrstuhl seie Zieletage reicht hat können die Personen aussteigen
     */
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
    
    /**
     * lässt Personen in Fahrstuhl mit gleicher Etage einsteigen
     */
    public boolean steigeEin(Person person, int etage){
        for (int i = 0; i < fahrstuehle.size(); i++) {
            if (fahrstuehle.get(i).getZielEtage() == etage) {
                return fahrstuehle.get(i).steigeEin(person);
            }
        }
        return false;
    }    
    
    
    public void tick(){
        for (int i = 0; i < fahrstuehle.size(); i++) {
            fahrstuehle.get(i).tick();
        }
    }
    
    
}
