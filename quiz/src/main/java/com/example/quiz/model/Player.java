/*
Description: Contains a players current: score, gameID (unique to each game) and player name.
Date: 1/4/19
Author: James Eason
 */

package com.example.quiz.model;

public class Player {
    
    private  String playerName;
    private  int playerScore;
    private int uniqueGameID;   //uniqueGameID is used in sorting highScores with compare() method in HighScore class
    
    public Player(){}
    
    public Player(String name, int gameID){
        playerName = name;
        playerScore = 0;    //playerScore starts from 0 when a Player is created
        uniqueGameID = gameID;
    }
    
    public void setScore(int changeToScore){                                    //updates playerScore when user gets a question right/wrong
        playerScore +=  changeToScore;
    }
    
    public int getScore(){                                                      //returns current playerScore
        return playerScore;
    }
    
    public void setUniqueGameID(int gameID){                                    //sets uniqueGameID when read from DB
        uniqueGameID = gameID;
    }
    
    public int getUniqueGameID(){                                   
        return uniqueGameID;
    }
    
    public void setName(String name){
        playerName = name;
    }
    
    public String getName(){
        return playerName;
    }
}
        

    



