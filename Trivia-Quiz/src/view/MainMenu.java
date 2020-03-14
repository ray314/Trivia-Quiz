/*
Description: Instantiates JButtons that allow a user to naviage to other displays e.g. View High Scores.
Date: 25/5/19
Author: James Eason
 */

package view;

import controller.Controller;
import java.awt.Dimension;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu {
    private JPanel centerPanel, northPanel, southPanel;
    private JButton playGame, viewInstructions, viewHighScores, exitGame;
    private ButtonGroup buttons;
    
    public MainMenu(){
        centerPanel = new JPanel();
    }
    
    public JPanel getAskQuestion() {
        return centerPanel;
    }
    
    public JPanel getNorthPanel(){
        return northPanel;
    }
    
    public JPanel getSouthPanel(){
        return southPanel;
    }
    
    public void setActionListener(Controller controller){
        playGame = new JButtonGradient("Play Game", 280, 53);
        viewHighScores = new JButtonGradient("View High Scores", 280, 53);
        viewInstructions = new JButtonGradient("Read Me", 280, 53);
        exitGame = new JButtonGradient("Exit", 280, 53);
        
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
        addButtons();
    }
        
    public void setSouthPanel(JPanel sPanel){
        this.southPanel = sPanel;        
        
        southPanel.removeAll();
        BoxLayout southLayout = new BoxLayout(southPanel, BoxLayout.Y_AXIS);    
        southPanel.setLayout(southLayout);
        southPanel.setBorder(new EmptyBorder(1, 0, 10, 0));  
    }
    
    public void setNorthPanel(JPanel north){                                    //add heading "Main Menu" to northPanel
        northPanel = north;
        northPanel.removeAll();
        
        BoxLayout boxLayout = new BoxLayout(northPanel, BoxLayout.Y_AXIS);
        northPanel.setLayout(boxLayout);
        northPanel.setBorder(new EmptyBorder(6, 264, 4, 0));                    //(top, left, bottom, right)
       
        Images mainMenu = new Images("mainmenu");
        mainMenu.setOpaque(false);
        mainMenu.setPreferredSize(new Dimension(324, 43));
        northPanel.add(mainMenu);
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
    
    public void addButtons(){                                                   
        BoxLayout boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(boxLayout);
        centerPanel.setBorder(new EmptyBorder(75, 100, 25, 100));               //(top, left, bottom, right)
        
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
