/*
Description: Displays whether player answered the question correctly/incorrectly and the correct answer.
Date: 24/5/19
Author: James Eason
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DisplayAnswer {
    private JPanel southPanel, centerBottom;
    private Images greenButton, redButton;
    private JLabel correctIncorrect, actualAnswer;
    
    public DisplayAnswer(){ 
        southPanel = new JPanel();
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
        southPanel.add(Box.createRigidArea(new Dimension(50, 75)));
        correctIncorrect = new JLabel("Correct");                               //display "Correct" 
        correctIncorrect.setForeground(Color.green.darker().darker());                              
    }
    
    public void incorrectAnswer(){
        redButton.setOpaque(false);                                          
        southPanel.add(redButton);                                              //display redButton
        southPanel.add(Box.createRigidArea(new Dimension(50, 75)));
            
        correctIncorrect = new JLabel("Incorrect");                             //display "Incorrect"
        correctIncorrect.setForeground(Color.red.darker());
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
        southPanel.setBorder(new EmptyBorder(0, 389, 10, 200));                 

        if (answerCorrect){                                                     //answerCorrect = true   
            correctAnswer();
        } else{ 
            incorrectAnswer();                                                  //answerCorrect = false
            actualAnswer(correctAnswer);
        }    
        
        correctIncorrect.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        correctIncorrect.setFont(new Font("calibri", Font.BOLD, 30));
        
        southPanel.add(correctIncorrect);
        southPanel.revalidate();
    } 
}
