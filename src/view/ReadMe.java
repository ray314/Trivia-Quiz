/*
Description: Instantiates components that display instructions and Database Metadata 
Date: 24/5/19
Author: James Eason
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ReadMe {
    private JPanel centerPanel;
    private JTextPane readMePane;
    private TextAttributes textAttributes;
    
    public ReadMe(){
        centerPanel = new JPanel();                                             
    }
    
    public JPanel getReadMe() {                                                 //returns centerPanel to View class after adding components
        return centerPanel;
    }
    
    public void setReadMe(JPanel center, String[] readMe, TextAttributes attributes) { //arguments from View class
        centerPanel = center;                                                   //reference JPanel to centerPanel in View class
        textAttributes = attributes;
        centerPanel.removeAll();
        
        instantiatePane();
        addTextToPane(readMe);
        Images woodenBackground = paintImage(); 
        assembleComponents(woodenBackground);
    }   
    
    public void instantiatePane(){
        readMePane = new JTextPane();
        readMePane.setCharacterAttributes(textAttributes.getHeadingSet(), true);
        readMePane.setText("Instructions"); 
        
        readMePane.setSize(new Dimension(300, 600));
        readMePane.setEditable(false);                                          //Set false so user cannot edit text in JTextPane
        readMePane.setOpaque(false);  
    }
    
    public void addTextToPane(String[] instructions){                           //argument contains instructions and Database Metadata
        Document doc = readMePane.getStyledDocument(); 
        
        try{ 
            for (int i = 0; i<7; i++){
               if (i%2 == 0){
                   doc.insertString(doc.getLength(), instructions[i], textAttributes.getTextSet()); 
               } else {
                   doc.insertString(doc.getLength(), instructions[i], textAttributes.getTextBoldSet()); 
               }
            }

            doc.insertString(doc.getLength(), "Database Information\n", textAttributes.getHeadingSet());          
            doc.insertString(doc.getLength(), "Product name: ", textAttributes.getTextBoldSet());           
            doc.insertString(doc.getLength(), instructions[7], textAttributes.getTextSet());            
            doc.insertString(doc.getLength(), "URL: ", textAttributes.getTextBoldSet());           
            doc.insertString(doc.getLength(), instructions[8], textAttributes.getTextSet());            
            doc.insertString(doc.getLength(), "Driver name: ", textAttributes.getTextBoldSet());           
            doc.insertString(doc.getLength(), instructions[9], textAttributes.getTextSet());            
            doc.insertString(doc.getLength(), "User name: ", textAttributes.getTextBoldSet());           
            doc.insertString(doc.getLength(), instructions[10], textAttributes.getTextSet());   
        } catch (BadLocationException e){
            Logger.getLogger(ReadMe.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        } 
    }
    
    public Images paintImage(){                                                 //paint background picture (Images) to a JPanel
        Images woodenBackground = new Images("readme");
        Color brown = new Color(102,51,0);
        centerPanel.setBorder(BorderFactory.createLineBorder(brown,5));         //add border to JPanel
        woodenBackground.setOpaque(false);
        
        return woodenBackground;
    }
    
    public void assembleComponents(Images woodenBackground){
        BoxLayout paperImageLayout = new BoxLayout(woodenBackground, BoxLayout.Y_AXIS);
        woodenBackground.setLayout(paperImageLayout);
        woodenBackground.setBorder(new EmptyBorder(84, 145, 13, 0));                  
        woodenBackground.add(readMePane);                                       //layer instructions and Database Metada on top of painted JPanel
        
        BoxLayout centerPanelLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerPanelLayout);
        centerPanel.setBorder(new EmptyBorder(5, 5, 0, 0));                     //(top, left, bottom, right)        
        centerPanel.add(woodenBackground);   
    } 
}
