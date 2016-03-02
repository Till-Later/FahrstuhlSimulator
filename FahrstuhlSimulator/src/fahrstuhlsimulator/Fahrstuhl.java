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
public class Fahrstuhl implements tick {
    private int restlicheFahrtzeit;
    private float traglast;
    private int zielEtage;
    private int vorherigeEtage;
    private ArrayList<Person> personen;
    //private boolean aktiv;
    private ArrayList<Integer> naechsteEtagen;
    
    
    public Fahrstuhl (float traglast) {
        this.traglast = traglast;
        personen = new ArrayList<Person>();
        naechsteEtagen = new ArrayList<Integer>();
    } 
    
    public void setNeueZielEtage (int etagennummer) {
        this.vorherigeEtage = this.zielEtage;
        this.zielEtage = etagennummer;
        
    }
    
    public int getZielEtage () {
        return this.zielEtage;
    }
    
    public ArrayList<Person> getAussteigendePersonen () {
        ArrayList<Person> aussteiger = new ArrayList<Person>();
        
        int i = 0;
        while (i < personen.size()) {
            if (zielEtage == personen.get(i).getAktuellenAufenthalt().getEtagennummer()) {
                aussteiger.add(personen.get(i));
                personen.remove(i);
            } else {
                i++;
            }
        }
        
        return aussteiger;
    }
    
    public int getAktuelleTraglast () {
        int gewicht = 0;
        
        for (int i = 0; i < personen.size(); i++) {
            gewicht += personen.get(i).getGewicht();
        }
        
        return gewicht;
    }
    
    public boolean getStehtGerade () {
        if (restlicheFahrtzeit == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getAnzahlDerPersonenImFahrstuhl () {
        return personen.size();
    }
       
    public boolean kannRechtzeitigBremsen () {
        // Erst wenn die Simulation mit Geschwindigkeiten arbeitet, kann geprüft werden, ob der Fahrstuhl rechtzeitig bremsen könnte. 
        // Solange wird davon ausgegangen, dass er es nicht kann.
        return false;         
    }
    
    public void addNeuesZiel (int etagennummer) {
        if (naechsteEtagen.size() == 0) {
            naechsteEtagen.add(etagennummer);
        } else {
            if (naechsteEtagen.get(naechsteEtagen.size()-1) > etagennummer) {
                
            }
        }
    
    }
    
    public void tick () {
        
    }
}
