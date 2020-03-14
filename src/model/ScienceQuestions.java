/*
Description: Contains Science qestions, answers and possible answers that are written to database if SCIQUESTIONS table does not exist
Date: 1/4/19
Author: James Eason
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;

public class ScienceQuestions  implements Questions{
ArrayList<String> questions;
    ArrayList<String> answers;
    ArrayList<ArrayList<String>> possibleAnswers;
    
    public ScienceQuestions(){
        setQuestions();
        setAnswers();
        setPossibleAnswers();
    }
    
    public void setQuestions(){ //<br> starts a new line in JLabel Object
        questions = new ArrayList<>();
        questions.add("<html>What is the most abundant gas in the <br>Earth's atmosphere?</html>");
        questions.add("What is the chemical symbol for Potassium?");
        questions.add("Diamonds are mostly composed of which element?");
        questions.add("Who developed the Theory of Relativity?" );
        questions.add("What does the Beaufort scale measure?");
        questions.add("What is the outer layer of the Earth called?");
        questions.add("What is the chemical symbol for Silver?");
        questions.add("What is the hottest planet in our Solar System?");
        questions.add("What does a Manometer measure?");
        questions.add("Who invented the Light Bulb?");        
    }
    
    public String getQuestion(int questionNumber){
        return questions.get(questionNumber-1);
    }
    
    public void setPossibleAnswers(){
        possibleAnswers = new ArrayList<>();
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Helium", "Nitrogen", "Oxygen", "Carbon Dioxide")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("K", "P", "Na", "Pt")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Diamond", "Sapphire", "Oxygen", "Carbon")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Ernest Rutherford", "Albert Einstein", "Isaac Newton", "Thomas Edison"))); 
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Height", "Weight", "Wind", "Temperature")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Sea", "Land", "Toast", "Crust")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Ag", "Sr", "Sm", "Se")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Venus", "Jupiter", "Satturn", "Mercury")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Weight", "Temperature", "Mass", "Pressure")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Albert Einstein", "Thomas Edison", "Isaac Newton", "Stephen Hawking")));
    }
    
    public ArrayList<String> getPossibleAnswer(int questionNumber){
        return possibleAnswers.get(questionNumber-1);
    }
    
    public void setAnswers(){
        answers = new ArrayList<>();
        answers.add("Nitrogen");
        answers.add("K");
        answers.add("Carbon");
        answers.add("Albert Einstein");
        answers.add("Wind");
        answers.add("Crust");
        answers.add("Ag");
        answers.add("Venus");
        answers.add("Pressure");
        answers.add("Thomas Edison");
    }
    
    public String getAnswer(int questionNumber){
        return answers.get(questionNumber-1);     
    }
}