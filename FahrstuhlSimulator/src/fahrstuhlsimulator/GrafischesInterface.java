/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahrstuhlsimulator;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
        this.setLayout(null);
        this.gebaeude = new gui.Gebaeude(FahrstuhlSimulator.gebaeude.fahrstuhlcontroller.fahrstuehle.size(), FahrstuhlSimulator.gebaeude.getEtagenzahl());
        this.gebaeude.setBounds(0, 0, this.gebaeude.getPreferredSize().width, this.gebaeude.getPreferredSize().height);
        this.add(gebaeude);
        
        this.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                gebaeude.setLocation(0, gebaeude.getY()+e.getWheelRotation());
            }
        });
        
        this.setSize(this.gebaeude.getPreferredSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
