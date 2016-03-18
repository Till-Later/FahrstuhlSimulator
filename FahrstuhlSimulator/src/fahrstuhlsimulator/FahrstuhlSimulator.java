/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahrstuhlsimulator;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author Lehmann
 */
public class FahrstuhlSimulator {
    
    public static int playstate = 0;
    public static float speed = 1;
    public static Calendar uhrzeit = new GregorianCalendar(2000,1,1,8,0,0);
    public static Gebaeude gebaeude;
    public static GrafischesInterface grafischesInterface;
    
    public static void setPlaystate(int ps){
        playstate = ps;
    }
    
    public static int getPlaystate(){
        return playstate;
    }
    
    public static void setSpeed(float s){
        speed = s;
    }
    
    public static float getSpeed(){
        return speed;
    }
    
    public static void setUhrzeit(Calendar uz){
        uhrzeit = uz;
    }
    
    public static Calendar getUhrzeit(){
        return uhrzeit;
    }
    public static void tick()
    {
        gebaeude.tick();
        grafischesInterface.tick();
    }
    
    public static void main(String[] args)
    {
        gebaeude = new Gebaeude();
        grafischesInterface = new GrafischesInterface();
        int i = 0;
        while (true) {
            i++;
            System.out.println("---------------------------------- Sekunde : "+ i + " ----------------------------------");
            tick();
            try {
                Thread.sleep((long)((1/speed)*1000));               
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
