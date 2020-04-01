/*
Description: A template that contains abstract methods to implement in various types of Question classes e.g. EntertainmentQuestions
Date: 1/4/19
Author: James Eason
 */
package com.example.quiz.model;

import java.util.ArrayList;

public interface Questions {
    
    public void setQuestions();  
    
    public String getQuestion(int questionNumber); 
    
    public void setPossibleAnswers();  
    
    public ArrayList<String> getPossibleAnswer(int questionNumber);  
    
    public void setAnswers();  
    
    public String getAnswer(int questionNumber);  
}
