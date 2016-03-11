package fahrstuhlsimulator;

import java.util.ArrayList;

/**
 *
 * @author Klein
 */
public class Haupteingang
{
    private Boxplot gewichtsVerteilung;
    private Boxplot aufenthaltsVerteilung;
    private Boxplot aufenthaltsDauerVerteilung;
    private int zustromInPpM;
    
    /**
     * Wird für die Methode #erschaffeNeuePersonen() benötigt.
     * Speichert die Sekunden seit der letzten vollen Minute, damit entsprechend
     * dem Zustrom neue Personen generiert werden.
     */
    public int sekunden;
    
    public Haupteingang()
    {
        //System.out.println("Ich bin ein neuer Haupteingang");
        this.gewichtsVerteilung = new Boxplot(40, 70, 80, 90, 120);
        this.aufenthaltsVerteilung = new Boxplot(1, 2, 3, 4, 5);
        
        this.aufenthaltsDauerVerteilung = new Boxplot(150, 600, 1200, 1800, 600*3);
        this.zustromInPpM = 15;
        
        sekunden = 0;
    }
    
    /**
     * Erstellt eine neue Person mit zufälligem Gewicht und zufälligen
     * Aufenthalten mit jeweils zufälligen Aufenthaltszeiten
     * @return eine neue Person
     */
    public Person createPerson()
    {
        int gewicht = this.gewichtsVerteilung.getZufaelligenWert();
        int anzahlAufenthalte = this.aufenthaltsVerteilung.getZufaelligenWert();
        ArrayList<Aufenthalt> aufenthalte = new ArrayList<Aufenthalt>();
        for (int i=0; i<anzahlAufenthalte; i++)
        {
            aufenthalte.add(createAufenthalt());
        }
        aufenthalte.add(new Aufenthalt(1,0));
        //System.out.println("Neue Person: " + aufenthalte.size() + "  Aufenthalte und " + gewicht + " kg.");
        //System.out.println("Nächtes Ziel: " + aufenthalte.get(0).getEtagennummer());
        return new Person(gewicht, aufenthalte);
    }
    
    /**
     * Erstellt einen zufälligen Aufenthalt (in einer zufälligen Etage und mit
     * einer zufälligen Dauer)
     * @return ein zufälliger Aufenthalt
     */
    public Aufenthalt createAufenthalt()
    {
        int etagenNummer = (int) (Math.random() * (FahrstuhlSimulator.gebaeude.getEtagenzahl()-1))+2;
        long aufenthaltsDauer = this.aufenthaltsDauerVerteilung.getZufaelligenWert();
        return new Aufenthalt(etagenNummer, aufenthaltsDauer);
    }
    
    /**
     * Setzt einen neuen Wert für "ZustromInPpM"
     * @param zustromInPpM der neue Wert
     */
    public void setZustromInPpM(int zustromInPpM)
    {
        this.zustromInPpM = zustromInPpM;
    }
    
    /**
     * Liefert den aktuellen Wert für "ZustromInPpM"
     * @return aktueller Wert für "ZustromInPpM"
     */
    public int getZustromInPpM()
    {
        return this.zustromInPpM;
    }
    
    /**
     * Setzt einen neuen Boxplot für die Aufenthaltsdauerverteilung des Gebäudes
     * @param verteilung der neue Boxplot
     */
    public void setAufenthaltsDauerVerteilung(Boxplot verteilung)
    {
        this.aufenthaltsDauerVerteilung = verteilung;
    }
    
    /**
     * Liefert den aktuellen Boxplot für die Aufenthaltsdauerverteilung des Gebäudes
     * @return der aktuelle Boxplot
     */
    public Boxplot getAufenthaltsDauerVerteilung()
    {
        return this.aufenthaltsDauerVerteilung;
    }
    
    /**
     * Setzt einen neuen Boxplot für die Aufenthaltsverteilung des Gebäudes
     * @param verteilung der neue Boxplot
     */
    public void setAufenthaltsVerteilung(Boxplot verteilung)
    {
        this.aufenthaltsVerteilung = verteilung;
    }
    
    /**
     * Liefert den aktuellen Boxplot für die Aufenthaltsverteilung des Gebäudes
     * @return der aktuelle Boxplot
     */
    public Boxplot getAufenthaltsVerteilung()
    {
        return this.aufenthaltsVerteilung;
    }
    
    /**
     * Setzt einen neuen Boxplot für die Gewichtsverteilung des Gebäudes
     * @param verteilung der neue Boxplot
     */
    public void setGewichtsVerteilung(Boxplot verteilung)
    {
        this.gewichtsVerteilung = verteilung;
    }
    
    /**
     * Liefert den aktuellen Boxplot für die Gewichtsverteilung des Gebäudes
     * @return der aktuelle Boxplot
     */
    public Boxplot getGewichtsVerteilung()
    {
        return this.gewichtsVerteilung;
    }
    
    public ArrayList<Person> erschaffeNeuePersonen()
    {
        //Anzahl der zu erstellenden Personen berechnen
        int anzahlPersonen = zustromInPpM/60;
        //zusätzliche Person?
        double rest = zustromInPpM % 60;
        if (((int) (rest/60*sekunden)) > ((int) (rest/60*(sekunden-1))))
            anzahlPersonen++;
        
        //Personen erstellen
        ArrayList<Person> personen = new ArrayList<Person>();
        for (int i=0;i<anzahlPersonen;i++)
            personen.add(createPerson());
        
        sekunden++;
        return personen;
    }
}
