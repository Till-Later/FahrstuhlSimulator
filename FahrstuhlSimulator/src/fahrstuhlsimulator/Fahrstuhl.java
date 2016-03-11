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
    private int traglast;
    private int zielEtage;
    private int vorherigeEtage;
    private ArrayList<Person> personen;
    //private boolean aktiv;
    private ArrayList<Integer> naechsteEtagen;
    
    
    public Fahrstuhl (int traglast) {
        this.traglast = traglast;
        this.personen = new ArrayList<Person>();
        this.naechsteEtagen = new ArrayList<Integer>();
        this.zielEtage = 1;
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
        
        //System.out.println(aussteiger.size() + " Personen sind ausgestiegen.");
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
        //System.out.println("Fahrstuhl steht gerade in Etage: " + this.zielEtage);
        if (restlicheFahrtzeit == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getAnzahlDerPersonenImFahrstuhl () {
        return personen.size();
    }
    
    public boolean steigeEin (Person person) {
        //System.out.println("Neue Person versucht einzusteigen");
        if (this.traglast >= (this.getAktuelleTraglast()+person.getGewicht())) {
            this.personen.add(person);
            this.addNeuesZiel(person.getAktuellenAufenthalt().getEtagennummer());
            //System.out.println("Neue Person eingestiegen.");
            return true;
        }
        return false;
    }
       
    public boolean kannRechtzeitigBremsen (int etagennnummer) {
        return false;         
    }
    
    public void addNeuesZiel (int etagennummer) {
        if (getZielRanking(etagennummer) != -1) 
            naechsteEtagen.add(getZielRanking(etagennummer), etagennummer);        
    }
    
    public int getZielRanking (int etagennummer) {
        // Wenn das Element sich schon in der Liste befindet, höre einfach auf.
        if (naechsteEtagen.contains(etagennummer)) {
            return -1;        
        }
        
        // Wenn die Etagenliste leer ist, kann das Element einfach in die Liste eingefügt werden.
        if (naechsteEtagen.isEmpty()) {            
            return 0;
        }
        
        // Wenn er rechtzeitig bremsen kann, füge es auf jeden Fall an den Anfang der Liste.
        if (kannRechtzeitigBremsen(etagennummer)) {
            return 0;
        } else {
            // Wenn die Etagenliste nur ein Element enthält und er nicht rechtzeitig bremsen kann, hänge die nächste Etage ans Ende ran.
            if (naechsteEtagen.size() == 1) {
                return naechsteEtagen.size();              
            }
            
            // Der Fahrstuhl fährt abwärts.
            if (naechsteEtagen.get(0) > naechsteEtagen.get(1)) {
                // Füge es in erste Hälfte der Liste ein.
                if (etagennummer < naechsteEtagen.get(0)) {                    
                    int i;
                    for (i = 0; i < getIndexVonKleinstesElementDerListe(naechsteEtagen); i++) {
                        if (etagennummer > naechsteEtagen.get(i+1)) {                            
                            return i+1;
                        }                        
                    }
                    return i+1;
                } else if (etagennummer > naechsteEtagen.get(0)) {
                    // Füge es in die zweite Hälfte der Liste ein.
                    int i;
                    for (i = getIndexVonKleinstesElementDerListe(naechsteEtagen)+1; i < naechsteEtagen.size(); i++) {
                        if (naechsteEtagen.get(i) > etagennummer) {                            
                            return i;
                        }
                    }
                    return i;                    
                }  // Der Fahrstuhl fährt aufwärts
            } else if (naechsteEtagen.get(0) < naechsteEtagen.get(1)) {
                // Füge es in erste Hälfte der Liste ein.
                if (etagennummer < naechsteEtagen.get(0)) {                    
                    int i;
                    for (i = 0; i < getIndexVonGroesstesElementDerListe(naechsteEtagen); i++) {
                        if (etagennummer > naechsteEtagen.get(i+1)) {                            
                            return i+1;
                        }                        
                    }                    
                    return i+1;
                } else if (etagennummer > naechsteEtagen.get(0)) {
                    // Füge es in die zweite Hälfte der Liste ein.
                    int i;
                    for (i = getIndexVonGroesstesElementDerListe(naechsteEtagen)+1; i < naechsteEtagen.size(); i++) {
                        if (naechsteEtagen.get(i) < etagennummer) {
                            return i;
                        }
                    }
                    return i;                    
                }                
            }
            
        }
        return -1;        
    }
    
    public int getIndexVonKleinstesElementDerListe (ArrayList<Integer> liste) {
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) < liste.get(index)) {
                index = i;
            }
        }
        return index;
    }
    
    public int getIndexVonGroesstesElementDerListe (ArrayList<Integer> liste) {
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) > liste.get(index)) {
                index = i;
            }
        }
        return index;
    }
    
    public void printNaechsteEtagen () {
        System.out.print("[");
        for (int i = 0; i < this.naechsteEtagen.size(); i++) {
            System.out.print(this.naechsteEtagen.get(i) + ",");
        }
    }
    
    public void tick () {
        System.out.println("##### Fahrstuhl (" + this.traglast + "kg): " + this.personen.size());
        System.out.print("------ Restliche Fahrtzeit: " + this.restlicheFahrtzeit + " Sekunden \n------ Nächste Etagen: "); 
        this.printNaechsteEtagen();
        System.out.println("]");               
                
        if (this.restlicheFahrtzeit == 0 && !this.naechsteEtagen.isEmpty()) {
            this.restlicheFahrtzeit = 10 + Math.abs(this.naechsteEtagen.get(0)-this.zielEtage)*5;
            this.zielEtage = this.naechsteEtagen.get(0);            
        }
        if (this.restlicheFahrtzeit > 0) {
            this.restlicheFahrtzeit--;
        }
        if (this.restlicheFahrtzeit == 0 && !this.naechsteEtagen.isEmpty()) {
            this.naechsteEtagen.remove(0);            
        }
    }
}
