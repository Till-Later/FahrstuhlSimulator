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
public class Etage implements tick
{
    private int etagenNummer;
    private ArrayList<Person> etagenPersonen;
    private ArrayList<Person> wartezimmerPersonen;
    
    public Etage()
    {
        
    }
    
    @Override
    public void tick() {
    }
    
}
