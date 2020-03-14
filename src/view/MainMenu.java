/*
Description: Instantiates JButtons that allow a user to naviage to other displays e.g. View High Scores.
Date: 25/5/19
Author: James Eason
 */

package view;

import controller.Controller;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu {
    private JPanel centerPanel;
    private JButton playGame, viewInstructions, viewHighScores, exitGame;
    private ButtonGroup buttons;
    
    public MainMenu(){
        centerPanel = new JPanel();
    }
    
    public JPanel getAskQuestion() {
        return centerPanel;
    }
    
    public void setActionListener(Controller controller){
        playGame = new JButton("Play Game");
        viewHighScores = new JButton("View High Scores");
        viewInstructions = new JButton("Read Me");
        exitGame = new JButton("Exit");
        
        playGame.addActionListener(controller); 
        viewHighScores.addActionListener(controller); 
        viewInstructions.addActionListener(controller); 
        exitGame.addActionListener(controller);
    }
    
    public void setAskQuestion(JPanel center) {
        centerPanel = center;
        centerPanel.removeAll();
        playGame.setFocusable(false);
        
        instantiateButtonGroup();
        removeButtonFocus();
        setButtonAttributes();
        addButtons();
    }
    
    public void instantiateButtonGroup(){
        buttons = new ButtonGroup();
        buttons.add(playGame);
        buttons.add(viewHighScores);
        buttons.add(viewInstructions);       
        buttons.add(exitGame);               
    }
    
    public void removeButtonFocus(){
        Enumeration<AbstractButton> elements = buttons.getElements();
        while (elements.hasMoreElements()) {
            JButton button = (JButton) elements.nextElement();
            button.setFocusable(false);                                         //removes line around JButton when selected
        }
    }
    
    public void setButtonAttributes(){
        Dimension buttonSize = new Dimension(244, 47); 
        
        Enumeration<AbstractButton> elements = buttons.getElements();
        while (elements.hasMoreElements()) {
            JButton button = (JButton) elements.nextElement();
            button.setFont(new Font("calibri", Font.PLAIN, 30));                //set Button Font
            button.setMaximumSize(buttonSize);                                         //set Button size
            button.setAlignmentX(Component.CENTER_ALIGNMENT);                   //set Button alignment
        }
    }
    
    public void addButtons(){                                                   
        BoxLayout boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(boxLayout);
        centerPanel.setBorder(new EmptyBorder(95, 100, 80, 100));               //(top, left, bottom, right)
        
        centerPanel.add(playGame);                                              //add JButton playGame to centerPanel 
        centerPanel.add(Box.createRigidArea(new Dimension(20, 30)));            
        centerPanel.add(viewHighScores);                                        //add JButton viewHighScores to centerPanel 
        centerPanel.add(Box.createRigidArea(new Dimension(20, 30)));
        centerPanel.add(viewInstructions);                                      //add JButton viewInstructions to centerPanel 
        centerPanel.add(Box.createRigidArea(new Dimension(20, 30)));
        centerPanel.add(exitGame);                                              //add JButton exitGame to centerPanel 
        centerPanel.add(Box.createRigidArea(new Dimension(20, 90))); 
    }
}
