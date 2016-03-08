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
public class Etage implements tick
{
    private int etagenNummer;
    private ArrayList<Person> etagenPersonen;
    private ArrayList<Person> wartezimmerPersonen;
    
    
    public Etage(int etagenNummer)
    {
      this.etagenNummer=etagenNummer; 
      etagenPersonen = new ArrayList<Person>();
      wartezimmerPersonen = new ArrayList<Person>();
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
    
    public boolean istFahrstuhleBenoetigt() {
        if (wartezimmerPersonen.size() > 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public void tick() {
    }
    
}
