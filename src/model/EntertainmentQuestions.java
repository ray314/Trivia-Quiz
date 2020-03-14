/*
Description: Contains Entertainment qestions, answers and possible answers that are written to database if ENTQUESTIONS table does not exist
Date: 1/4/19
Author: James Eason
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;

public class EntertainmentQuestions implements Questions{
    ArrayList<String> questions;
    ArrayList<String> answers;
    ArrayList<ArrayList<String>> possibleAnswers;
    
    public EntertainmentQuestions(){
        setQuestions();
        setAnswers();
        setPossibleAnswers();
    }
    
    public void setQuestions(){ //<br> starts a new line in JLabel Object
        questions = new ArrayList<>(); 
        questions.add("Who is the lead singer of the band Nirvana?");
        questions.add("<html>In the movie \"Lord of the Rings\" what's the <br>last name of the Hobbit Frodo?</html>");
        questions.add("<html>In the cartoon \"Scooby Doo\" what's the name <br>of Scooby's nephew?</html>");
        questions.add("What is Homer Simpson's middle name?" );
        questions.add("<html>What's the name of the dog that stars in the <br>movie \"The Wizard of Oz\"?</html>");
        questions.add("Which actor played Superman and died in 2004?");
        questions.add("Who played Jack in the movie \"Titanic\"?");
        questions.add("<html>In which country did \"The Sound of Music\" <br>take place?</html>");
        questions.add("Who plays Ron Burgundy in the movie \"Anchorman\"?");
        questions.add("<html>Who is Bart Simpsons best friend at <br>Springfield Elementary School?</html>");        
    }
    
    public ArrayList<String> getQuestions(){
        return questions;
    }
    
    public String getQuestion(int questionNumber){
        return questions.get(questionNumber-1);   
    }
    
    public void setPossibleAnswers(){
        possibleAnswers = new ArrayList<>();
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Homer Simpson", "Kurt Cobain", "Michael Jackson", "Jimi Hendrix")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Gamgee", "Baggins", "Gandalf", "Took")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Yabba Doo", "Scooby Dee ", "Scrappy Doo", "Skippy Do ")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("John", "Jay", "Jack", "Javier"))); 
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Wolf", "Doggy", "Scooby", "Toto")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Christopher Reeve", "John Newton", "Gerard Christopher", "Dean Cain")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Lenonardo da Vinci", "Leonardo DiCaprio", "Tom Hanks", "Jim Carrey")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("New Zealand", "Cambodia", "Austria", "Egypt")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Will Ferrell", "Paul Burgundy", "Steve Carrell", "Paul Rudd")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Millhouse", "Milton", "Barney", "Mr Burns")));
    }
    
    public ArrayList<String> getPossibleAnswer(int questionNumber){
        return possibleAnswers.get(questionNumber-1);
    }
    
    public void setAnswers(){
        answers = new ArrayList<>();
        answers.add("Kurt Cobain");
        answers.add("Baggins");
        answers.add("Scrappy Doo");
        answers.add("Jay");
        answers.add("Toto");
        answers.add("Christopher Reeve");
        answers.add("Leonardo DiCaprio");
        answers.add("Austria");
        answers.add("Will Ferrell");
        answers.add("Millhouse");
    }
    
    public String getAnswer(int questionNumber){
        return answers.get(questionNumber-1);     
    }
}
