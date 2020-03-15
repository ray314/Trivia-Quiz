/*
Description: Receives ActionEvents and MouseEvents from Objects in the View class.
             Interacts with Model and View classes.
Date: 19/5/19
Author: James Eason /
 */

package controller;

import model.Model;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener, KeyListener{
    private Model model;
    private View view;
    
    public Controller(){}
    
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;

        this.view.setActionListener(this);                                      //adds ActionListener to Objects in View class
        this.view.setMouseListener(this);                                       //adds MouseListener to Objects in View class
        this.view.setKeyListener(this);                                         //adds KeyListener to Object in View class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String eventString = e.getActionCommand();
            
            switch (eventString){
                case("Continue"):
                    view.mainMenu();                                        //calls method displaying Main Menu
                    break;                
                case ("Read Me"):
                    model.setInstructions();                                    //calls method displaying Instructions
                    break;
                case ("View High Scores"):                                      
                    viewHighScores();
                    break;
                case ("Exit"):                                                  //ends program
                    System.exit(0);
                    break;
                case ("Begin Game"):
                    beginGame();
                    break;
                case ("Play Game"):                                             //calls method asking for User Details
                    view.getPlayerDetails();
                    break;
                default:
                    break;       
            }          
        } catch (NumberFormatException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {              
        if (!model.getAnswerChecked()) {                                         //if answer has not been marked/checked
            model.checkAnswer(view.getPlayerAnswer());                       //check if user answer is correct
        } else {           
            if (model.getCurrentQuestion() < model.getNumberQuestions()){       //if questions remaining       
                model.askQuestion();                                            //ask another question
            } else {                                                            //else 
                model.writeGameID();                                            //call methods to finish game
                model.setHighScore();                                                                    
                model.setSortedHighScores();                                        
                model.writeHighScores();                                        
                model.setCurrentQuestion(0);                                    
                model.checkNewHighScore();                                      
            }                                                                   
        }    
    } 
    
    @Override
    public void keyPressed(KeyEvent e) {
            if (view.getUserNameLength() >= 20) 
                view.setUserNameLength();
    }
        
    public void viewHighScores(){
        model.readHighScores();                                                 //calls method displaying High Scores
        model.setSortedHighScores();
        model.stringHighScores();
    }
    
    public void beginGame(){
        String usersName = view.getUserNameField();                 

        if(!model.checkUserDetails(usersName)){                                 //if true calls methods to begin game
            int questionCategory = view.getQuestionCategory();      
            model.setPlayer(usersName);
            model.readHighScores();                                
            model.readQuestions(questionCategory);
            model.randomlySelectedQuestions();
            model.askQuestion();                                    
        }
    }
    
    @Override                                                                   //unused imports
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
