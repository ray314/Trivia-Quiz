/*
Description: Tests methods in InputOutput class
Date: 17/5/19
Author: James Eason
 */
package modelTesting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import model.EntertainmentQuestions;
import model.GeographyQuestions;
import model.InputOutput;
import model.Model;
import model.Questions;
import model.ScienceQuestions;
import org.junit.BeforeClass;
import org.junit.Test;

public class InputOutputTest {
    private static InputOutput inputOutput;
    private Model model = new Model();
    private static  String[] metaData;
    
    @BeforeClass
    public static void setUpClass() {
        inputOutput = new InputOutput(false);
        inputOutput.connectToDatabase();
        metaData = inputOutput.getMetaData();
    }         
    
    /**
     * Tests of getQuestionObject method, of class InputOutput.                       
     * public Questions getQuestionObject(int questionCategory){ 
     * returns Question object that is an instance of either: EntertainmentQuestions, Geography Questions, ScienceQuestions, SportQuestions
     * Argument int questionCategory: [1,4]
     */
    @Test
    public void testGetQuestionObjectArgument1() {    //questionCategory = 1 (lower boundary)                                                  
        boolean expected = true;
        Questions returnQuestion = inputOutput.getQuestionObject(1);
        boolean actual = (returnQuestion instanceof EntertainmentQuestions); 
        
        Assert.assertEquals(expected, actual);  
    }
    
    @Test
    public void testGetQuestionObjectArgument4() {    //questionCategory = 4 (upper boundary)                                                  
        boolean expected = true;
        Questions returnQuestion = inputOutput.getQuestionObject(4);
        boolean actual = (returnQuestion instanceof ScienceQuestions); 
        
        Assert.assertEquals(expected, actual);  
    }
    
    @Test
    public void testGetQuestionObjectArgument2() {    //questionCategory = 2 (within bounds)                                                  
        boolean expected = false;
        Questions returnQuestion = inputOutput.getQuestionObject(4);
        boolean actual = (returnQuestion instanceof GeographyQuestions); 
        
        Assert.assertEquals(expected, actual);  
    }
    
    @Test
    public void testGetQuestionObjectArgument83() {    //questionCategory = 83 (outside bounds)                                                  
        boolean expected = true;
        Questions returnQuestion = inputOutput.getQuestionObject(83);
        boolean actual = (returnQuestion instanceof EntertainmentQuestions); 
        
        Assert.assertEquals(expected, actual);  
    }
    
    /**
     * Tests of getQuestions method, of class InputOutput.                       
     * public ResultSet getQuestions(int questionCategory){
     * returns ResultSet object that contains either: EntertainmentQuestions, Geography Questions, ScienceQuestions or SportQuestions
     * Argument int questionCategory: [1,4]
     */
    @Test
    public void testGetQuestionsArgument1() {                                                 
        boolean expected = true;
        boolean actual;
       
        String questionToAsk = null;
        ResultSet resultSet = inputOutput.getQuestions(1);                      //questionCategory = 1 (lower boundary)     
        try{
            while (resultSet.next()){       
                questionToAsk = resultSet.getString("QUESTION");                //questionToAsk is a String containing 10 questions
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputOutputTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringTokenizer st = new StringTokenizer(questionToAsk, "\n");
        String lastQuestion = st.nextToken();                                   //retrieve the first question from the String
        actual = lastQuestion.equals("<html>Who is Bart Simpsons best friend at <br>Springfield Elementary School?</html>"); //lastQuestion equals the first question read from the database (for questionCateogry 1)
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetQuestionsArgument4() {                                                 
        boolean expected = true;
        boolean actual;
       
        String questionToAsk = null;
        ResultSet resultSet = inputOutput.getQuestions(4);                      //questionCategory = 4 (upper boundary)     
        try{
            while (resultSet.next()){       
                questionToAsk = resultSet.getString("QUESTION");                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputOutputTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringTokenizer st = new StringTokenizer(questionToAsk, "\n");
        String lastQuestion = st.nextToken();                                   
        actual = lastQuestion.equals("Who invented the Light Bulb?");
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetQuestionsArgument2() {                                                 
        boolean expected = true;
        boolean actual;
       
        String questionToAsk = null;
        ResultSet resultSet = inputOutput.getQuestions(2);                      //questionCategory = 2 (within bounds)     
        
        try{
            while (resultSet.next()){       
                questionToAsk = resultSet.getString("QUESTION");                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputOutputTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringTokenizer st = new StringTokenizer(questionToAsk, "\n");
        String lastQuestion = st.nextToken();                                   
        actual = lastQuestion.equals("<html>Who is the third highest paid sportsperson <br>in New Zealand?</html>");
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetQuestionsArgument99() {                                                 
        boolean expected = true;
        boolean actual;
       
        String questionToAsk = null;
        ResultSet resultSet = inputOutput.getQuestions(99);                     //questionCategory = 99 (outside bounds)     
        
        try{
            while (resultSet.next()){       
                questionToAsk = resultSet.getString("QUESTION");                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InputOutputTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringTokenizer st = new StringTokenizer(questionToAsk, "\n");
        String lastQuestion = st.nextToken();                                   
        actual = lastQuestion.equals("<html>Who is Bart Simpsons best friend at <br>Springfield Elementary School?</html>");
        
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests of getMetaData method, of class InputOutput.                       
     * public String[] getMetaData(){
     * test returned String[] containing elements: [0,3]
     */
    @Test
    public void testGetMetaDataElement0() {                                                 
        boolean expected = true;
        boolean actual;
       
        String element = (metaData[0]);                                        //element 0 (lower boundary)
        actual = element.equals("Apache Derby\n");
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetMetaDataElement3() {                                                 
        boolean expected = true;
        boolean actual;
       
        String element = (metaData[3]);                                         //element 3 (upper boundary)
        actual = element.equals("james\n");
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetMetaDataElement2() {                                                 
        boolean expected = true;
        boolean actual;
       
        String element = (metaData[2]);                                         //element 2 (within boundary)
        actual = element.equals("Apache Derby Embedded JDBC Driver\n");
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testGetMetaDataElement50() {                                                 
        String element = (metaData[50]);                                        //element 50 (outside bounds)
    }
}