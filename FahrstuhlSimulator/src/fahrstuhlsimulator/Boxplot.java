package fahrstuhlsimulator;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Klein
 */
public class Boxplot
{
    private final int MINIMUM         = 0;
    private final int UNTERES_QUARTIL = 1;
    private final int MEDIAN          = 2;
    private final int OBERES_QUARTIL  = 3;
    private final int MAXIMUM         = 4;
    
    private int minimum;
    private int unteresQuartil;
    private int median;
    private int oberesQuartil;
    private int maximum;
    
    private double spannweite;
    
    private ArrayList<LineMatrix> matrizen;
    
    /**
     * Erstellt einen Boxplot mit Standard-Werten.
     */
    public Boxplot()
    {
        this(10, 30, 40, 50, 70);
    }
    
    /**
     * Erstellt einen Boxplot mit den übergebenen Werten.
     * @param min Minimum: unterste Grenze des Boxplots
     * @param uq unteres Quartil: Grenze für untere 25% der Datenwerte
     * @param med Median; Mittelwert: Grenze für 50% der Datenwerte
     * @param oq oberes Quartil: Grenze für obere 25% der Datenwerte
     * @param max Maximum: oberste Grenze des Boxplots
     */
    public Boxplot(int min, int uq, int med, int oq, int max)
    {
        this.minimum         = min;
        this.unteresQuartil  = uq;
        this.median          = med;
        this.oberesQuartil   = oq;
        this.maximum         = max;
        
        this.matrizen = new ArrayList<LineMatrix>();
        updateMatrizen();
    }
    
    /**
     * Liefert eine ArrayList mit den Linien des Boxplots im LineMatrix-Format.
     * <strong>Achtung:</strong>
     * Die darin gespeicherten Werte entsprechen <b>nicht</b> den Kenngrößen
     * des Boxplots.
     * @return eine ArrayList mit den Linien des Boxplots als LineMatrix-Objekte
     */
    public ArrayList<LineMatrix> getLinien()
    {
        return matrizen;
    }
    
    /**
     * Prüft, ob die übergebene Linie korrekt steht, also zwischen den Linien
     * links und rechts. Beispiel: Der Median muss zwischen dem unteren und dem
     * oberen Quartil liegen.
     * @param lm die zu überprüfende Linie
     * @return true, wenn die Linie korrekt steht
     */
    public boolean pruefeLinie(LineMatrix lm)
    {
        int index = matrizen.indexOf(lm);
        if (index != 0) //linke Seite prüfen
            if (lm.getX() <= matrizen.get(index-1).getX())
                return false;
        if (index != 4) //rechte Seite prüfen
            if (lm.getX() >= matrizen.get(index+1).getX())
                return false;
        return true;
    }
    
    /**
     * Prüft alle Linien auf korrekte Stellung. Siehe #pruefeLinie
     * @return true, wenn alle Linien korekt stehen
     * @see #pruefeLinie(fahrstuhlsimulatortestbereich.Boxplot.LineMatrix) 
     */
    public boolean pruefeWerte()
    {
        for (LineMatrix lm : matrizen)
            if (!pruefeLinie(lm))
                return false;
        return true;
    }
    
    /**
     * Liefert die Linie, die am Nächsten zum übergebenen Punkt liegt.
     * @param p ein Punkt
     * @return die nächste Linie oder null, wenn keine Linie in der Nähe liegt (siehe LineMatrix#enthaeltPunkt)
     * @see fahrstuhlsimulatortestbereich.Boxplot.LineMatrix#enthaeltPunkt(java.awt.Point) 
     */
    public LineMatrix getLinieAt(Point p)
    {
        for (LineMatrix lm : matrizen)
            if (lm.enthaeltPunkt(p))
                return lm;
        return null;
    }
    
    /**
     * Aktualisiert die Positionen der Linien, zum Beispiel nach Änderung einer
     * oder mehrerer Kenngrößen des Boxplots.
     * @see fahrstuhlsimulatortestbereich.Boxplot.LineMatrix
     */
    private void updateMatrizen()
    {
        matrizen.clear();
        
        spannweite = maximum-minimum;
        
        matrizen.add(new LineMatrix(15, 20, 60, false)); //Minimum
        
        matrizen.add(new LineMatrix((int) ((unteresQuartil-minimum)/spannweite*300)+15, 15, 65, true));
        matrizen.add(new LineMatrix((int) ((median        -minimum)/spannweite*300)+15, 15, 65, true));
        matrizen.add(new LineMatrix((int) ((oberesQuartil -minimum)/spannweite*300)+15, 15, 65, true));
        
        matrizen.add(new LineMatrix(315, 20, 60, false)); //Maximum
    }
    
    /**
     * Berechnet aus der x-Koordinate den aktuellen Wert für den Boxplot.
     * @param xPos der x-Wert der Linie
     * @return der neue Wert für die entsprechende Kenngröße des Boxplots
     */
    public double berechneAbsolutenWert(int xPos)
    {
        return (((xPos-15)*spannweite)/300)+minimum;
    }
    
    /**
     * Setzt einen neuen Wert für die Kenngröße "Minimum" des Boxplots.
     * @param min der neue Wert
     */
    public void setMinimum(int min)
    {
        minimum = min;
        updateMatrizen();
    }
    
