/*
Description: Connects to a Database using a Connection object.  
             Updates/Queries the Database using a Statement object.
Date: 19/5/19
Author: James Eason
 */

package com.example.quiz.model;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputOutput { 
    private Connection connection;
    private Statement statement;
    private final int NUMBER_OF_QUESTIONS = 10;
    private String url;
    private String user;
    private String password;
    
    public InputOutput(boolean urlType){
        if (urlType == true){
            url = "jdbc:derby://localhost:1527/Trivia;create=true";   //server mode, create=true  database will be created if it doesn't exist
            user = "james";
            password = "james";
        } else {
            user = "james";
            password = "james";
            url = "jdbc:derby:Trivia;create=true;"; //embedded mode - only application can access database (user cannot view tables)
        } 
    }
    
    public void connectToDatabase(){
        try{
            connection = DriverManager.getConnection(url, user, password);      
            statement = connection.createStatement();                           
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }   
    }
    
    public boolean setQuestions(int questionCategory){                             
        String tableName = getTableName(questionCategory);
        
        if (checkTableExisting(tableName)==false){   
            try{
                statement.executeUpdate(sqlCreateTable(tableName));  //create table if it doesn't exist e.g. SPOQUESTIONS                       
                return false;                                        //false indicates that table needs populating with data
            } catch (SQLException e){
                Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
                System.err.println(e);
            }       
        }
        return true;
    }
    
    //instantiate Question object that can retrieve fields for a particular questionCategory                 
    public Questions getQuestionObject(int questionCategory){  
        Questions questionType;
                                
        switch (getTableName(questionCategory)){
            case "SPOQUESTIONS":
                questionType = new SportQuestions();                                  
                break;
            case "GEOQUESTIONS":
                questionType = new GeographyQuestions();                               
                break;
            case "SCIQUESTIONS":
                questionType = new ScienceQuestions();                                
                break;
            default:
                questionType = new EntertainmentQuestions();
                break;
        }
        return questionType;
    }
    
    public void writeQuestions(Questions getQuestionInfo, int questionCategory){ //write Question data to table in database                       
        try{
            String sqlStatement = "INSERT INTO " +getTableName(questionCategory)+ " VALUES (? , ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sqlStatement);
                
            for (int questionNumber=1; questionNumber<=NUMBER_OF_QUESTIONS; questionNumber++){                                
                ArrayList<String> possibleAnswers = getQuestionInfo.getPossibleAnswer(questionNumber);
                pStmt.setInt(1, questionNumber);
                pStmt.setString(2, getQuestionInfo.getQuestion(questionNumber));
                pStmt.setString(3, possibleAnswers.get(0));
                pStmt.setString(4, possibleAnswers.get(1));
                pStmt.setString(5, possibleAnswers.get(2));                   
                pStmt.setString(6, possibleAnswers.get(3));
                pStmt.setString(7, getQuestionInfo.getAnswer(questionNumber));                    
                pStmt.executeUpdate();
            } 
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }  
    }
    
    public String sqlCreateTable(String tableName){ //creates a table for a Question category e.g. ENTQUESTIONS  
        return "CREATE TABLE " +tableName+ "(QUESTIONNUMBER INT, QUESTION VARCHAR(150), POSSIBLEANSWERA VARCHAR(100), "
        + "POSSIBLEANSWERB VARCHAR(100), POSSIBLEANSWERC VARCHAR(100), POSSIBLEANSWERD VARCHAR(100), ANSWER VARCHAR(100))";
    }
    
    public String getTableName(int questionCategory){   //returns a table name from the argument int questionCategory
        String tableName = "ENTQUESTIONS";
        
        switch (questionCategory){
            case 1:
                break;
            case 2:
                 tableName = "SPOQUESTIONS";                                       
                break;
            case 3:
                 tableName = "GEOQUESTIONS";                                    
                break;
            case 4:
                 tableName = "SCIQUESTIONS";                                    
                break;
            default:
                break;
        }
        return tableName;
    }
    
    public ResultSet getQuestions(int questionCategory){                        //returns a ResultSet object containing question data
        String tableName = getTableName(questionCategory);
        String sqlSelect = "SELECT * FROM " +tableName;                         //select all fields avaiable in the table 
        ResultSet resultSet = null;
        
        if (checkTableExisting(getTableName(questionCategory))==true){
            try{
                resultSet = statement.executeQuery(sqlSelect);                  
            } catch (SQLException e){
                Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
                System.err.println(e);
            }  
        } 
        return resultSet;
    }
    
    public ArrayList<Question> readQuestions(ResultSet resultSet){              //creates Question objects from ResultSet data
        ArrayList<Question> questions = new ArrayList<>();
        try{
            while (resultSet.next()){
                int questionNumber = resultSet.getInt("QUESTIONNUMBER");        //question number
                String question = resultSet.getString("QUESTION");              //question
                String answer = resultSet.getString("ANSWER");                  //answer   
                
                ArrayList<String> possibleAnswers = new ArrayList<>();      
                possibleAnswers.add(resultSet.getString("POSSIBLEANSWERA"));    //possibleAnswerA
                possibleAnswers.add(resultSet.getString("POSSIBLEANSWERB"));    //possibleAnswerB
                possibleAnswers.add(resultSet.getString("POSSIBLEANSWERC"));    //possibleAnswerC
                possibleAnswers.add(resultSet.getString("POSSIBLEANSWERD"));    //possibleAnswerC
            
                Question newQuestion = new Question(questionNumber, question, possibleAnswers, answer); //create Question Object
                questions.add(newQuestion);                                     //add Question object to ArrayList of Questions
            }
        } catch (SQLException e){
                Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
                System.err.println(e);
        }  
        return questions;
    }
    
    public void setUniqueGameID(int uniqueGameID){                              //updates uniqueGameID in table
        try{
            if (checkTableExisting("UNIQUEGAMEID")==false){
                statement.executeUpdate("CREATE TABLE UNIQUEGAMEID (GAMEID INT)");
            } else {
                statement.executeUpdate("DELETE FROM UNIQUEGAMEID");            //delete existing uniqueGameID from table
            }
        
            statement.executeUpdate("INSERT INTO UNIQUEGAMEID VALUES (" +uniqueGameID+ ")"); //insert new uniqueGameID into table      
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        } 
    }
    
    public int getUniqueGameID(){   
        int gameID = 6; //normally initialize gameID=0, but I have created 6 fake highScore values to demonstrate program functionality
                                                                                
        if (checkTableExisting("UNIQUEGAMEID")==true){                          //check table exists
            try{
                ResultSet resultSet = statement.executeQuery("SELECT * FROM UNIQUEGAMEID");
                while(resultSet.next()){
                    gameID = resultSet.getInt("GAMEID");                        //retrieves int gameID from ResultSet Object
                }
            } catch (SQLException e){
                Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
                System.err.println(e);
            }
        }     
        return gameID+1;                                                        //gameID+1 is the current value (gameID is allready taken)
    }    
    
    public void setHighScores(){   
        try{
            if (checkTableExisting("HIGHSCORES")==false){                       //create empty table if it doesn't exist
                statement.executeUpdate("CREATE TABLE HIGHSCORES (PLAYERNAME VARCHAR(50), GAMEID INT, PLAYERSCORE INT)");
            } else {
                statement.executeUpdate("DELETE FROM HIGHSCORES");              //if table exists delete all fields
            }  
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
    }
    
    public void writeHighScores(List<HashMap<String, Integer>> soredScores, HighScore highScore){
        try{
            Iterator iterator = soredScores.iterator();
        
            while (iterator.hasNext()) {
                HashMap<String, Integer> hScore = (HashMap) iterator.next();  
                
                int playerScore = highScore.getPlayerScore(hScore);             //playerScore                                  
                int gameID = highScore.getGameID(hScore);                       //gameID
                String playerName = highScore.getPlayerName(hScore);            //playername     
                   
                statement.addBatch(("INSERT INTO HIGHSCORES VALUES ('"+playerName+"', "+gameID+", "+playerScore+")"));
            }
            statement.executeBatch(); 
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
    }
    
    public boolean getHighScores(){                          
        if (checkTableExisting("HIGHSCORES")==false){   
            return false;   //create HIGHSCORES table in database
        } else {
            return true;    //retrieve high scores from HIGHSCORES table
        }  
    }
    
    public void createHighScores() { //creates table in database and adds some fictional scores to demonstrate functionality
        try{
            statement.executeUpdate("CREATE TABLE HIGHSCORES (PLAYERNAME VARCHAR(50), GAMEID INT, PLAYERSCORE INT)");
            statement.executeUpdate(("INSERT INTO HIGHSCORES VALUES ('Sandeep Aujla', 0, 30)")); 
            statement.executeUpdate(("INSERT INTO HIGHSCORES VALUES ('Billy', 1, 25)"));
            statement.executeUpdate(("INSERT INTO HIGHSCORES VALUES ('Gurwinder Singh', 2, 20)"));
            statement.executeUpdate(("INSERT INTO HIGHSCORES VALUES ('Jimmy', 3, 15)"));
            statement.executeUpdate(("INSERT INTO HIGHSCORES VALUES ('Dong Joon Choi',  4, 10)"));
            statement.executeUpdate(("INSERT INTO HIGHSCORES VALUES ('Sammy',  5, 5)"));
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
    }
    
    public void readHighScores(HighScore highScore){
        HashMap<String, Integer> highScores = new HashMap<>();
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM HIGHSCORES");
            while (resultSet.next()){
                String playerName = resultSet.getString("PLAYERNAME");      //playerName
                int gameID = resultSet.getInt("GAMEID");                    //gameID
                int playerScore = resultSet.getInt("PLAYERSCORE");          //playerScore
                    
                highScores.put(gameID +","+ playerName, playerScore);       //put variables into a HashMap
            }
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
        
        highScore.setHighScores(highScores);
    }
    
    public String[] getMetaData(){                                              
        String[] metaData = new String[4];                                      //String Array containing MetaData
        
        try{
            DatabaseMetaData dbmd = connection.getMetaData();
            metaData[0] = dbmd.getDatabaseProductName() +"\n";                  //DB product name
            metaData[1] = dbmd.getURL() +"\n";                                  //url
            metaData[2] = dbmd.getDriverName() +"\n";                           //driver name
            metaData[3] = dbmd.getUserName() +"\n";                             //user name
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
        
        return metaData;    
    }
    
    public void deleteTable(String tableName){                                  //deletes a table from DB
        try {
            statement.executeUpdate("DROP TABLE " +tableName);
        } catch (SQLException ex) {
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
    }
    
    public boolean checkTableExisting(String checkTableName) {                  //checks if table exists in DB
        boolean tableExists = false;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();             
            ResultSet metaData = databaseMetaData.getTables(null, null, null, null);

            while (metaData.next()) {
                String tableName = metaData.getString("TABLE_NAME");            //tableName = DB table
                if (tableName.compareToIgnoreCase(checkTableName) == 0) {       //checkTableName = name of possibly existing table
                    tableExists = true;
                }
            }
        } catch (SQLException e){
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, e);
            System.err.println(e);
        }
        
        return tableExists;
    }
}

