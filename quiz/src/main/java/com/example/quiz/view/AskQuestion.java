/*
Description: Layers components which display the question and posssible answers onto the centerPanel (JPanel) 
Date: 24/5/19
Author: James Eason
 */

package com.example.quiz.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AskQuestion {
    private JPanel centerPanel, top, left, right, middle, bottom;
    private JRadioButton possibleAnswerA, possibleAnswerB, possibleAnswerC, possibleAnswerD;
    private String questionString;
    private ArrayList<String> possibleAnswers;
    private ButtonGroup buttons;
    
    public AskQuestion(){
        centerPanel = new JPanel();                                             
    }
    
    public JPanel getAskQuestion() {
        return centerPanel;
    }
    
    public int getPlayerAnswer(){                                               //returns which of the possible answers is selected
        int playerAnswer;
        
        if (possibleAnswerA.isSelected()) {
            playerAnswer = 0;
        } else if (possibleAnswerB.isSelected()) {
            playerAnswer = 1;
        } else if (possibleAnswerC.isSelected()) {
            playerAnswer = 2;
        } else {
            playerAnswer = 3;
        }
        
        return playerAnswer;
    }
    
    public JPanel getCenterBottomPanel(){
        return bottom;
    }
    
    public void setQuestion(ArrayList<String> possAnswers, String question){    
        possibleAnswers = possAnswers;
        questionString = question;
    }
    
    public void setAskQuestion(JPanel center, JLabel triviaQuestion, JPanel bottomPanel) { 
        centerPanel = center;
        bottom = bottomPanel;
        centerPanel.removeAll();
        
        instantiateComponents();
        setComponentsTransparent();
        createButtonGroup();
        setButtonAttributes();

        setTopPanel(triviaQuestion);
        setLeftPanel();
        setRightPanel();
        setMiddlePanel();
        setBottomPanel();
        setCenterPanel();
    }
    
    public void instantiateComponents(){                                        //isntanitate JPanels and JRadioButtons 
        middle = new JPanel();                                                  //container for top, left, right, bottom JPanels
        top = new JPanel();                                               
        left = new JPanel();                                                    
        right = new JPanel();
        
        possibleAnswerA = new JRadioButton();
        possibleAnswerB = new JRadioButton();
        possibleAnswerC = new JRadioButton();
        possibleAnswerD = new JRadioButton();
    }
    
    public void setComponentsTransparent(){//set JPanels/JRadioButtons transparent so user can see background JPanel (picture) 
        top.setOpaque(false);           
        left.setOpaque(false);
        right.setOpaque(false);
        middle.setOpaque(false);
        bottom.setOpaque(false);
        
        possibleAnswerA.setOpaque(false);
        possibleAnswerB.setOpaque(false);
        possibleAnswerC.setOpaque(false);
        possibleAnswerD.setOpaque(false);
    }
    
    public void createButtonGroup(){
        buttons = new ButtonGroup();
        buttons.add(possibleAnswerA);
        buttons.add(possibleAnswerB);
        buttons.add(possibleAnswerC);
        buttons.add(possibleAnswerD);
    }
    
    public void setButtonAttributes(){
        Enumeration<AbstractButton> elements = buttons.getElements();
        while (elements.hasMoreElements()) {
            JRadioButton button = (JRadioButton) elements.nextElement();
            button.setFont(new Font("calibri", Font.BOLD, 30));                 //set Font for JRadioButton
            button.setFocusable(false);                                         //removes line around JButton when selected
        }
    }
    
    public void setTopPanel(JLabel triviaQuestion){
        triviaQuestion.setFont(new Font("calibri", Font.ITALIC + Font.BOLD, 30));//display question using italic and bold Font
        triviaQuestion.setText(questionString);
        triviaQuestion.setHorizontalAlignment(SwingConstants.CENTER);           //aligns question to center
        
        FlowLayout layoutCenterTop = new FlowLayout(FlowLayout.CENTER, 0, 0);   //(horizontal centre, horizontal gap, vertical gap)       
        top.setLayout(layoutCenterTop);                                   
        top.add(triviaQuestion);                                                //add JLabel displaying question to top JPanel
    }
   
    public void setLeftPanel(){
        possibleAnswerA.setText(possibleAnswers.get(0));
        possibleAnswerA.setSelected(true);                                      //select as default answer
        possibleAnswerB.setText(possibleAnswers.get(1));
                
        BoxLayout leftLayout = new BoxLayout(left, BoxLayout.Y_AXIS);           
        left.setLayout(leftLayout);
        left.setBorder(new EmptyBorder(0, 0, 0, 0));                
        left.add(possibleAnswerA);                                              //add possible answer buttons to left JPanel
        left.add(possibleAnswerB);
        
    }
    
    public void setRightPanel(){
        possibleAnswerC.setText(possibleAnswers.get(2));
        possibleAnswerD.setText(possibleAnswers.get(3));
        
        BoxLayout rightLayout = new BoxLayout(right, BoxLayout.Y_AXIS);         
        right.setLayout(rightLayout);
        right.setBorder(new EmptyBorder(0, 0, 0, 0));               
        right.add(possibleAnswerC);                                             //add possible answer to right JPanel
        right.add(possibleAnswerD);  
    }
    
    public void setMiddlePanel(){
        FlowLayout middleLayout = new FlowLayout(FlowLayout.CENTER, 100, 25);      
        middle.setLayout(middleLayout);                             
        middle.add(left);                                                       //add JPanel containing 2 possible answers to middle JPanel
        middle.add(right);                                                      
    }
    
    public void setBottomPanel(){
        FlowLayout bottomLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);       
        bottom.setLayout(bottomLayout);
        bottom.add(Box.createRigidArea(new Dimension(30, 37)));
    }
    
    public void setCenterPanel(){                                               //add top, middle and bottom JPanels to centerPanel
        BoxLayout centerPanelLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerPanelLayout);
        centerPanel.setBorder(new EmptyBorder(50, 0, 50, 0));    
        centerPanel.add(top);                                                   
        centerPanel.add(middle);                                                
        centerPanel.add(bottom);                                                
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