    /**
     * Setzt einen neuen Wert für die Kenngröße "unteres Quartal" des Boxplots.
     * @param uq der neue Wert
     */
    public void setUnteresQuartil(int uq)
    {
        unteresQuartil = uq;
        updateMatrizen();
    }
    
    /**
     * Setzt einen neuen Wert für die Kenngröße "Median" des Boxplots.
     * @param med der neue Wert
     */
    public void setMedian(int med)
    {
        median = med;
        updateMatrizen();
    }
    
    /**
     * Setzt einen neuen Wert für die Kenngröße "oberes Quartil" des Boxplots.
     * @param oq der neue Wert
     */
    public void setOberesQuartil(int oq)
    {
        oberesQuartil = oq;
        updateMatrizen();
    }
    
    /**
     * Setzt einen neuen Wert für die Kenngröße "Maximum" des Boxplots.
     * @param max der neue Wert
     */
    public void setMaximum(int max)
    {
        maximum = max;
        updateMatrizen();
    }
    
    /**
     * Liefert den Wert für die Kenngröße "Minimum" des Boxplots.
     * @return der aktuelle Wert
     */
    public int getMinimum()
    {
        return minimum;
    }
    
    /**
     * Liefert den Wert für die Kenngröße "unteres Quartil" des Boxplots.
     * @return der aktuelle Wert
     */
    public int getUnteresQuartil()
    {
        return unteresQuartil;
    }
    
    /**
     * Liefert den Wert für die Kenngröße "Median" des Boxplots.
     * @return der aktuelle Wert
     */
    public int getMedian()
    {
        return median;
    }
    
    /**
     * Liefert den Wert für die Kenngröße "oberes Quartil" des Boxplots.
     * @return der aktuelle Wert
     */
    public int getOberesQuartil()
    {
        return oberesQuartil;
    }
    
    /**
     * Liefert den Wert für die Kenngröße "Maximum" des Boxplots.
     * @return der aktuelle Wert
     */
    public int getMaximum()
    {
        return maximum;
    }
    
    /**
     * Liefert einen zufälligen Wert, entsprechend der Wahrscheinlichkeiten,
     * die der Boxplot repräsentiert.
     * @return einen zufälligen Wert
     */
    public int getZufaelligenWert()
    {
        //zufälliges Abteil des Boxplots wählen, aus dem der Zufallswert kommen soll
        //Abteile: zwischen Minimum und unteres Quartil oder zwischen unteres Quartil und Median oder ...
        int abteil = (int) (Math.random()*4);
        
        int untereGrenze,obereGrenze;
        if (abteil == 0)
        {
            untereGrenze = this.minimum;
            obereGrenze  = this.unteresQuartil;
        } else if (abteil == 1)
        {
            untereGrenze = this.unteresQuartil;
            obereGrenze  = this.median;
        } else if (abteil == 2)
        {
            untereGrenze = this.median;
            obereGrenze  = this.oberesQuartil;
        } else {
            untereGrenze = this.oberesQuartil;
            obereGrenze = this.maximum;
        }
        int breiteDesAbteils = obereGrenze - untereGrenze;
        
        return ((int) (Math.random() * breiteDesAbteils)) + untereGrenze;
    }
    
    /**
     * Eine LineMatrix enthält die x- und y-Werte einer Linie im Boxplot.
     * Sie enthält Methoden zum Verändern der x-Werte (Verschieben der Linie)
     * und zum überprüfen, ob ein übergebener Punkt in der Nähe dieser Linie
     * liegt.
     * @author Kevin Klein
     */
    public class LineMatrix extends Object
    {
        private int x;

        private int yStart;
        private int yEnde;

        private boolean beweglich;

        /**
         * Erstellt eine LineMatrix mit den übergebenen Werten.
         * @param x1
         * @param y1
         * @param y2
         * @param bew gibt an, ob die Linie auf dem Panel verschoben werden darf
         */
        LineMatrix(int x1, int y1, int y2, boolean bew)
        {
            x         = x1;
            yStart    = y1;
            yEnde     = y2;
            beweglich = bew;
        }
        /**
         * Überprüft, ob ein Punkt in der Nähe der Linie liegt. "In der Nähe"
         * heißt hier: +5 Pixel oder -5 Pixel in horizontaler Richtung.
         * @param p der zu Überprüfende Punkt
         * @return true, wenn der Punkt in der Nähe der Linie liegt
         */
        public boolean enthaeltPunkt(Point p)
        {
            return (p.x >= x-5) && (p.x <= x+5);
        }
        /**
         * Ändert den x-Wert zum übergebenen Wert und verschiebt damit
         * die Linie beim nächsten Zeichnen.
         * @param newXPos der x-Wert der neuen Position der Linie
         */
        public void verschiebe(int newXPos)
        {
            x = newXPos;
        }
        /**
         * Liefert den x-Wert der Linie.
         * @return x-Wert
         */
        public int getX()
        {
            return x;
        }
        /**
         * Liefert den y-Wert des Startpunktes der Linie.
         * @return y-Wert des Startpunktes der Linie
         */
        public int getYStart()
        {
            return yStart;
        }
        /**
         * Liefert den y-Wert des Endpunktes der Linie.
         * @return y-Wert des Endpunktes der Linie
         */
        public int getYEnde()
        {
            return yEnde;
        }
        /**
         * Gibt an, ob die Linie auf dem Panel verschoben werden darf
         * @return 
         */
        public boolean istBeweglich()
        {
            return beweglich;
        }
    }
}