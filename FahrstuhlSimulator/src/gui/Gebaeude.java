package gui;

import fahrstuhlsimulator.Daten;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Klein
 */
public class Gebaeude extends JPanel implements Runnable, fahrstuhlsimulator.tick
{
    private final int FS_ABSTAND = 10; //Abstand zwischen den Fahrstühlen
    
    private final int GEBAEUDE_RAND_ABSTAND_NORD = 10;
    private final int GEBAEUDE_RAND_ABSTAND_WEST = 50;
    private final int GEBAEUDE_RAND_ABSTAND_SUED = 20;
    private final int GEBAEUDE_RAND_ABSTAND_OST  = 50;
    
    private final int ETAGEN_HOEHE = 100;
    private final int E_INFO_BREITE = 100;
    private final int W_RAUM_BREITE = 150;
    private final int FAHRSTUHL_BREITE = 75;
    private final int DACH_HOEHE = 100;
    
    private int hoehe;
    private int breite;
    
    private JButton playPause;
    private JButton stop;
    private JButton einstellungen;
    private JSlider speedSlider;
    private JLabel speedLabel;
    
    private int[] fahrstuhlPositionen;
    
    int anzahlFahrstuehle;
    int anzahlEtagen;
    
    private Map<String,JLabel> personenAnzahlLabels;
    
