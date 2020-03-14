/*
Description: Instantiates a Thread which calls setButtonColour() (Model class) to flash a button in the View class
             Invokes setTimeLimitExpired() (Model class) when a questions time limit has expired
Date: 19/5/19
Author: James Eason
 */

package model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeLimit implements Runnable{
   private Model model;
    int currentQuestion;
    int checkQuestion;
    
    public TimeLimit(){}
    
    public TimeLimit(Model object){
        model = object;
        new Thread(this, "TimeLimit").start();                                  //set Thread state to runnable (dispatcher will set to run)
    }
    
    @Override
    public void run()  {
        try{ 
            currentQuestion = model.getCurrentQuestion();
            flashStartButton();
                   
            checkQuestion = model.getCurrentQuestion();
            timeExpired();
        } catch (InterruptedException e){
            Logger.getLogger(TimeLimit.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }    
    }
    
    public void flashStartButton() throws InterruptedException{
        for (int i=0; i<=6; i++){
                Thread.sleep(1200);
                checkQuestion = model.getCurrentQuestion();
                
                if (checkQuestion == currentQuestion && model.getAnswerChecked() == false){ //if still on the current question 
                    //invokes method in Model class taht change the colour of the button (calls every 1200ms)
                    model.setButtonColour(); 
                } 
            }
    }    
    
    public void timeExpired() throws InterruptedException{  
        if (checkQuestion == currentQuestion && model.getAnswerChecked() == false){
            Thread.sleep(1200);
            //invokes method in Model class to indicate that question limit has expired
            model.timeLimitExpired();   
        } 
    }
}
