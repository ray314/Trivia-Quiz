/*
Description: Tests Methods in HighScore class
Date: 28/5/19
Author: James Eason
 */
package modelTesting;

import java.util.HashMap;
import java.util.NoSuchElementException;
import model.HighScore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HighScoreTest {
    private HighScore highScore = new HighScore();
    private HashMap<String, Integer> score1;
    private HashMap<String, Integer> score2;
    private String key1, key2;
    private int value1, value2;
    
    @Before
    public void setUp() {
        score1 = new HashMap<>();
        score2 = new HashMap<>();
        key1 = "3,Ringo";
        key2 = "1,Billy";
        value1 = 34;
        value2 = 33;
    }

    /**
     * Test of getPlayerScore method, of class HighScore.
     * public int getPlayerScore(HashMap<String, Integer> hashMap)
     * returns int (±∞)
     */
    @Test
    public void testGetScoreNegative() {                                        //int playerScore = -100
        String key = "53,Ringo";
        int playerScore = -100; 
        score1.put(key, playerScore);
        
        int expected = -100;
        int actual = highScore.getPlayerScore(score1);
        Assert.assertEquals(expected, actual);
    }   
    
    @Test
    public void testGetScoreZero() {                                            //int playerScore = 0
        String key = "53,Ringo";
        int playerScore = 0; 
        score1.put(key, playerScore);
        
        int expected = 0;
        int actual = highScore.getPlayerScore(score1);
        Assert.assertEquals(expected, actual);
    }   
    
    @Test
    public void testGetScorePositive() {                                        //int playerScore = 999
        String key = "53,Ringo";
        int playerScore = 999; 
        score1.put(key, playerScore);
        
        int expected = 999;
        int actual = highScore.getPlayerScore(score1);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test (expected = NoSuchElementException.class)
    public void testGetScoreDouble() {                                          //double playerScore = 13.692
        HashMap<String, Double> scoreDouble = new HashMap<>();
        String key = "53,Ringo";
        double playerScore = 13.692; 
        scoreDouble.put(key, playerScore);
        
        double actual = highScore.getPlayerScore(score1);
    } 
    
    /**
     * Test of getPlayerName method, of class HighScore.
     * public String getPlayerName(HashMap<String, Integer> hashMap)
     * HashMap Key = UniqueGameID +","+  playerName
     *      where playerName is a String
     */
    @Test
    public void testGetNameString() {                                           //playerName = "Ringo"
        String key = "3,Ringo";   
        int value = 23; 
        score1.put(key, value);
        
        String expected = "Ringo";
        String actual = highScore.getPlayerName(score1);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test
    public void testGetNameNull() {                                             //playerName = null
        String playerID = "3";
        String playerName = null;
        String key = playerID +","+ playerName;
        int value = 10;
        score1.put(key, value);
        
        String expected = "null";
        String actual = highScore.getPlayerName(score1);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test (expected = NoSuchElementException.class)
    public void testGetNameEmptyString() {                                      //playerName = ""
        String playerID = "3";
        String playerName = "";
        String key = playerID +","+ playerName;
        int value = 10;
        score1.put(key, value);
        
        String actual = highScore.getPlayerName(score1);
    }
    
    @Test (expected = NoSuchElementException.class) //change parameter passed to method from - HashMap<String, Integer> hashMap
    public void testGetNameIncorrectParameter() {   //to - HashMap<Integer, String> hashMap                                
        HashMap<Integer, Integer> integerHashMap = new HashMap<>();
        int value = 3;
        int key = 4;
        integerHashMap.put(key, value);
        
        String actual = highScore.getPlayerName(score1);
    } 
    
    /**
     * Test of compare method, of class HighScore.
     * public int compare(HashMap<String, Integer> score1, HashMap<String, Integer> score2){
     * HashMap Value = playerScore 
     * Method compares playerScore for each HashMap
     */
    @Test
    public void testCompareScore1Greater() {                                    //score1 Value > score2 Value
        score1.put(key1, value1);
        score2.put(key2, value2);
        
        int expected = -1;
        int actual = highScore.compare(score1, score2);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test
    public void testCompareScore2Greater() {                                    //score1 Value < score2 Value
        int value = -503; 
        score1.put(key1, value);
        score2.put(key2, value2);
        
        int expected = 1;
        int actual = highScore.compare(score1, score2);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test
    public void testCompareID1Greater() {                                       //score1 Value == score2 Value
        int valueA = 13;                                                        //playerID1 > playerID2 
        int valueB = 13;                                                        //HashMap Key contains playerID
        score1.put(key1, valueA);
        score2.put(key2, valueB);
        
        int expected = 1;
        int actual = highScore.compare(score1, score2);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test
    public void testCompareID2Greater() { //score1 Value == score2 Value 
        String keyB = "164,Billy";  //playerID1 < playerID2          
        int valueA = 13; 
        int valueB = 13;
        score1.put(key1, valueA);
        score2.put(keyB, valueB);
        
        int expected = -1;
        int actual = highScore.compare(score1, score2);
        Assert.assertEquals(expected, actual);
    } 
    
    @Test
    public void testCompareEqual() {                                            //score1 Value == score2 Value 
        String keyB = "3,Billy";                                                //playerID1 == playerID2          
        int valueA = 13; 
        int valueB = 13;
        score1.put(key1, valueA);
        score2.put(keyB, valueB);
        
        int expected = 0;
        int actual = highScore.compare(score1, score2);
        Assert.assertEquals(expected, actual);
    }
    
    @Test (expected = NumberFormatException.class)
    public void testCompareNull() {                                             //score1 Value = null        
        Integer valueA = new Integer(null); 
        score1.put(key1, valueA);
        score2.put(key2, value2);
        
        int actual = highScore.compare(score1, score2);
    }
}
