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
    public ArrayList<Etage> etagen;
    public FahrstuhlController fahrstuhlcontroller;
    private float etagenhoehe;
    private int etagenZahl;
       
    public Gebaeude () {
       this.etagen = new ArrayList<Etage>();
       this.fahrstuhlcontroller = new FahrstuhlController();
       this.etagenhoehe = 3F;
       this.etagenZahl = 5;
       this.etagen.add(new Erdgeschoss());
       for (int i = 1; i < this.etagenZahl; i++) {
           this.etagen.add(new Etage(i+1));
       }
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
        // Erdgeschoss neue Personen
        etagen.get(0).tick();
        // Aufenthalte aktualisieren
        for (int i = 1; i < etagen.size();i++) {
            etagen.get(i).tick();
        }
        
        // Personen verschieben / Fahrstühle anfordern
        for (int i = 0; i < etagen.size();i++) {
            if (etagen.get(i).istFahrstuhlBenoetigt()) {
                fahrstuhlcontroller.fordereFahrstuhlAn(i+1);
            }
        }
        
        System.out.println("----------------------------------");
        fahrstuhlcontroller.tick();

        
        for (int i = 0; i < fahrstuhlcontroller.getAngekommeneFahrstuehle().size(); i++) {
            int etage = fahrstuhlcontroller.getAngekommeneFahrstuehle().get(i);
            //System.out.println("Ein Fahrstuhl ist angekommen in Etage " + (etage));
            etagen.get(etage-1).bewegePersonenInEtage(fahrstuhlcontroller.lassePersonenAussteigen(etage));
            while (etagen.get(etage-1).getAnzahlDerPersonenImWartezimmer() > 0) {
                Person person = etagen.get(etage-1).lassePersonInFahrstuhlEinsteigen();
                if (!fahrstuhlcontroller.steigeEin(person, etage)) {
                    etagen.get(etage-1).bewegePersonInWartezimmer(person);
                    return;
                }
            }
        }
        
    }
}