    public Gebaeude(int anzahlFahrstuehle, int anzahlEtagen)
    {
        this.anzahlFahrstuehle = anzahlFahrstuehle;
        this.anzahlEtagen = anzahlEtagen;
        
        //Breite der Etagen berechnen
        this.breite = E_INFO_BREITE + W_RAUM_BREITE;
        
        //Breite des Gebäudes berechnen
        for (int i=0;i<anzahlFahrstuehle;i++)
        {
            this.breite += FAHRSTUHL_BREITE;
            this.breite += FS_ABSTAND;
        }
        
        //Höhe des Gebäudes berechnen (ohne Dach)
        this.hoehe = anzahlEtagen*ETAGEN_HOEHE;
        
        this.setSize(breite+GEBAEUDE_RAND_ABSTAND_WEST+GEBAEUDE_RAND_ABSTAND_OST,
                     hoehe+DACH_HOEHE+GEBAEUDE_RAND_ABSTAND_NORD+GEBAEUDE_RAND_ABSTAND_SUED);
        this.setPreferredSize(new Dimension(breite+GEBAEUDE_RAND_ABSTAND_WEST+GEBAEUDE_RAND_ABSTAND_OST,
                                 hoehe+DACH_HOEHE+GEBAEUDE_RAND_ABSTAND_NORD+GEBAEUDE_RAND_ABSTAND_SUED));
        
        //x-Werte der Positionen der Fahrstühle berechnen
        this.fahrstuhlPositionen = new int[anzahlFahrstuehle];
        for (int i=0;i<anzahlFahrstuehle;i++)
        {
            fahrstuhlPositionen[i] = GEBAEUDE_RAND_ABSTAND_WEST+breite-(i+1)*(FAHRSTUHL_BREITE+FS_ABSTAND);
        }
        
        this.personenAnzahlLabels = new HashMap<String,JLabel>();
        for (int i=0;i<this.anzahlEtagen;i++)
        {
            JLabel a = new JLabel("0");
            JLabel b = new JLabel("0");
            a.setFont(a.getFont().deriveFont(30f));
            b.setFont(b.getFont().deriveFont(30f));
            this.add(a);
            this.add(b);
            this.personenAnzahlLabels.put("etage"+i, a);
            this.personenAnzahlLabels.put("wartezimmer"+i, b);
        }
        for (int i=0;i<this.anzahlFahrstuehle;i++)
        {
            JLabel a= new JLabel("0");
            a.setFont(a.getFont().deriveFont(30f));
            this.personenAnzahlLabels.put("fahrstuhl"+i, a);
            this.add(a);
        }
        this.setLayout(null);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        int gebaeudeObenYPos = GEBAEUDE_RAND_ABSTAND_NORD+DACH_HOEHE;
        
        //Grundviereck für das Gebäude zeichnen
        //                x-Position              y-Position
        g.drawRect(GEBAEUDE_RAND_ABSTAND_WEST, gebaeudeObenYPos, breite, hoehe);
        
        //Eckpunkte des Dreiecks berechnen
        int[] xPos = new int[3];
        int[] yPos = new int[3];
        
        xPos[0] = GEBAEUDE_RAND_ABSTAND_WEST;
        yPos[0] = gebaeudeObenYPos;
        xPos[1] = GEBAEUDE_RAND_ABSTAND_WEST+breite;
        yPos[1] = gebaeudeObenYPos;
        xPos[2] = GEBAEUDE_RAND_ABSTAND_WEST+(breite/2);
        yPos[2] = GEBAEUDE_RAND_ABSTAND_NORD;
        
        //Grunddreieck für Dach zeichnen
        g.drawPolygon(xPos, yPos, 3);
        
        //linke Abtrennung des Wartebereichs zeichnen
        Color defaultColor = g.getColor();
        g.setColor(new Color(170,170,170));
        g.drawLine(GEBAEUDE_RAND_ABSTAND_WEST+E_INFO_BREITE, gebaeudeObenYPos+1, GEBAEUDE_RAND_ABSTAND_WEST+E_INFO_BREITE, gebaeudeObenYPos+hoehe);
        g.setColor(defaultColor);
        
        //Etagen zeichen
        int aktEtageYPos = gebaeudeObenYPos;
        for (int i=0;i<anzahlEtagen;i++)
        {
            int etage = anzahlEtagen-i-1;
            this.personenAnzahlLabels.get("etage"+etage).setBounds(GEBAEUDE_RAND_ABSTAND_WEST+40, aktEtageYPos+30, 100, 40);
            this.personenAnzahlLabels.get("wartezimmer"+etage).setBounds(GEBAEUDE_RAND_ABSTAND_WEST+170, aktEtageYPos+30, 100, 40);
            aktEtageYPos += ETAGEN_HOEHE;
            g.drawLine(GEBAEUDE_RAND_ABSTAND_WEST, aktEtageYPos, GEBAEUDE_RAND_ABSTAND_WEST+breite, aktEtageYPos);
        }
        
        //Fahrstuhlschächte zeichnen
        int a=0;
        for (int pos : fahrstuhlPositionen)
        {
            g.drawLine(pos, gebaeudeObenYPos, pos, gebaeudeObenYPos+hoehe);
            g.drawLine(pos+FAHRSTUHL_BREITE, gebaeudeObenYPos, pos+FAHRSTUHL_BREITE, gebaeudeObenYPos+hoehe);
            this.personenAnzahlLabels.get("fahrstuhl"+a).setBounds(pos, aktEtageYPos-ETAGEN_HOEHE, 100, 40);
            a++;
        }
        
        //Bilder laden
        try {
            g.drawImage(ImageIO.read(new File("img/play.png")), 15, 15, null);
            g.drawImage(ImageIO.read(new File("img/pause.png")), 45, 15, null);
            g.drawImage(ImageIO.read(new File("img/stop.png")), 75, 15, null);
            g.drawImage(ImageIO.read(new File("img/einstellungen.png")), 105, 15, null);
        } catch (IOException ex) {System.out.println("aa");ex.printStackTrace();}
    }

    @Override
    public void run()
    {
        while(true)
        {
            repaint();
            System.out.println("repaint");
            try {
                synchronized (this){
                    this.wait(1000);
                }
            } catch (InterruptedException ex) {}
        }
    }
    
    @Override
    public void tick()
    {
        for (int i=0;i<this.anzahlEtagen;i++)
        {
            this.personenAnzahlLabels.get("etage"+i).setText(""+Daten.getAnzahlPersonenInEtage(i+1));
            this.personenAnzahlLabels.get("wartezimmer"+i).setText(""+Daten.getAnzahlPersonenInWartezimmer(i+1));
        }
        for (int i=0;i<this.anzahlFahrstuehle;i++)
        {
            this.personenAnzahlLabels.get("fahrstuhl"+i).setText(""+Daten.getAnzahlPersonenInFahrstuhl(i+1));
        }
        repaint();
    }
    
    public static void main(String[] args)
    {
        new fahrstuhlsimulator.GrafischesInterface();
    }
}
