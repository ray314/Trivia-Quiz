/*
Description:  Displays "Time Limit Expired" message and the correct answer.
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

public class TimeExpired {
    private JPanel southPanel;
    private JLabel correctIncorrect, actualAnswer;
    private String correctAnswer;
    
    public TimeExpired(){ 
        southPanel = new JPanel();
        actualAnswer = new JLabel();
    }
    
    public void setComponents(JLabel incorrect, String theAnswer){ //rectrieve Objects from View class
        correctIncorrect = incorrect;
        correctAnswer = theAnswer;
    }
    
    public JPanel getTimeExpired(){                                             //return modified Objects to View class
        return southPanel;
    }
    
    public void timeExpiredMessage(Images redButton){
        redButton.setOpaque(false);
        
        southPanel.setBorder(new EmptyBorder(0, 389, 10, 100));             
        southPanel.add(redButton);                                              //display redButton
        southPanel.add(Box.createRigidArea(new Dimension(50, 75)));
        
        correctIncorrect = new JLabel("Time Limit Expired");                    //display "Time Limit Expired"
        correctIncorrect.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        correctIncorrect.setFont(new Font("calibri", Font.BOLD, 30));
        correctIncorrect.setForeground(Color.red.darker());
    }
    
    public void displayAnswer(JPanel centerBottom){         
        actualAnswer.setText("Correct answer is " + correctAnswer);             //display the correct answer
        actualAnswer.setFont(new Font("calibri", Font.BOLD, 30));
        actualAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        centerBottom.add(actualAnswer); 
            
        southPanel.add(correctIncorrect);
        southPanel.revalidate();
    }

    public void setTimeExpired(JPanel south, Images redButton, JPanel centerBottom) {
        southPanel = south;
        southPanel.removeAll();
        
        timeExpiredMessage(redButton);
        displayAnswer(centerBottom);
    }    
}
