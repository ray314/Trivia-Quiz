/*
Description:  Adds components to centrePanel and northPanel JPanels.
Date:  24/6/19
Author:  JamesEason
 */

package com.example.quiz.view;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Welcome {
    private JPanel centerPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    
    public JPanel getCenterPanel(){
        return centerPanel;
    }
    
    public JPanel getNorthPanel(){
        return northPanel;
    }
    
    public JPanel getSouthPanel(){
        return southPanel;
    }
      
    public void setCenterPanel(JPanel center){
        centerPanel = center;
        
        BoxLayout centerLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerLayout);
        centerPanel.setBorder(new EmptyBorder(0, 2, 0, 4));                     //top, left, bottom, right   
        
        Images quizImage = new Images("quiz");                                  //create instance of JPanel that displays quiz image
        quizImage.setOpaque(false);
        quizImage.setMaximumSize(new Dimension(826, 431));
        centerPanel.add(quizImage);
    }
    
    public void setNorthPanel(JPanel north){
        northPanel = north;
        northPanel.removeAll();
        
        BoxLayout northLayout = new BoxLayout(northPanel, BoxLayout.X_AXIS);    //X_Axis adds components left to right
        northPanel.setLayout(northLayout);
        northPanel.setBorder(new EmptyBorder(6, 260 , 4, 0));                     //top, left, bottom, right        
        
        Images triviaQuiz = new Images("triviaquiz");                                  
        triviaQuiz.setPreferredSize(new Dimension(339, 48));
        triviaQuiz.setOpaque(false);
        northPanel.add(triviaQuiz);   
    }  
    
    public void setSouthPanel(JPanel south, JButton button){
        southPanel = south;
        button.setPreferredSize(new Dimension(200, 47));
        
        BoxLayout southLayout = new BoxLayout(southPanel, BoxLayout.Y_AXIS);    
        southPanel.setLayout(southLayout);
        southPanel.setBorder(new EmptyBorder(1, 0, 10, 0));  
        
        southPanel.add(Box.createRigidArea(new Dimension(20, 55)));
        southPanel.add(button);
    }
}
