/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahrstuhlsimulator;

import javax.swing.JFrame;

/**
 *
 * @author Lehmann
 */
public class GrafischesInterface extends JFrame implements tick
{
    private gui.Gebaeude gebaeude;
    
    public GrafischesInterface()
    {
        super("Fahrstuhlsimulator");
        this.gebaeude = new gui.Gebaeude(FahrstuhlSimulator.gebaeude.fahrstuhlcontroller.fahrstuehle.size(), FahrstuhlSimulator.gebaeude.getEtagenzahl());
        this.add(gebaeude);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void tick()
    {
        gebaeude.tick();
    }
    
    public static void main(String[] args)
    {
        new GrafischesInterface();
    }
}
