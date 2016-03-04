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
    
    public static int playstate = 1;
    public static float speed = 1;
    public static Calendar uhrzeit = new GregorianCalendar(2000,1,1,8,0,0);
    public static Gebaeude gebaeude;
    public static GrafischesInterface grafischesInterface;
    
    /**
     * @param args the command line arguments
     */
    public void setPlaystate(int playstate){
        this.playstate = playstate;
    }
    
    public int getPlaystate(){
        return playstate;
    }
    
    public void setSpeed(float speed){
        this.speed = speed;
    }
    
    public float getSpeed(){
        return speed;
    }
    
    public void setUhrzeit(Calendar uhrzeit){
        this.uhrzeit = uhrzeit;
    }
    
    public Calendar getUhrzeit(){
        return uhrzeit;
    }
    
    public void tick(){
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
