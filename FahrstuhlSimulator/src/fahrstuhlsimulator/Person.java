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
public class Person {
   private int gewicht;
   private ArrayList <Aufenthalt> aufenthalte = new ArrayList<>(); //das erste Element der Liste ist der aktuelle Aufenthalt
   
   public Person(int gewicht, ArrayList<Aufenthalt> aufenthalte){
       this.gewicht = gewicht;
       this.aufenthalte = aufenthalte;
       //System.out.println("Neue Person mit erster Aufenthalt:" + aufenthalte.get(0).getEtagennummer());
   }
   
   public int getGewicht(){
       return gewicht;
   }
   
   public Aufenthalt getAktuellenAufenthalt(){
       return aufenthalte.get(0);
   }
   
   public void setAktuellenAufenthalt(Aufenthalt aufenthalt){
       aufenthalte.set(0,aufenthalt);
   }
   /** 
    * Aktuelle Aufenthalt der Person wird gelöscht
    */
   public void loescheAktuellenAufenthalt(){
       aufenthalte.remove(0);
   }
   
   
   
}