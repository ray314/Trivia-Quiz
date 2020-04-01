/*
Description: Stores, updates and sorts HashMaps that contain a players score.
             HashMap<String, Integer> highScores = new HashMap<>(); contains data about a particular game.   
                Key = UniqueGameID() +","+  playerName.
                Value = playerScore.
                    uniqueGameID, playerName and playerScore are fields of Player class
Date: 1/4/19
Author: James Eason
 */

package com.example.quiz.model; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HighScore implements Comparator<HashMap<String, Integer>>{         //overrides public int compare(Object obj)
    private List<HashMap<String, Integer>> sortedHighScores;                    
                                                                                
    private  HashMap<String, Integer> highScores;                               
    
    public HighScore(){
        highScores = new HashMap<>();
    }
    
    public HashMap<String, Integer> returnHighScores(){
        return highScores;
    }
    
    public int getPlayerScore(HashMap<String, Integer> hashMap){                //returns the Value (playerScore) from the HashMap argument
        String key = hashMap.keySet().iterator().next();               
        
        return hashMap.get(key);
    }   
    
    public int getGameID(HashMap<String, Integer> hashMap){                     //returns uniqueGameID from the HashMap argument
        String key = hashMap.keySet().iterator().next();               
        StringTokenizer st = new StringTokenizer(key, ",\n");             
        
        return Integer.parseInt(st.nextToken());
    }
    
    public String getPlayerName(HashMap<String, Integer> hashMap){              //returns playerName from the HashMap argument
        String key = hashMap.keySet().iterator().next();               
    
        StringTokenizer st = new StringTokenizer(key, ",\n");             
        int gameID = Integer.parseInt(st.nextToken());
        String playerName = st.nextToken();
        
        return playerName;
    }
    
    public void setHighScores(HashMap<String, Integer> newHighScores){          //adds HashMap to highscores containing the following:    
        highScores = newHighScores;                                             //Key = UniqueGameID() +","+ playerName
    }                                                                           //Value = playerScore
                
        
    public void addHighScore(Player player){                                    //adds playerScore (in Player class) to highScore 
        String key = player.getUniqueGameID() +","+ player.getName();
        int value = player.getScore();
        highScores.put(key, value);   
    }
    
    public void checkScoresPositive(){                                          //deletes any scores < 0
        List<HashMap<String, Integer>> positiveScores = new ArrayList<>();
        
        Iterator<String> keyIterator = highScores.keySet().iterator();
        while (keyIterator.hasNext()){
            String key = keyIterator.next();                                    //Key = UniqueGameID() +","+  playerName.
            int score = highScores.get(key);                                    //Value = playerScore
            
            if (score > 0){                                                     //only keep positive scores
                HashMap<String, Integer> positiveScore = new HashMap<>();
                positiveScore.put(key, score);
                positiveScores.add(positiveScore);                            
            }
        }
        
        sortedHighScores = positiveScores;
    }
    
    public void sortScoresDescending(){//
        Collections.sort(sortedHighScores, this);                               //sort scores in descending order
    }
    
    public void topTenScores(){//
         List<HashMap<String, Integer>> topTenScores = new ArrayList<>();   
        
        for (int i=0; i<sortedHighScores.size() && i<10; i++){                  //only keep the top 10 scores
            HashMap<String, Integer> sortedScore = sortedHighScores.get(i);
            topTenScores.add(sortedScore);
        } 
        sortedHighScores = topTenScores; 
    }

    
    public List<HashMap<String, Integer>> getSortedHighScores(){                //returns sortedHighScores 
        return sortedHighScores;
    }
    
    public ArrayList<String> getStringHighScores(){ 
        Iterator iterator = sortedHighScores.iterator();
        ArrayList<String> scoresToReturn = new ArrayList();
        
        for (int scoreRanking=1; iterator.hasNext() ; scoreRanking++) {
            HashMap<String, Integer> highScore = (HashMap) iterator.next();     //get a HashMap Object containg player score information
          
            scoresToReturn.add(Integer.toString(scoreRanking));                 //ranking e.g. 1
            scoresToReturn.add(getPlayerName(highScore));                       //name e.g. "James"
            scoresToReturn.add(Integer.toString(getPlayerScore(highScore)));    //score e.g. 20
        }
        return scoresToReturn; //returns empty ArrayList if there are no highScores
    }
    
    public String[] checkNewHighScore(Player player){                           //checks if playerScore is an element of sortedHighScores (top 10 high score)
        String[] newHighScore = new String[3];
        Iterator iterator = sortedHighScores.iterator();
        
        for (int rank=1; iterator.hasNext(); rank++) {
            HashMap<String, Integer> scoreDetails = (HashMap) iterator.next();
            
            if(player.getUniqueGameID() == getGameID(scoreDetails)){ //if current player gameID = gameID contained in sortedHighScores player has got a new high score
                newHighScore[0] = String.valueOf(rank);                             //rank
                newHighScore[1] = getPlayerName(scoreDetails);                      //name
                newHighScore[2] = Integer.toString(getPlayerScore(scoreDetails));   //score
            }   
        }
        return newHighScore;
    }
    
    @Override
    public int compare(HashMap<String, Integer> score1, HashMap<String, Integer> score2){ //invoked using Collestions.sort()                           
        if (getPlayerScore(score1) < getPlayerScore(score2)){                   //if (score1 < score2)                                                   
            return 1;                                                           
        } else if (getPlayerScore(score1) > getPlayerScore(score2)){            //else if (score1 > score2)
            return -1;
        } else {                                                                //else (score1 == score2)
            if (getGameID(score1) < getGameID(score2)){                         //if (gameID1 < gameID2)
                return -1;
            } else if (getGameID(score1) > getGameID(score2)) {                 //else if (gameID1 > gameID2>)
                return 1;
            } else {                                                            //else (gameID1 == gameID2) - this condition should not occur
                return 0;
            }  
        }
    }
}



