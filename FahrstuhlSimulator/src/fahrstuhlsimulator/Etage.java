/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahrstuhlsimulator;

import java.util.ArrayList;

/**
 *
 * @author mex
 */

public class Etage implements tick {
    protected int etagenNummer;
    protected ArrayList<Person> etagenPersonen;
    protected ArrayList<Person> wartezimmerPersonen;
    
    public Etage(int etagenNummer) {
      //System.out.println("Ich bin Etage: " + etagenNummer);
      this.etagenNummer=etagenNummer; 
      this.etagenPersonen = new ArrayList<Person>();
      this.wartezimmerPersonen = new ArrayList<Person>();
    }
    
    public int getEtagenNummer(){
       return this.etagenNummer;
    }
    
    public int getAnzahlDerPersonenImWartezimmer(){
            return this.wartezimmerPersonen.size();
    } 
    
    public int getAnzahlDerPersonInDerEtage(){
            return this.etagenPersonen.size();
    } 
    
    /**
     * Fügt die übergebene Person der Liste etagenPersonen hinzu
     * @param person 
     */
    public void bewegePersonInWartezimmer(Person person){
        this.etagenPersonen.add(person);
                }
    /**
     * Fügt die übergebene Person der Liste wartezimmerPersonen hinzu
     * @param person 
     */
    public void bewegePersonInEtage(Person person){
        this.wartezimmerPersonen.add(person);
    }
    
    /**
     * Personen, deren Aufenthaltsdauer in einer Etage vorbei ist, 
     * werden aus dieser entfernt, löschen ihren Aufenthalt und 
     * werden in dem Wartezimmer der jeweiligen Etage zugefügt.
     * Die anderen verringern ihre Aufenthaltsdauer.
     */
    public void aktualisierePersonen(){       
        for (int i=0; i < etagenPersonen.size(); i++) {
            if (etagenPersonen.get(i).getAktuellenAufenthalt().getAufenthaltsdauer() == 0) {
               etagenPersonen.get(i).loescheAktuellenAufenthalt();
               wartezimmerPersonen.add(etagenPersonen.get(i));
               etagenPersonen.remove(i);
               i++;
               continue;
            } else {
                etagenPersonen.get(i).getAktuellenAufenthalt().verkleinereAufenthaltsdauer();                
            }                         
        }                                 
    }
    
    
    public boolean istFahrstuhlBenoetigt(){
        return !wartezimmerPersonen.isEmpty();
    }

    public void bewegePersonenInEtage(ArrayList<Person> personen){
        this.etagenPersonen.addAll(personen);
    }
          
    public Person lassePersonInFahrstuhlEinsteigen(){
            return wartezimmerPersonen.remove(0);
    }
    
    public void tick() {
        System.out.println("##### Etage "+ this.etagenNummer +":");
        System.out.println("----- im Wartezimmer: " + this.wartezimmerPersonen.size());
        System.out.println("----- in der Etage:   " + this.etagenPersonen.size());
        this.aktualisierePersonen();
       
    }
    
}
