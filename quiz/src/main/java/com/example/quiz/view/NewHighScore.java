/*
Description: At the end of a game if a player has created a new HighScore display the Players 
             score is  top of a shield picture (an Image painted onto a JPanel).
Date: 24/5/19
Author: James Eason
 */

package com.example.quiz.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class NewHighScore{
    private JPanel centerPanel;
    private JLabel newTop10Score;
    private JTextPane newHighScore;
    private TextAttributes textAttributes;
    private String[] score;

    public NewHighScore(){
        centerPanel = new JPanel();                                             
    }
    
    public JPanel getNewScore() {                                               //returns centerPanel to View class after adding components
        return centerPanel;
    }

    public void setNewScore(JPanel center, String[] scoreDetails, TextAttributes attributes) { //arguments from View class
        centerPanel = center;                                                   //reference JPanel to centerPanel in View class
        score = scoreDetails;                                                   //details containing the Players score
        textAttributes = attributes;
        
        centerPanel.removeAll();
        congratulationsMessage();
        displayPlayerDetails();
        int docLength = AddPlayerDetails();
        alignPlayerDetails(docLength);
        addShieldImage();   
    }
    
    public void congratulationsMessage(){                                       //Instantiate a JLabel that displays "Congratulations"   
        newTop10Score = new JLabel();
        
        Dimension jLabelSize = new Dimension(860, 100); //width, height
        newTop10Score.setSize(jLabelSize);
        newTop10Score.setAlignmentX(Component.CENTER_ALIGNMENT);
        newTop10Score.setText("Congratulations!");                              //display "Congratulations"
        newTop10Score.setHorizontalAlignment(SwingConstants.CENTER);
        newTop10Score.setFont(new Font("calibri", Font.BOLD, 35));
        newTop10Score.setForeground(Color.RED);
        newTop10Score.setSize(new Dimension(200, 165)); 
    }
    
    public void displayPlayerDetails(){                                         //creates a JTextPane to display the Players score
        newHighScore = new JTextPane();
        newHighScore.setEditable(false);
        newHighScore.setOpaque(false);
        newHighScore.setEditable(false);
        newHighScore.setOpaque(false);
        
        StyleConstants.setFontSize(textAttributes.getTextSet(), 30);            //increase font size in thisJTextPane from 15 to 30
        StyleConstants.setFontSize(textAttributes.getTextBoldSet(), 30);
        
        newHighScore.setCharacterAttributes(textAttributes.getTextBoldSet(), true);
        newHighScore.setText("\tRank: ");                                       
    }
    
    public int AddPlayerDetails(){                                              //add Player details to JTextPane created in displayPlayerDetails()
        Document doc = newHighScore.getStyledDocument();
        
        try{                                                                             
            doc.insertString(doc.getLength(), score[0]+"\n", textAttributes.getTextSet());  //display rank
            doc.insertString(doc.getLength(), "\tName: ", textAttributes.getTextBoldSet());         
            doc.insertString(doc.getLength(), score[1]+"\n", textAttributes.getTextSet());  //display name
            doc.insertString(doc.getLength(), "\tScore: ", textAttributes.getTextBoldSet());        
            doc.insertString(doc.getLength(), score[2], textAttributes.getTextSet());       //display score      
        } catch (BadLocationException e){
            Logger.getLogger(NewHighScore.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }   
        
        return doc.getLength();
    }
    
    public void alignPlayerDetails(int documentLength){                         //center text in JTextPane horizoltally  
        StyledDocument sDocument = newHighScore.getStyledDocument();            
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        sDocument.setParagraphAttributes(0, documentLength, center, false);      
    }
    
    public void addShieldImage(){                                               //paint a picture of a shield (Image) to a JPanel
        BoxLayout centerPanelLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerPanelLayout);
        centerPanel.setBorder(new EmptyBorder(0, 5, 0, 0));                     //(top, left, bottom, right)       
        centerPanel.setOpaque(false);
        
        Images goldShield = new Images("shield");                               //Image paints shield.jpg onto background
        goldShield.setBorder(BorderFactory.createEtchedBorder());///
        
        BoxLayout shieldLayout = new BoxLayout(goldShield, BoxLayout.Y_AXIS);
        goldShield.setLayout(shieldLayout);
        goldShield.setBorder(new EmptyBorder(120, 141, 141, 145));                  
        goldShield.add(newTop10Score);
        goldShield.add(newHighScore);        
        goldShield.setOpaque(false);
              
        centerPanel.add(goldShield);
    }
}
