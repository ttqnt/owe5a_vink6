package owe5a_vink6;

/**
 *
 * @author karin
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener{
    private final JTextField textf;
    private final JButton browseButton, analyseButton;
    private final JLabel label1, label2, label3;
    private final JPanel panel;
    private final JTextArea area;
    private JFileChooser fileChooser;

			
	//constructor:
    public Gui(){
	super("Yet Another New Amino Acid Reader Application");
        setLayout(new FlowLayout());
		
        label3 = new JLabel("Bestand");
	add(label3);
		
	textf = new JTextField(35);
	add(textf);
		
	browseButton = new JButton("Browse");
	add(browseButton);
		
	analyseButton = new JButton("Analyseer");
	add(analyseButton);
		
	label1 = new JLabel("Sequentie:");
	add(label1);

	area = new JTextArea(25,55);
	area.setLineWrap(true);
	area.setWrapStyleWord(true);
	add( area);
	JScrollPane scroll = new JScrollPane ( area );
	scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	add (scroll);

	label2 = new JLabel("Percentage:");
	add(label2);
		
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 100));
        panel.setBackground(Color.lightGray);
        add(panel);
		
        analyseButton.addActionListener(this);
        browseButton.addActionListener(this);
	}
	

	    
    public void actionPerformed(ActionEvent event) {
        Object buttonPressed = event.getSource();
	String input = area.getText();
	File selectedFile;
	int reply;
	BufferedReader infile;
		
	//Door de Analyse knop aan te klikken wordt een object van de SeqAnalysis klasse aangemaakt en de resultaten weergeven in de Gui
	if (analyseButton == buttonPressed){ 
            if (input.length() <= 0){
                JOptionPane.showMessageDialog(null, "Voer sequentie in");
            } else try {
                SeqAnalysis analyse = new SeqAnalysis(input);
		area.setText("Het totaal aantal aminozuren: "+ analyse.getTotal());
			
		Graphics graph = panel.getGraphics();
		graph.setColor(Color.lightGray);
		graph.fillRect(0, 0, 450,100);
		graph.setColor(Color.green);
		graph.fillRect(0, 10, analyse.getPolar()*4, 36);
		graph.setColor(Color.black);
		graph.drawString("Polair ("+ analyse.getPolar() + "%)", 10, 32);
		graph.setColor(Color.red);
		graph.fillRect(0, 55, analyse.getNonpolar()*4, 36);	
		graph.setColor(Color.black);
		graph.drawString("Apolair: ("+ analyse.getNonpolar() + "%)", 10, 78);
            } catch (InvalidSeqException e) {
                JOptionPane.showMessageDialog(null, "Corrupte sequentie");
            }
	//Door de browseknop aan te klikken wordt filechooser geopent, de gekozen file geopent, gelezen en in de textarea geprint
        } else if (browseButton == buttonPressed){
            fileChooser = new JFileChooser();
            reply = fileChooser.showOpenDialog(this);
            String line;
            
            if(reply == JFileChooser.APPROVE_OPTION){
		selectedFile = fileChooser.getSelectedFile();
		textf.setText(selectedFile.getAbsolutePath());
            } try {
                infile = new BufferedReader(new FileReader(textf.getText()));
                while ((line = infile.readLine()) != null){
                    area.setText(line);
                }
                infile.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Bestand niet gevonden");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Kan bestand niet lezen");
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Onbekende fout");
            }
        }
    }
}