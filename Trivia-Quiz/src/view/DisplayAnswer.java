/*
Description: Displays whether player answered the question correctly/incorrectly and the correct answer.
Date: 24/5/19
Author: James Eason
 */

package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DisplayAnswer {
    private JPanel southPanel, centerBottom;
    private Images greenButton, redButton, correctIncorrect;
    private JLabel actualAnswer;
    
    public DisplayAnswer(){ 
        actualAnswer = new JLabel();
    }

    public JPanel[] getPanels() {                                              //returns modified Objects to View class
        JPanel[] panels = new JPanel[2];
        panels[0] = southPanel;
        panels[1] = centerBottom;

        return panels;
    }
    
    public void setComponents(Images green, Images red, JPanel bottom){         //sets components from View class
        greenButton = green;
        redButton = red;
        centerBottom = bottom;
    }
    
    public void correctAnswer(){
        greenButton.setOpaque(false);
        southPanel.add(greenButton);                                            //display greenButton 
        southPanel.add(Box.createRigidArea(new Dimension(75, 75)));
        
        correctIncorrect = new Images("correct");                               //display correct.png      
    }
    
    public void incorrectAnswer(){
        redButton.setOpaque(false);                                          
        southPanel.add(redButton);                                              //display redButton
        southPanel.add(Box.createRigidArea(new Dimension(75, 75)));
            
        correctIncorrect = new Images("incorrect");                             //display incorrect.png
    }
    
    public void actualAnswer(String correctAnswer){
        actualAnswer.setFont(new Font("calibri", Font.BOLD, 30));
        actualAnswer.setText("Correct answer is " + correctAnswer);             //display the actual answer
        actualAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        centerBottom.add(actualAnswer);
    }
    
    public void setAskQuestion(JPanel south, boolean answerCorrect, String correctAnswer) {
        southPanel = south;
        southPanel.removeAll();
        
        BoxLayout boxLayout = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        southPanel.setLayout(boxLayout);
        southPanel.setBorder(new EmptyBorder(0, 389, 10, 75));                 //top, left, bottom, right

        if (answerCorrect){                                                     //answerCorrect = true   
            correctAnswer();
        } else{ 
            incorrectAnswer();                                                  //answerCorrect = false
            actualAnswer(correctAnswer);
        }    
        
        correctIncorrect.setPreferredSize(new Dimension(222,43));
        correctIncorrect.setOpaque(false);
        southPanel.add(correctIncorrect);
        southPanel.revalidate();
    } 
}
