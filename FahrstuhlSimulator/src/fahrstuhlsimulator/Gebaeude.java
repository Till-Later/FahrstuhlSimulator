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
public class Gebaeude implements tick {
    private ArrayList<Etage> etagen;
    private FahrstuhlController fahrstuhlcontroller;
    private float etagenhoehe;
    private int etagenZahl;
    
    public Gebaeude () {
       this.etagen = new ArrayList<Etage>();
       this.fahrstuhlcontroller = new FahrstuhlController();
       this.etagenhoehe = 3F;
       this.etagenZahl = 8;
    }
    
    public void setEtagenhoehe (float etagenhoehe) {
        this.etagenhoehe = etagenhoehe;
    }
    public float getEtagenhoehe () {
        return this.etagenhoehe;
    }
    
    public void setEtagenzahl (int etagenZahl)  {
        this.etagenZahl = etagenZahl;
        if (this.etagenZahl < this.etagen.size()) {
            while (this.etagenZahl < this.etagen.size()) {
                this.etagen.remove(this.etagen.size()-1);
            }
        } else if (this.etagenZahl > this.etagen.size()) {
            while (this.etagenZahl > this.etagen.size()) {
                this.etagen.add(new Etage(this.etagen.size()+1));
            }
        }
    }
    
    public int getEtagenzahl () {
        return this.etagenZahl;
    }
    
    public void starteNotraeumung () {
    
    }
    
    public void tick () {
    
    }
}
