/*
Description: Instanties the View, Model and Controller classes which starts the game
             **TestPackages contains JUnit testing classe**
Date: 19/5/19
Author: James Eason
 */

package controller;

import model.Model;
import view.View;

public class RunQuiz {
    public static void main(String[] args){
        View view = new View();                                                 //initializes JFrame and other components
        Model model = new Model();                                              //creates Model for storing/updating data
        //Controller controller = new Controller(view, model);                    //creates Controller and adds Listeners to Objects in View class
        model.addObserver(view);                                                //can call update method in View class by notifying Observer/s
        view.setVisible(true);
    }
}
