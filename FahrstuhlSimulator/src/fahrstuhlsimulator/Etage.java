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
    private int etagenNummer;
    protected ArrayList<Person> etagenPersonen;
    protected ArrayList<Person> wartezimmerPersonen;
    
    public Etage(int etagenNummer) {
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
    
    public void bewegePersonInWartezimmer(Person person){
        this.etagenPersonen.add(person);
                }
    
    public void bewegePersonInEtage(Person person){
        this.wartezimmerPersonen.add(person);
    }
               
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
        this.wartezimmerPersonen.addAll(personen);
    }
          
    public Person lassePersonInFahrstuhlEinsteigen(){
            return wartezimmerPersonen.remove(0);
    }
    
    public void tick() {
        this.aktualisierePersonen();
    }
    
}
