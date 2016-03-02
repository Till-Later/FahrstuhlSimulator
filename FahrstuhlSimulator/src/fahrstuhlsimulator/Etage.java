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
    
    
    public Etage(etagenNummer)
    {
      this.etagenNummer=etagenNummer; 
    }
    
    public int getEtagenNummer(){
       return this.etagenNummer;
    }
    
    public int getAnzahlDerPersonenImWartezimmer(){
            return this.wartezimmerPersonen;
    } 
    
    public int getAnzahlDerPersonInDerEtage(){
            return this.etagenPersonen;
    } 
    
    public void bewegePersonInWartezimmer(Person person){
        this.etagenPersonen.add(person);
                }
    
    public void bewegePersonInEtage(Person person){
        this.wartezimmerPersonen.add(person);
    }
               
    public void aktualisierePersonen(){       
        for (int i=0; i < etagenPersonen.size(); i++) {
             etagenPersonen.get(i).getAktuellenAufenthalt().verkleinereAufenthaltsdauer();
        }
        
        
        
    
    }  
    
    @Override
    public void tick() {
    }
    
}
