/*
Description: Instantiates components which get a players name and question category (type of questions) from the user.
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlayerDetails {
    private JPanel centerPanel;
    private JTextField userNameField;
    private JLabel questionType;
    private JRadioButton entertainmentCategory, sportCategory, geographyCategory, scienceCategory;
    private ButtonGroup buttons;
    
    public PlayerDetails(){
        centerPanel = new JPanel();
        userNameField = new JTextField(20);
        questionType = new JLabel("Select Question Type:");
    }

     public int getUserNameLength() {
        return userNameField.getText().length();
    }
     
    public String getUserNameField(){
        return userNameField.getText(); 
    } 
    
    public JPanel getPlayerDetails() {
        return centerPanel;
    }
    
    public int getQuestionCategory(){
        int questionCategory;
        
        if (entertainmentCategory.isSelected()) {
            questionCategory = 1;                                       //Entertainment Questions
        } else if (sportCategory.isSelected()) {
            questionCategory = 2;                                       //Sport Questions
        } else if (geographyCategory.isSelected()) {
            questionCategory = 3;                                       //Geography Questions
        } else {
            questionCategory = 4;                                       //Science Questions
        }
        
        return questionCategory;        
    }
    
    public void setKeyListener(Controller controller){
        userNameField.addKeyListener(controller);                               //Key Listener limits userNameField to 20 char max.
    }
    
    public void setUserNameLength() {
        userNameField.setText(userNameField.getText().substring(0,20));         //if player enters more than 20 characters for delete them
    }
    
    public void setPlayerDetails(JPanel center) {
        centerPanel = center;
        centerPanel.removeAll();
        
        userNameField.setSize(new Dimension(600, 50));
        userNameField.setText("<Enter Name>");                                  
        userNameField.requestFocus();
        userNameField.selectAll();                                              //selects the text "<Enter name here>" in JTextField  
        
        questionType = new JLabel("Select Question Type:");
        
        instantiateButtons();
        createButtonGroup();
        setButtonFont();
        setButtonAlignment();
        addComponents();                              
    } 
    
    public void instantiateButtons(){
        entertainmentCategory = new JRadioButton("Entertainment", true);
        sportCategory = new JRadioButton("Sport");
        geographyCategory = new JRadioButton("Geography");
        scienceCategory = new JRadioButton("Science");
        
        entertainmentCategory.setOpaque(false);                                 //make foreground JRadioButtons translucent so user can see background image                                     
        sportCategory.setOpaque(false);
        geographyCategory.setOpaque(false);
        scienceCategory.setOpaque(false);
    }
    
    public void createButtonGroup(){                                            //ensures only 1 button can be selected at a time
        buttons = new ButtonGroup();
        buttons.add(entertainmentCategory);
        buttons.add(sportCategory);
        buttons.add(geographyCategory);
        buttons.add(scienceCategory);
    }
    
    public void setButtonFont(){
        questionType.setFont(new Font("calibri", Font.BOLD + Font.ITALIC, 35));
        entertainmentCategory.setFont(new Font("calibri", Font.BOLD, 30));
        sportCategory.setFont(new Font("calibri", Font.BOLD, 30));
        geographyCategory.setFont(new Font("calibri", Font.BOLD, 30));
        scienceCategory.setFont(new Font("calibri", Font.BOLD, 30));
        userNameField.setFont(new Font("calibri", Font.PLAIN, 30));
    }
    
    public void setButtonAlignment(){
        userNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionType.setAlignmentX(Component.LEFT_ALIGNMENT);
        entertainmentCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
        sportCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
        geographyCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
        scienceCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
    
    public void addComponents(){                                                //add JTextField and JRadioButtons to centerPanel (JPanel)
        BoxLayout boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setBorder(new EmptyBorder(50, 256, 150, 150));              
        centerPanel.setLayout(boxLayout);
        
        centerPanel.add(userNameField);
        centerPanel.add(Box.createRigidArea(new Dimension(20, 30)));            //create rigid empty space
        centerPanel.add(questionType);
        centerPanel.add(Box.createRigidArea(new Dimension(20, 10)));            //(width, height)
        
        Enumeration<AbstractButton> elements = buttons.getElements();
        while (elements.hasMoreElements()) {
            JRadioButton button = (JRadioButton) elements.nextElement();
            centerPanel.add(button);                                        //add JRadioButtons
            centerPanel.add(Box.createRigidArea(new Dimension(20, 10)));    //create rigid empty space
        }
    } 
}
