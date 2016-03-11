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
public class Erdgeschoss extends Etage implements tick {
 
    private Haupteingang haupteingang;
    
    public Erdgeschoss () {
        super(1);
        haupteingang = new Haupteingang();
    }
    
    public void pruefeObPersonenInsWartezimmerMuessen(){
       ArrayList <Person> personen = new ArrayList<>();
       personen.addAll(haupteingang.erschaffeNeuePersonen());
   }
    
    public void bewegeNeuePersonenInWartenzimmer(ArrayList<Person> personen){
        this.wartezimmerPersonen.addAll(personen);
    }

    public void bewegePersonInEtage(Person person){
    
    } 
    
    @Override
    public void tick() {
        this.bewegeNeuePersonenInWartenzimmer(haupteingang.erschaffeNeuePersonen());
        System.out.println("##### Erdgeschoss:");
        System.out.println("----- im Wartezimmer: " + this.wartezimmerPersonen.size());
    }
    
}