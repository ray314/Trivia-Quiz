/*
Description: Contains Sport qestions, answers and possible answers that are written to database if SPOQUESTIONS table does not exist
Date: 1/4/19
Author: James Eason
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;

public class SportQuestions implements Questions {
    ArrayList<String> questions;
    ArrayList<String> answers;
    ArrayList<ArrayList<String>> possibleAnswers;
    
    public SportQuestions(){
        setQuestions();
        setAnswers();
        setPossibleAnswers();
    }
    
    public void setQuestions(){ //<br> starts a new line in JLabel Object
        questions = new ArrayList<>();
        questions.add("<html>Which New Zealand Cricket batter has the <br>highest One Day batting average?</html>");
        questions.add("<html>What was the first year New Zealand won the <br>Americas Cup?</html>");
        questions.add("<html>What is the name of the New Zealand <br>Mens National Basketball Team?</html>");
        questions.add("<html>What is the name of the New Zealand <br>Mens National Rugby Team?</html>");
        questions.add("<html>Which New Zealand Cricket bowler has the <br>most Test Match Wickets?</html>");
        questions.add("<html>What is Golfer Lydia Ko's best place at the <br>U.S. Womens Open?</html>");
        questions.add("<html>Which race did Oracle Team USA win in the <br>2017 Americas Cup?</html>");
        questions.add("<html>Which New Zealander won gold at the <br>2016 Olympics for canoeing?</html>");
        questions.add("<html>Which New Zealand Cricket player has the <br>most Twenty20 catches?</html>");
        questions.add("<html>Who is the third highest paid sportsperson <br>in New Zealand?</html>");        
    }
    
    public String getQuestion(int questionNumber){
        return questions.get(questionNumber-1);
    }
    
    public void setPossibleAnswers(){
        possibleAnswers = new ArrayList<>();
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Henry Nicholls", "Tom Latham", "Ross Taylor", "Jimmy Neesham")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("2000", "1995", "2017", "They have never won")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Chicago Bulls", "Black Caps", "Tall Blacks", "Black Sticks")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("All Whites", "All Blacks", "Vodafone Warriors", "Black Sticks"))); 
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Neil Wagner", "Dion Nash", "Richard Hadlee", "Daniel Vettori")));
        possibleAnswers.add( new ArrayList<>(Arrays.asList("1st", "3rd", "Tied 3rd", "10th")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("None", "Race 1", "Race 6", "Race 8")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Hamid Bond", "Eric Murray", "Peter Burling", "Lisa Carrington")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Kyle Mills", "Shane Bond", "Ross Taylor", "Martin Guptill")));
        possibleAnswers.add(new ArrayList<>(Arrays.asList("Russell Coutts", "Scott Dixon", "Lydia Ko", "Brendon McCullum")));
    }
    
    public ArrayList<String> getPossibleAnswer(int questionNumber){
        return possibleAnswers.get(questionNumber-1);
    }
    
    public void setAnswers(){
        answers = new ArrayList<>();
        answers.add("Ross Taylor");
        answers.add("1995");
        answers.add("Tall Blacks");
        answers.add("All Blacks");
        answers.add("Richard Hadlee");
        answers.add("Tied 3rd");
        answers.add("Race 6");
        answers.add("Lisa Carrington");
        answers.add("Ross Taylor");
        answers.add("Scott Dixon");
    }
    
    public String getAnswer(int questionNumber){
        return answers.get(questionNumber-1);     
    }
}
