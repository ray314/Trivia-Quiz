/*
Description:    Contains a JFrame which adds/remove components creating a GUI 
                Displays a main menu
                Creates a Player object
                Reads questions, possible answers, correct answers and high scores from TriviaDatabase
                Updates playerScore (in Player class) when the user gets questions right/wrong
                Displays notifies user if they have set a new highScore
                Prompts the user if they would like to play  again
Date:   25/4/19
Author: James Eason
 */
package view;

import controller.Controller;
import controller.UpdateInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class View extends JFrame implements Observer{
    private Images background, greyButton, orangeButton, redButton, greenButton, quizLogo, rotatedQuizLogo;
    private JPanel northPanel, southPanel, eastPanel, westPanel, centerPanel, centerBottom;
    private JButton mainMenu, beginGame;
    private JLabel invalidEntry, correctIncorrect, triviaQuestion;
    private TextAttributes textAttributes;
    private PlayerDetails playerDetails;
    private MainMenu main;
    private AskQuestion askQuestion;
    private Welcome welcome;

    public View(){                                
        greenButton = new Images("greenbutton");                                //initialize JButtons so Controller can add MouseListeners when its constructor is called
        redButton = new Images ("redbutton");
        orangeButton = new Images("orangebutton");
        greyButton = new Images("greybutton");
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();                          
        Dimension screenDimensions = toolkit.getScreenSize();
        int screenWidth = screenDimensions.width;
        int screenHeight = screenDimensions.height;

        setLocation(screenWidth / 4, screenHeight / 4);
        setSize(860, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trivia");
        setResizable(false);                                                    //prevent user from adjusting JFrame size  

        //lower level View classes
        textAttributes = new TextAttributes();                                  
        playerDetails = new PlayerDetails();
        main = new MainMenu();
        welcome = new Welcome();
        
        northPanel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel = new JPanel();
        centerPanel = new JPanel();

        background = new Images("paper");                                       //JPanel painted with paper Image                                          
        background.setOpaque(false);
        background.setLayout(new BorderLayout()); 
        background.setSize(new Dimension(854, 556));
        
        beginGame = new JButtonGradient("Begin Game", 200, 90);                 //JButton text, width, height 
        mainMenu = new JButtonGradient("Continue", 200, 90);
        mainMenu.setFocusable(false);                                           //Remove JButton default selection

        northPanel.setOpaque(false);                                            //make JPanels translucent so user can see background image
        southPanel.setOpaque(false);
        eastPanel.setOpaque(false);
        westPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        
        welcome.setCenterPanel(centerPanel);
        welcome.setNorthPanel(northPanel);
        welcome.setSouthPanel(southPanel, mainMenu);
        centerPanel = welcome.getCenterPanel(); 
        northPanel = welcome.getNorthPanel(); 
        southPanel = welcome.getSouthPanel();
       
        background.add(northPanel, BorderLayout.NORTH);                         //add foreground JPanel (northPanel) to background JPanel
        background.add(southPanel, BorderLayout.SOUTH);
        background.add(eastPanel, BorderLayout.EAST);
        background.add(westPanel, BorderLayout.WEST);
        background.add(centerPanel, BorderLayout.CENTER);
        add(background);                                                        //add background JPanel (containing foreground JPanels) to JFrame                                                 
    }
    
    public void setActionListener(Controller controller){                       //Adds ActionListener instance from Controller class to JButtons
        beginGame.addActionListener(controller);                          
        main.setActionListener(controller); 
        mainMenu.addActionListener(controller); 
    }
    
    public void setMouseListener(Controller controller){                        //Adds MouseListener instance from Controller class to JButtons
        greenButton.addMouseListener(controller);                             
        redButton.addMouseListener(controller);    
        orangeButton.addMouseListener(controller);    
        greyButton.addMouseListener(controller);    
    }
    
    public void setKeyListener(Controller controller){
        playerDetails.setKeyListener(controller);                               //set Key Listener in PlayerDetails class
    }
    
    @Override
    public void update(Observable o, Object arg) {
        UpdateInfo updateInfo = (UpdateInfo) arg;
        int selectedFlag = updateInfo.getSelectedFlag();                        //flags are fields in UpdateInfoclass
        updateInfo.setFlag(selectedFlag, false);                                //sets selected flag back to false (i.e. not selected)
       
        switch (selectedFlag){                                                  //flags indicate which method to be called in View class
            case 0:                                                             //changeButton flag
                setButtonColor(updateInfo.getButtonColor());
                break;
            case 1:                                                             //timeLimitExpired flag
                timeLimitExpired(updateInfo.getCorrectAnswer());
                break;
            case 2:                                                             //askQuestion flag
                askQuestion(updateInfo.getQuestionString(), updateInfo.getPossibleAnswers()); 
                break;
            case 3:                                                             //checkAnswer flag
                displayAnswer(updateInfo.getAnswerCorrect(), updateInfo.getCorrectAnswer()); 
                break;
            default:
                updateLessCritical(updateInfo, selectedFlag);
                break;     
        }  
    }
    
    public void updateLessCritical(UpdateInfo updateInfo, int selectedFlag){    //invokes methods that are less time critical than update(...) method above
        switch (selectedFlag){ 
            case 4:                                                             //incorrectUserDetails flag
                incorrectUserDetails();
                break;
            case 5:                                                             //showNewHighScore flag
                showNewHighScore(updateInfo.getNewHighScore());
                break;
            case 6:                                                             //viewReadMe flag
                readMe(updateInfo.getInstructions());
                break;
            case 7:                                                             //viewHighScores flag
                viewHighScores(updateInfo.getStringHighScores());
                break;
            case 8:                                                             //mainMenu flag
                mainMenu();
                break;
            default:
                break;
        }
    }
    
    public void setButtonColor(int buttonColor){                                //changes the Button image to a different colour every 1200ms                                       
        southPanel.removeAll();
        southPanel.setBorder(new EmptyBorder(0, 389, 10, 200));                 
        
        if (buttonColor == 1){                                                  //buttonColor 1 = orangeButton                     
            orangeButton.setOpaque(false);
            southPanel.add(orangeButton);
        } else {                                    
            greyButton.setOpaque(false);
            southPanel.add(greyButton);                                         //buttonColor 2 = greyButton
        }

        southPanel.add(Box.createRigidArea(new Dimension(50, 75)));
        southPanel.revalidate();
        southPanel.repaint();
    }
    
    public void setRotatedQuizLogo(){
        background.removeAll();

        rotatedQuizLogo = new Images("rotated");                                                   
        rotatedQuizLogo.setOpaque(false);
        rotatedQuizLogo.setLayout(new BorderLayout());
        rotatedQuizLogo.add(northPanel, BorderLayout.NORTH);                                                
        rotatedQuizLogo.add(southPanel, BorderLayout.SOUTH);
        rotatedQuizLogo.add(eastPanel, BorderLayout.EAST);
        rotatedQuizLogo.add(westPanel, BorderLayout.WEST);
        rotatedQuizLogo.add(centerPanel, BorderLayout.CENTER);

        background.add(rotatedQuizLogo);
    }
    
    public void removeQuizLogo(){
        background.removeAll();                                                 //remove all JPanels then re-add them excluding quizLogo
        quizLogo.removeAll();
        
        background.add(northPanel, BorderLayout.NORTH);                         
        background.add(southPanel, BorderLayout.SOUTH);
        background.add(eastPanel, BorderLayout.EAST);
        background.add(westPanel, BorderLayout.WEST);
        background.add(centerPanel, BorderLayout.CENTER);
    }
     
    public int getPlayerAnswer(){                                               //returs int representing players selected answer
        return askQuestion.getPlayerAnswer();
    }
    
    public int getQuestionCategory(){
        return playerDetails.getQuestionCategory();                             //return int representing Question type: 1=Entertainment, 2=Sport, 3=Geography, 4=Science
    }
    
    public String getUserNameField(){
        return playerDetails.getUserNameField();
    }
    
    public int getUserNameLength(){
        return playerDetails.getUserNameLength();
    }
    
    public void setUserNameLength(){
        playerDetails.setUserNameLength();                                      //invokes method in PlayerDetails class than limits a user name to 20 characters
    }
    
    public void mainMenu() {                                                    //allows user to: exit, view instructions, view highscore, play game
        main.setNorthPanel(northPanel);
        northPanel = main.getNorthPanel();
        invalidEntry = new JLabel();
        background.removeAll();     
        
        quizLogo = new Images("quizlogo");                                      //JPanel painted with quizLogo (quiz logo Image)
        quizLogo.setOpaque(false);
        quizLogo.setLayout(new BorderLayout());
        
        quizLogo.add(northPanel, BorderLayout.NORTH);                           //add JPanel to quizLogo                       
        quizLogo.add(southPanel, BorderLayout.SOUTH);
        quizLogo.add(eastPanel, BorderLayout.EAST);
        quizLogo.add(westPanel, BorderLayout.WEST);
        quizLogo.add(centerPanel, BorderLayout.CENTER);
        background.add(quizLogo);                                               //layer quizoLogo over the top of background
        
        main.setAskQuestion(centerPanel);                                       //MainMenu (main) class adds components to centerPanel (JPanel)
        centerPanel = main.getAskQuestion();                                    //get centerPanel back from MainMenu class      
        
        main.setSouthPanel(southPanel);
        southPanel = main.getSouthPanel();
    }
    
    public void readMe(String[] readMe){                                        //displays instructions and game info. on a JTextPane
        removeQuizLogo();
        
        ReadMe readMeObject = new ReadMe();                                     
        readMeObject.setNorthPanel(northPanel);
        northPanel = readMeObject.getNorthPanel();
        
        readMeObject.setReadMe(centerPanel, readMe, textAttributes);            //ReadMe class adds components to centerPanel (JPanel)
        centerPanel = readMeObject.getReadMe();                                 //get centerPanel back from ViewHighScores class
 
        southPanel.add(mainMenu);
    }

    public void viewHighScores(ArrayList<String> highScores) {
        southPanel.add(mainMenu);
        background.removeAll();
        setRotatedQuizLogo();
        
        ViewHighScores viewScores = new ViewHighScores();                       
        viewScores.setNorthPanel(northPanel);
        northPanel = viewScores.getNorthPanel();
        
        viewScores.setViewScores(centerPanel, highScores, textAttributes);      //ViewHighScores class adds components to centerPanel (JPanel)
        centerPanel = viewScores.getViewScores();                               //get centerPanel back from ViewHighScores class
    }
    
    public void getPlayerDetails() {                                          
        beginGame.setFont(new Font("calibri", Font.PLAIN, 30));                    
        beginGame.setFocusable(false);
        
        playerDetails.setNorthPanel(northPanel);
        northPanel = playerDetails.getNorthPanel();
        
        playerDetails.setPlayerDetails(centerPanel);                            //PlayerDetails class adds components to centerPanel (JPanel)
        centerPanel = playerDetails.getPlayerDetails();                         //get centerPanel back from PlayerDetails class 
       
        BoxLayout southPanelLayout = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        southPanel.setLayout(southPanelLayout);
        southPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        southPanel.setBorder(new EmptyBorder(0, 336, 6, 0));                    //top, left, bottom, right             
        beginGame.setPreferredSize(new Dimension(200, 47));
        southPanel.add(beginGame);
        southPanel.add(Box.createRigidArea(new Dimension(20, 20)));             
    }
    
    public void incorrectUserDetails() {                                        //if user enters nothing as their name JLabel invalidEntry displays "Invalid name entry"
        beginGame.setFocusable(false);                                          //unselect JButton BeginGame once it's been clicked (mousePressed)
        if (invalidEntry.getText() != "Invalid name entry") {                   //if JLabel doesn't display "Invalid name entry"
            invalidEntry.setText("Invalid name entry");                         //set text "Invalid name entry"
            invalidEntry.setFont(new Font("calibri", Font.PLAIN, 30));
            southPanel.add(invalidEntry);                                       //add JLabel to southPanel                 
            southPanel.revalidate();
        }
    }
    
    public void askQuestion(String questionString, ArrayList<String> possibleAnswers) {
        welcome.setNorthPanel(northPanel);
        northPanel = welcome.getNorthPanel();
        
        triviaQuestion = new JLabel();
        centerBottom = new JPanel();
        
        askQuestion = new AskQuestion();
        askQuestion.setQuestion(possibleAnswers, questionString);               //pass questionString and possibleAnswers to AskQuestion class
        askQuestion.setAskQuestion(centerPanel, triviaQuestion, centerBottom);  //AskQuestion class adds components to centerPanel and centerBottom (JPanels)
        centerPanel = askQuestion.getAskQuestion();                             //get centerPanel back from AskQuestion class
        centerBottom = askQuestion.getCenterBottomPanel();                      //get centerBottom back from AskQuestion class
        
        greyButton.setOpaque(false);
        
        southPanel.removeAll();
        southPanel.setBorder(new EmptyBorder(0, 389, 10, 200));                 
        southPanel.add(greyButton);
        southPanel.add(Box.createRigidArea(new Dimension(50, 75)));

        southPanel.repaint();
    }

    public void displayAnswer(boolean answerCorrect, String correctAnswer) {    //answerCorrect = true if use has got the answer correct
        DisplayAnswer displayAnswer= new DisplayAnswer();
        displayAnswer.setComponents(greenButton, redButton, centerBottom);
        displayAnswer.setAskQuestion(southPanel, answerCorrect, correctAnswer);
        
        JPanel[] panels = displayAnswer.getPanels();                            //retrieves modified JPanels from DisplayAnswer class
        southPanel = panels[0];
        centerBottom = panels[1];
        
        centerPanel.revalidate();
        centerPanel.repaint();
    }
     
    public void timeLimitExpired(String correctAnswer){ //called if player hasn't answered question within approx. 12 seconds
        triviaQuestion.setFont(new Font("calibri", Font.BOLD, 30));

        TimeExpired expired = new TimeExpired();
        expired.setComponents(correctIncorrect, correctAnswer);
        expired.setTimeExpired(southPanel, redButton, centerBottom);
        southPanel = expired.getTimeExpired();
            
        centerPanel.revalidate();
        centerPanel.repaint();       
    }

    public void showNewHighScore(String[] score) {                              //Array String contains rank, playerName, score
        removeQuizLogo();
        southPanel.removeAll();
        
        BoxLayout southPanelLayout = new BoxLayout(southPanel, BoxLayout.Y_AXIS);
        southPanel.setLayout(southPanelLayout);
        southPanel.setBorder(new EmptyBorder(2, 355, 8, 349));                  //(top, left, bottom, right)
        southPanel.add(mainMenu);

        TextAttributes attributes = new TextAttributes();
        
        NewHighScore newScore = new NewHighScore();
        newScore.setNorthPanel(northPanel);
        northPanel = newScore.getNorthPanel();
        
        newScore.setNewScore(centerPanel, score, attributes);                   //ShowNewHighScore class adds components to centerPanel (JPanel)                   
        
        centerPanel.repaint();
        southPanel.revalidate();
        southPanel.repaint();
    }
}
