/*
Description: Contains Geography qestions, answers and possible answers that are written to database if GEOQUESTIONS table does not exist
Date: 1/4/19
Author: James Eason
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;

public class GeographyQuestions implements Questions{
ArrayList<String> questions;
    ArrayList<String> answers;
    ArrayList<ArrayList<String>> possibleAnswers;
    
    public GeographyQuestions(){
        setQuestions();
        setAnswers();
        setPossibleAnswers();
    }
    
    public void setQuestions(){ //<br> starts a new line in JLabel Object
        questions = new ArrayList<>();
        questions.add("Which ocean lies on the east coast of the United States?");
        questions.add("Which is the Worlds highest mountain?");
        questions.add("What is the biggest desert in the world?");
        questions.add("What specifies a distance North or South of the Equator?" );
        questions.add("<html>What is the imaginary line that connects the <br>North and South Pole?</html>");
        questions.add("What U.S. state is home to the Grand Canyon?");
        questions.add("What is the Worlds longest river?");
        questions.add("What is the largest ocean on Earth?");
        questions.add("What is the size of the Earths equator?");
        questions.add("What is the Earths largest continent?");        
    }
    
    public String getQuestion(int questionNumber){
        return questions.get(questionNumber-1);
    }
    
    public void setPossibleAnswers(){
        possibleAnswers = new ArrayList<>();
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Pacific", "Eastern", "Atlantic", "Indian")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Mount Cook", "The Southern Alps", "Kilimanjaro", "Mount Everest")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Arabian", "Great Australian", "Sahara", "Namib")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Bearing", "Heading", "Latitude", "Longitude"))); 
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Prime Meridian", "Earth Axis","Prime Axis", "Magnetic Field")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Arizona", "Colorado", "Nevada", "Washington")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Yellow River", "Amazon River", "Nile River", "Waikato River")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Atlantic Ocean", "Pacific Ocean", "Indian Ocean", "Southern Ocean")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("40,000km", "80,000km", "60,000km", "61,000km")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Africa", "Europe", "Asia", "Antractica")));
    }
    
    public ArrayList<String> getPossibleAnswer(int questionNumber){
        return possibleAnswers.get(questionNumber-1);
    }
    
    public void setAnswers(){
        answers = new ArrayList<>();
        answers.add("Atlantic");
        answers.add("Mount Everest");
        answers.add("Sahara");
        answers.add("Latitude");
        answers.add("Prime Meridian");
        answers.add("Arizona");
        answers.add("Nile River");
        answers.add("Pacific Ocean");
        answers.add("40,000km");
        answers.add("Asia");
    }
    
    public String getAnswer(int questionNumber){
        return answers.get(questionNumber-1);     
    }
}
