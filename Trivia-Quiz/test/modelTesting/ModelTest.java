/*
Description: Tests methods in the Model class.
Date: 28/5/19
Author: James Eason
 */
package modelTesting;

import controller.UpdateInfo;
import java.util.ArrayList;
import junit.framework.Assert;
import model.Model;
import model.Question;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {

    private Model model;
    private UpdateInfo updateInfo;
    
    @Before
    public void Before() {
        model = new Model();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }   

    /**
     * Test of setCheckUserDetails method, of class Model.  
     * setScheckUserDetails(String userName) returns true if String is [1,20] characters long
     */  
    @Test
    public void testSetCheckUserDetails5() { //test for String with length withing [1,20] = 5
        boolean expected = false;
        boolean actual = model.checkUserDetails("James");
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testSetCheckUserDetails1() { //test for String with length lower bounder = 1
        boolean expected = false;
        boolean actual = model.checkUserDetails("J");
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testSetCheckUserDetails20() { //test for String with length upper bounder = 20
        boolean expected = false;
        boolean actual = model.checkUserDetails("Alexander Abrahamson");
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testSetCheckUserDetails0() { //test for String with length outside of bounds = 0
        boolean expected = true;
        boolean actual = model.checkUserDetails("");
        Assert.assertEquals(expected, actual);
    }
    
     /**
     * Test of readQuestions method, of class Model.  
     * public void readQuestions(int questionCategory){
     * takes an int argument: [1,4]
     */ 
    @Test
    public void testReadQuestionsArgument1(){                                           
        String expected = "Who is the lead singer of the band Nirvana?";
        model.readQuestions(1);                                                 //questionCategory = 1 (lower boundary)

        ArrayList<Question> questions = model.getQuestions();
        Question firstQuestion = questions.get(0);                              //get Question element 0 from ArrayList
        String actual = firstQuestion.getQuestion();                            //get the question to ask user from firstQuestion
        Assert.assertEquals(expected, actual);                                  //compare expected question and actual question
    }
    
    @Test
    public void testReadQuestionsArgument4(){                                           
        String expected = "What is the outer layer of the Earth called?";
        
        model.readQuestions(4);                                                 //questionCategory = 4 (upper boundary)

        ArrayList<Question> questions = model.getQuestions();
        Question fifthQuestion = questions.get(5);                              
        String actual = fifthQuestion.getQuestion();                            
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testReadQuestionsArgument2(){                                           
        String expected = "<html>Who is the third highest paid sportsperson <br>in New Zealand?</html>";
        
        model.readQuestions(2);                                                 //questionCategory = 2 (within bounds)

        ArrayList<Question> questions = model.getQuestions();
        Question fifthQuestion = questions.get(9);                              
        String actual = fifthQuestion.getQuestion();                            
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testReadQuestionsArgument5(){                                           
        String expected = "<html>In the movie \"Lord of the Rings\" what's the <br>last name of the Hobbit Frodo?</html>";
        
        model.readQuestions(5);                                                 //questionCategory = 5 (outside of bounds)

        ArrayList<Question> questions = model.getQuestions();
        Question fifthQuestion = questions.get(1);                              
        String actual = fifthQuestion.getQuestion();                            
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test of checkAnswer method, of class Model.  
     * public void checkAnswer(int playerAnswer){
     * takes int playerAnswer argument with elements: [0,4]
     */ 
    @Test
    public void testcheckAnswerArgument0(){
        boolean expected = false;
        model.readQuestions(1);                                                 //read Questions from database                          
        model.checkAnswer(0);                                                   //playerAnswer = 0 (lower boundary)
        updateInfo = model.getUpdateInfo();
        boolean actual = updateInfo.getAnswerCorrect();                         //returns true if playerAnswer is the correct answer
        
        Assert.assertEquals(expected, actual);  
    }
    
    @Test
    public void testcheckAnswerArgument1(){
        boolean expected = true;
        model.readQuestions(1);                                                                        
        model.checkAnswer(1);                                                   //playerAnswer = 1 (within bounds)
        updateInfo = model.getUpdateInfo();
        boolean actual = updateInfo.getAnswerCorrect();                         
        
        Assert.assertEquals(expected, actual);  
    }
    
    @Test
    public void testcheckAnswerArgument3(){
        boolean expected = false;
        model.readQuestions(2);                                                                        
        model.checkAnswer(3);                                                   //playerAnswer = 3 (upper boundary)
        updateInfo = model.getUpdateInfo();
        boolean actual = updateInfo.getAnswerCorrect();                         
        
        Assert.assertEquals(expected, actual);  
    }
    
    @Test
    public void testcheckAnswerArgument555(){
        boolean expected = false;
        model.readQuestions(2);                                                                        
        model.checkAnswer(3);                                                   //playerAnswer = 555 (outside bounds)
        updateInfo = model.getUpdateInfo();
        boolean actual = updateInfo.getAnswerCorrect();                         
        
        Assert.assertEquals(expected, actual);  
    }
}
