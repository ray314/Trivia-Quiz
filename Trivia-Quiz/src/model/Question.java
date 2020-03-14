/*
Description: Contains fields for Question objects.
Date: 1/4/19
Author: James Eason
 */

package model;

import java.util.ArrayList;

public class Question {
    private int questionNumber;
    private String question;
    private ArrayList<String> possibleAnswers;
    private String answer;
    
    public Question(int questionNumber, String question, ArrayList<String> possibleAnswers, String answer){
        this.questionNumber = questionNumber;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.answer = answer;
    }
    
    public int getQuestionNumber(){
        return questionNumber;
    }
    
    public String getQuestion(){
        return question;
    }
    
    public ArrayList<String> getPossibleAnswers(){
        return possibleAnswers;
    }
    
    public String getAnswer(){
        return answer;
    }
}
