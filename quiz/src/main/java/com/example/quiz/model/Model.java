/*
Description: Stores and updates game Objects/fields.
             Notifies Observer in View class of changes to game Objects/fields.
Date: 19/5/19
Author: James Eason
 */

package com.example.quiz.model;

import controller.UpdateInfo;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Model extends Observable {
    private InputOutput database;
    private HighScore highScore;
    private UpdateInfo updateInfo;
    private Player player;
    private ArrayList<Question> questions;
    private final int NUMBER_OF_QUESTIONS = 5;  //number of Questions to ask user per game
    private boolean answerChecked;
    private int currentQuestion;
    private int buttonColor;
    
    public Model(){
        database = new InputOutput(false);  //use embedded url
        database.connectToDatabase();
        highScore = new HighScore();
        updateInfo = new UpdateInfo();
        currentQuestion = 0;
        buttonColor = 0;    //alternates between values 0 and 1
    }
    
    public int getNumberQuestions(){
        return NUMBER_OF_QUESTIONS;
    }
    
    public int getCurrentQuestion(){
        return currentQuestion;
    }
    
    public ArrayList<Question> getQuestions(){                                   
        return questions;
    }
    
    public boolean getAnswerChecked(){
        return answerChecked;
    }
    
    public UpdateInfo getUpdateInfo(){
        return updateInfo;                                                      //private updateInfo object is used in testing
    }
           
    public void randomlySelectedQuestions(){                                    //randomly selects 5/10 Questions
        ArrayList<Question> listOfQuestions = new ArrayList<>();
        
        boolean[] question = new boolean[10];                                   //create empty boolean array representing each Question Object
        Arrays.fill(question, true);                                            //set all boolean value true (Question is available to be selected)
        
        Random random = new Random();                                           
        for (int counter = 0; counter < 5;) {
            int randomNumber = random.nextInt(9);                               //generate a random Question number
            if (question[randomNumber] == true) {                               
                listOfQuestions.add(questions.get(randomNumber));               //add random Question to Array of Questions 
                question[randomNumber] = false;
                counter++;
            }
        }
        
        questions = listOfQuestions;                                            //quesions now contains 5 randomly selected Questions
    }
    
    public void setButtonColour(){                                              //alternates button colour
        if (buttonColor == 0){
            buttonColor = 1;                                                    //1 = orange button
        } else {
            buttonColor = 0;                                                    //2 = grey button
        }
        updateInfo.setFlag(0, true); 
        updateInfo.setButtonColor(buttonColor);
        setChanged();
        notifyObservers(updateInfo);
    }
    
    public void setCurrentQuestion(int number){
        currentQuestion = number;
    }
    
    public void setPlayer(String userName){
        Player player = new Player(userName, readGameID());
        this.player = player;
    }    
    
    public void setInstructions(){                                              
        ReadMe readMe = new ReadMe(database);
        readMe.setInstructions();
        readMe.setMetaData(database);
        String[] instructions = readMe.getReadMeString();                       //Arry String contains instructions
        
        updateInfo.setInstructions(instructions);
        updateInfo.setFlag(6, true);                                            //flag calls viewReadMe() in View class
        setChanged();
        notifyObservers(updateInfo);
    }
     
    public void stringHighScores(){
        updateInfo.setStringHighScores(highScore.getStringHighScores());
        updateInfo.setFlag(7, true);                                            //flag calls viewHighScores() in View class
        setChanged();
        notifyObservers(updateInfo);
    }
    
    public void setHighScore(){
        highScore.addHighScore(player);
    }
    
    public void setSortedHighScores(){ 
        highScore.checkScoresPositive();
        highScore.sortScoresDescending();
        highScore.topTenScores();  
    }
    
    public boolean checkUserDetails(String userName) {                       
        boolean incorrectUserDetails = false;
        
        if (userName.isEmpty() || userName.equals("") || userName.length() > 20){//if invalid unserName
            updateInfo.setFlag(4, true);                                        //flag calls incorrectUserDetails() in View class
            setChanged();
            notifyObservers(updateInfo);
            incorrectUserDetails = true;
        }
        
        return incorrectUserDetails;
    }
    
    public void checkAnswer(int playerAnswer){
        boolean answerCorrect;
        Question question = questions.get(currentQuestion);
        ArrayList<String> possibleAnswers = question.getPossibleAnswers();
        
        String playersAnswer = possibleAnswers.get(playerAnswer);
        String correctAnswer = question.getAnswer();
        
        if (playersAnswer.equals(correctAnswer)) {
            answerCorrect = true;
            if (player!= null) player.setScore(10);                             //+10 playersScore if correct
        } else {
            answerCorrect = false;
            if (player!= null) player.setScore(-5);                             //-5 playeScore if incorrect
        }
        
        answerChecked = true;
        currentQuestion++;                                                      //currentQuestion has finished, increment currentQuestion

        updateInfo.setFlag(3 , true);                                           //flag calls checkAnswer() in View class
        updateInfo.setAnswerCorrect(answerCorrect);                             //answerCorrect boolean set to true/false
        updateInfo.setCorrectAnswer(correctAnswer);
        setChanged();
        notifyObservers(updateInfo);
    }
       
    public void timeLimitExpired(){          
        player.setScore(-3);                                                    //-3 playerScore if time limit expire 
        answerChecked = true;                                                   //don't check answer as user has allready run out of time
        updateInfo.setFlag(1 , true);                                           //call timeLimitExpired() in View class
        
        Question question = questions.get(currentQuestion);
        updateInfo.setCorrectAnswer(question.getAnswer()); 
        currentQuestion++;                                                      //currentQuestion has finished, increment currentQuestion
        setChanged();
        notifyObservers(updateInfo);
    }
       
    public void askQuestion(){
        answerChecked = false;
        
        Question question = questions.get(currentQuestion);  
        
        updateInfo.setQuestionString(question.getQuestion());
        updateInfo.setPossibleAnswers(question.getPossibleAnswers());
        updateInfo.setFlag(2 , true);    //askQuestion()

        TimeLimit beginTiming = new TimeLimit(this);                            //instantiates Thread in TimeLimit class
        
        setChanged();
        notifyObservers(updateInfo);
    }
         
    public int readGameID(){
        return database.getUniqueGameID();
    }
        
    public void readHighScores(){
        if (database.getHighScores() == true){  //if HIGHSCORES table exists in database
            database.readHighScores(highScore); //retrieve data
        } else {                                //else
           database.createHighScores();         //create HIGHSCORES table in database
           database.readHighScores(highScore);
        }
    }
    
    public void writeGameID(){
        database.setUniqueGameID(player.getUniqueGameID());
    }
    
    public void readQuestions(int questionCategory){
        boolean questionsExist = database.setQuestions(questionCategory);                                //creates table containg Question data if it doesn't exist
        
        if (!questionsExist){
                Questions questionType = database.getQuestionObject(questionCategory);
                database.writeQuestions(questionType, questionCategory);
            }    
            ResultSet resultSet =  database.getQuestions(questionCategory);
            ArrayList<Question> listOfQuestions = database.readQuestions(resultSet);
          
        questions = listOfQuestions;
    }  
    
    public void writeHighScores(){
        database.setHighScores();
        List<HashMap<String, Integer>> sortedScores = highScore.getSortedHighScores();
        database.writeHighScores(sortedScores, highScore);
    }
    
    public void checkNewHighScore(){
        String[] checkScore = highScore.checkNewHighScore(player);
        if (checkScore[0] != null){
            updateInfo.setNewHighScore(checkScore);
            updateInfo.setFlag(5, true);                                        //flag calls showNewHighScore() in View class
        } else {
            updateInfo.setFlag(8, true);                                        //flag calls mainMenu() in View class
        }
        setChanged();
        notifyObservers(updateInfo);
    }
}
