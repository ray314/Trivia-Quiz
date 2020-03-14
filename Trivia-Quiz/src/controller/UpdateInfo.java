/*
Description: Passes information between the Model and View class
Date: 19/5/19
Author: James Eason
 */

package controller;

import java.util.ArrayList;

public class UpdateInfo {
    private boolean[] flags;    //setting a flag to true indicates a method in View class should be called
    private boolean changeButton, timeLimitExpired, askQuestion, checkAnswer, answerCorrect,
            incorrectUserDetails, showNewHighScore, viewReadMe, viewHighScores, mainMenu;
    private ArrayList<String> highScores, possibleAnswers;   //information received from Model class and accessed by View class
    private String[] instructions, newHighScore;
    private String correctAnswer, questionString;
    private int buttonColor;
       
    public UpdateInfo(){
        flags = new boolean[9];
        flags[0] = changeButton; 
        flags[1] = timeLimitExpired; 
        flags[2] = askQuestion;
        flags[3] = checkAnswer; 
        flags[4] = incorrectUserDetails;
        flags[5] = showNewHighScore; 
        flags[6] = viewReadMe; 
        flags[7] = viewHighScores;
        flags[8] = mainMenu;
    }

    public boolean getAnswerCorrect() {
        return answerCorrect;
    }
        
    public String[] getInstructions(){
        return instructions;
    }
        
    public ArrayList<String> getStringHighScores(){
        return highScores;
    }
    
    public int getSelectedFlag(){
        int selectedFlag = 0;
        for (int i=0; i<10; i++){
            if (flags[i] == true){
                selectedFlag = i;
                break;  //break for loop when flag has been identified as true
            }
        }
        return selectedFlag;
    }
    
    public String getCorrectAnswer(){
        return correctAnswer;
    }
    
    public String[] getNewHighScore(){
        return newHighScore;
    }
     
    public int getButtonColor() {
        return buttonColor;         
    }
    
    public String getQuestionString() {
        return questionString;
    }
    
    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }
    
    public void setAnswerCorrect(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public void setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
    }

    public void setNewHighScore(String[] newHighScore) {
        this.newHighScore = newHighScore;
    }
    
    public void setCorrectAnswer(String answer){
        correctAnswer = answer;
    }
    
    public void setInstructions(String[] instructions){
        this.instructions = instructions;
    }   
    
    public void setStringHighScores(ArrayList<String> highScores){
        this.highScores = highScores;
    }
       
    public void setFlag(int flag, boolean value){
        flags[flag] = value;    //used to set flag status
    }
}

