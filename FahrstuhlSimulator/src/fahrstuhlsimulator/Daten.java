package fahrstuhlsimulator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Klein
 */
public class Daten
{
    public static Map<Integer,Integer> getAnzahlPersonenInEtagen()
    {
        Map<Integer,Integer> ausgabe = new HashMap<Integer,Integer>();
        for (Etage e : FahrstuhlSimulator.gebaeude.etagen)
            ausgabe.put(e.getEtagenNummer(), e.getAnzahlDerPersonInDerEtage());
        return ausgabe;
    }

    public static int getAnzahlPersonenInEtage(int etage)
    {
        for (Etage e : FahrstuhlSimulator.gebaeude.etagen)
            if (e.getEtagenNummer() == etage)
                return e.getAnzahlDerPersonInDerEtage();
        return -1;
    }

    public static Map<Integer,Integer> getAnzahlPersonenInWartezimmern()
    {
        Map<Integer,Integer> ausgabe = new HashMap<Integer,Integer>();
        for (Etage e : FahrstuhlSimulator.gebaeude.etagen)
            ausgabe.put(e.getEtagenNummer(), e.getAnzahlDerPersonenImWartezimmer());
        return ausgabe;
    }

    public static int getAnzahlPersonenInWartezimmer(int etage)
    {
        for (Etage e : FahrstuhlSimulator.gebaeude.etagen)
            if (e.getEtagenNummer() == etage)
                return e.getAnzahlDerPersonenImWartezimmer();
        return -1;
    }

    public static Map<Integer,Integer> getAnzahlPersonenInFahrstuehlen()
    {
        Map<Integer,Integer> ausgabe = new HashMap<Integer,Integer>();
        for (Fahrstuhl f : FahrstuhlSimulator.gebaeude.fahrstuhlcontroller.fahrstuehle)
            ausgabe.put(FahrstuhlSimulator.gebaeude.fahrstuhlcontroller.fahrstuehle.indexOf(f), f.getAnzahlDerPersonenImFahrstuhl());
        return ausgabe;
    }

    public static int getAnzahlPersonenInFahrstuhl(int fahrstuhl)
    {
        for (Fahrstuhl f : FahrstuhlSimulator.gebaeude.fahrstuhlcontroller.fahrstuehle)
            if (FahrstuhlSimulator.gebaeude.fahrstuhlcontroller.fahrstuehle.indexOf(f) == fahrstuhl)
                return f.getAnzahlDerPersonenImFahrstuhl();
        return -1;
    }
}
