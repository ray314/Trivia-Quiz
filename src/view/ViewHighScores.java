/*
Description: Adds JPanels that display details of the top 10 high scores to the centerPanel from the View class
Date:   24/5/19
Author: James Eason
 */

package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ViewHighScores {
    private JPanel centerPanel;
    private JTextPane highScores1, highScores2;
    private TextAttributes textAttributes;
    private Images goldPlaque, silverPlaque;
    ArrayList<String> highScores;
    
    public ViewHighScores(){
        centerPanel = new JPanel();
    }
    
    public JPanel getViewScores(){                                              //returns centerPanel to View class after adding components
        return centerPanel;
    }
    
    public void setViewScores(JPanel center, ArrayList<String> scores, TextAttributes attributes){ //arguments from View class
        centerPanel = center;                                                   //reference JPanel to centerPanel in View class
        highScores = scores;                                                    //ArrayList contains details (Strings) of the top 10 high scores
        textAttributes = attributes;
        
        createHighScoresComponents();
        addScores();
        paintImages();
        layerScoresOnImages();
        assembleComponents();
    }

    public void createHighScoresComponents(){                                              
        centerPanel.removeAll();
        
        highScores1 = new JTextPane();                                          //create 2 JTextPane Objects side by side 
        highScores2 = new JTextPane();                                          //each displays 5 highScores
        highScores1.setCharacterAttributes(textAttributes.getTextSet(), true);
        highScores2.setCharacterAttributes(textAttributes.getTextSet(), true);
        highScores1.setText("");
        highScores2.setText("");
        
        highScores1.setSize(new Dimension(258, 371)); 
        highScores2.setSize(new Dimension(258, 371));
        highScores1.setEditable(false);                                         //Set false so user cannot edit the JTextPane
        highScores2.setEditable(false);
        highScores1.setOpaque(false);
        highScores2.setOpaque(false);
    }
    
    public void addScores(){                                                    //Add high score details to JTextPane objects
        Document doc1 = highScores1.getStyledDocument();
        Document doc2 = highScores2.getStyledDocument();
        
        ArrayList<String> arrayHighScores = highScores;
        
        Document doc = doc1;
        try{
            if (!arrayHighScores.isEmpty()){
                Iterator iterator = arrayHighScores.iterator();                                             
                for (int i=0; iterator.hasNext() && i<2; i++){
                    if (i==1) doc = doc2;

                    for (int j=0 ;iterator.hasNext() && j<5; j++) {             //insert Strings from sortedHighScores Objects into each JTextPane
                        String rank = (String)iterator.next();                  //"Rank: " +rank+ "\n";
                        String name = (String)iterator.next();                  //"Name: " +name+ +"\n";
                        String score = (String)iterator.next();                 //"Score: " +score+ +"\n\n";
                
                        doc.insertString(doc.getLength(), "Rank :", textAttributes.getTextBoldSet());
                        doc.insertString(doc.getLength(), rank+"\n", textAttributes.getTextSet());      //rank (1 is highest)
                        doc.insertString(doc.getLength(), "Name :", textAttributes.getTextBoldSet());
                        doc.insertString(doc.getLength(), name+"\n", textAttributes.getTextSet());      //Player name
                        doc.insertString(doc.getLength(), "Score :", textAttributes.getTextBoldSet());
                        doc.insertString(doc.getLength(), score+"\n\n", textAttributes.getTextSet());   //score
                    }
                }
            }   else {                                                                
                doc1.insertString(doc1.getLength(), "There are currntly no High Scores...", textAttributes.getTextBoldSet()); //display message if there are no sortedHighScores
            }
        } catch (BadLocationException e){
            Logger.getLogger(ViewHighScores.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
    }    
    
    public void paintImages(){                                                  //paint Images onto JPanels
        goldPlaque = new Images("goldplaque");
        silverPlaque = new Images("silverplaque");
        
        goldPlaque.setMaximumSize(new Dimension(258, 371)); 
        silverPlaque.setMaximumSize(new Dimension(258, 371));  
       
        goldPlaque.setOpaque(false);
        silverPlaque.setOpaque(false);
    }   
    
    public void layerScoresOnImages(){                                          //Layer JTextPane objects on top of painted JPanels
        BoxLayout goldPlaqueLayout = new BoxLayout(goldPlaque, BoxLayout.Y_AXIS);
        BoxLayout silverPlaqueLayout = new BoxLayout(silverPlaque, BoxLayout.Y_AXIS);
        
        goldPlaque.setLayout(goldPlaqueLayout);
        silverPlaque.setLayout(silverPlaqueLayout);
        goldPlaque.setBorder(new EmptyBorder(0, 30, 0, 0));                     //top, left, bottom, right
        silverPlaque.setBorder(new EmptyBorder(0, 30, 0, 0));                   
        
        goldPlaque.add(highScores1);
        silverPlaque.add(highScores2);  
    }
    
    public void assembleComponents(){                                           //add painted JPanels with score details to centerPanel
        BoxLayout centerPanelLayout = new BoxLayout(centerPanel, BoxLayout.X_AXIS);
        
        centerPanel.setBorder(new EmptyBorder(30, 108, 30, 100));               
        centerPanel.setLayout(centerPanelLayout);
        centerPanel.add(goldPlaque);
        centerPanel.add(Box.createRigidArea(new Dimension(100, 300)));          
        centerPanel.add(silverPlaque); 
    }  
}
