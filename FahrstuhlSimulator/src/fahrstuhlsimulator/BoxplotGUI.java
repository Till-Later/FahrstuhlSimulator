package fahrstuhlsimulator;

import fahrstuhlsimulator.Boxplot.LineMatrix;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Klein
 */
public class BoxplotGUI extends JPanel
{
    Boxplot steuerung;
    
    private JFormattedTextField minimumTF;
    private JFormattedTextField uqTF;
    private JFormattedTextField medianTF;
    private JFormattedTextField oqTF;
    private JFormattedTextField maximumTF;
    
    int a = 1;
    /**
     * Erstellt die Grafik eines Boxplots mit den Werten des übergebenen
     * <code>Boxplot</code>-Objektes.</ br>
     * <strong>Hinweis:</strong> Mit Erstellen eines <code>Boxplot</code> wird
     * immer automatisch ein <code>BoxplotGUI</code> erstellt und kann über 
     * Boxplot#getGraphicalUserInterface aufgerufen werden, um es zum Beispiel
     * auf einem <code>JFrame</code> anzeigen zu können.
     * @param boxplotSteuerung Ein <code>Boxplot</code>-Objekt, dass die Grafik steuert
     * @see fahrstuhlsimulatortestbereich.Boxplot
     */
    public BoxplotGUI(Boxplot boxplotSteuerung)
    {
        setSize(330,100);
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        setLayout(null);
        this.addMouseMotionListener(new MouseAdapter(){  //zum Bewegen der Linien
            
            //LineMatrix der Linie, die gerade verschoben wird
            LineMatrix aktLineMatrix;
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (aktLineMatrix == null) //neue Linie suchen, wenn gerade keine verschoben wird
                    aktLineMatrix = steuerung.getLinieAt(e.getPoint());
                if (aktLineMatrix != null && aktLineMatrix.istBeweglich()) //Linie verschieben
                {
                    int altX = aktLineMatrix.getX();
                    aktLineMatrix.verschiebe(e.getX());
                    if (!steuerung.pruefeLinie(aktLineMatrix)) //prüfen, ob die Linie jetzt auf einer anderen Linie liegt
                        aktLineMatrix.verschiebe(altX);
                    
                    //der Linie entsprechendes Textfeld suchen
                    int index = steuerung.getLinien().indexOf(aktLineMatrix);
                    
                    //der absolute Wert, dem die Linie gerade entspricht
                    double temp = steuerung.berechneAbsolutenWert(e.getX());
                    
                    //Textfeld auf den aktuellen Wert setzen
                    //Dabei darf der Wert nicht größer als der der rechten und
                    //nicht kleiner als der der linken Komponente werden
                    if (index == 1      && temp>steuerung.getMinimum()
                                        && temp<steuerung.getMedian())
                        uqTF.setValue(temp);
                    else if (index == 2 && temp>steuerung.getUnteresQuartil()
                                        && temp<steuerung.getOberesQuartil())
                        medianTF.setValue(temp);
                    else if (index == 3 && temp>steuerung.getMedian()
                                        && temp<steuerung.getMaximum())
                        oqTF.setValue(temp);
                    
                    repaint();
                }
            }
            
            boolean istStandardCursorSichtbar = true;
            @Override
            public void mouseMoved(MouseEvent e)
            {
                aktLineMatrix = steuerung.getLinieAt(e.getPoint());
                if (aktLineMatrix == null && !istStandardCursorSichtbar)
                    setCursor(Cursor.getDefaultCursor());
                if (aktLineMatrix != null && istStandardCursorSichtbar){
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));}
                istStandardCursorSichtbar = !istStandardCursorSichtbar;
                
                aktLineMatrix = null;
            }
        });
        
        steuerung = boxplotSteuerung;
        
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ROOT);
        nf.setGroupingUsed(false);
        
        NumberFormatter formatter = new NumberFormatter(nf);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(false);
        
        minimumTF = new JFormattedTextField(nf);
        uqTF      = new JFormattedTextField(nf);
        medianTF  = new JFormattedTextField(nf);
        oqTF      = new JFormattedTextField(nf);
        maximumTF = new JFormattedTextField(nf);
        
        minimumTF.setValue(steuerung.getMinimum());
        uqTF     .setValue(steuerung.getUnteresQuartil());
        medianTF .setValue(steuerung.getMedian());
        oqTF     .setValue(steuerung.getOberesQuartil());
        maximumTF.setValue(steuerung.getMaximum());
        
        minimumTF.setBounds(5, 75, 52, 28);
        uqTF     .setBounds(72, 75, 52, 28);
        medianTF .setBounds(139, 75, 52, 28);
        oqTF     .setBounds(206, 75, 52, 28);
        maximumTF.setBounds(273, 75, 52, 28);
        
        FocusListener focusListener = new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e)
            {
                JFormattedTextField atf = (JFormattedTextField) e.getSource();
                if (atf.equals(minimumTF))
                    steuerung.setMinimum(Double.parseDouble((String)minimumTF.getText()));
                else if (atf.equals(uqTF))
                    steuerung.setUnteresQuartil(Double.parseDouble((String)uqTF.getText()));
                else if (atf.equals(medianTF))
                    steuerung.setMedian(Double.parseDouble((String)medianTF.getText()));
                else if (atf.equals(oqTF))
                    steuerung.setOberesQuartil(Double.parseDouble((String)oqTF.getText()));
                else if (atf.equals(maximumTF))
                    steuerung.setMaximum(Double.parseDouble((String)maximumTF.getText()));
            }
        };
        minimumTF.addFocusListener(focusListener);
        uqTF     .addFocusListener(focusListener);
        medianTF .addFocusListener(focusListener);
        oqTF     .addFocusListener(focusListener);
        maximumTF.addFocusListener(focusListener);
        
        add(minimumTF);
        add(uqTF);
        add(medianTF);
        add(oqTF);
        add(maximumTF);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ArrayList<LineMatrix> matrizen = steuerung.getLinien();
        
        //senkrechte Linien für die 5 Kompononten zeichnen
        for (LineMatrix lm : matrizen)
            g.drawLine(lm.getX(), lm.getYStart(), lm.getX(), lm.getYEnde());
        
        //waagerechte Linien zum Vervollständigen des Rechtecks (unteres bis oberes Quartil) zeichnen
        g.drawLine(matrizen.get(1).getX(), 15, matrizen.get(3).getX(), 15);
        g.drawLine(matrizen.get(1).getX(), 65, matrizen.get(3).getX(), 65);
        
        //waagerechte Verbindungslinien zwischen Quartillen und Außengrenzen zeichnen
        g.drawLine(matrizen.get(0).getX(), 40, matrizen.get(1).getX(), 40);
        g.drawLine(matrizen.get(3).getX(), 40, matrizen.get(4).getX(), 40);
    }
}
