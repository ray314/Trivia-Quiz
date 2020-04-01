/*
Description: Paints am image onto Object that is an instance of a JPanel
Date: 23/4/19
Author: James Eason
 */

package com.example.quiz.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Images extends JPanel {
    private Image backgroundImage = null;
    private Image button = null;
    private Image quiz = null;
    
    public Images(String image){    //determine which image to paint
        switch (image) {
            case "paper":
                backgroundImage = new ImageIcon("paper.jpg").getImage();
                break;
            case "quiz":
                quiz = new ImageIcon("quiz.jpg").getImage();
                break;    
            case "readme":
                backgroundImage = new ImageIcon("readme.jpg").getImage();        
                break;
            case "goldplaque":
                backgroundImage = new ImageIcon("goldplaque.jpg").getImage();
                break;
            case "greybutton":
                button = new ImageIcon("greybutton.jpg").getImage();
                break;
            case "redbutton":
                button = new ImageIcon("redbutton.jpg").getImage();
                break;
            case "greenbutton":
                button = new ImageIcon("greenbutton.jpg").getImage();
                break;
            case "orangebutton":
                button = new ImageIcon("orangebutton.jpg").getImage();
                break; 
            case "shield":
                backgroundImage = new ImageIcon("shield.png").getImage();
                break;
            case "silverplaque":
                backgroundImage = new ImageIcon("silverplaque.jpg").getImage();
                break;
            case "quizlogo":
                backgroundImage = new ImageIcon("quizlogo.png").getImage();
                break;
            default:
                break;
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if (backgroundImage!=null || quiz!=null){
            if (quiz!=null){    //if true setClip to RoundRectangle2D
                Graphics2D g2 = (Graphics2D) g; 
                g2.setStroke(new BasicStroke(2));                               
                g.setColor(Color.LIGHT_GRAY);                                   
                g2.setClip(new RoundRectangle2D.Double(0, 0, 826, 431, 50, 50));//x, y, width, height, arcWidth, arcHeight
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawImage(quiz, 0, 0, null);
                g2.setStroke(new BasicStroke(1));                               //width = 1
                g2.setColor(Color.LIGHT_GRAY);                                  //colour = light gray
                g2.draw(new RoundRectangle2D.Double(0, 0, 826, 431, 50, 50));   //paint a rectangle with rounded edges around "quiz.jpg"
            } else {
               g.drawImage(backgroundImage, 0, 0, null); 
            }
        } else{ //setClip to Ellipse2D
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D e = new Ellipse2D.Float(0, 0, 75, 75);                        //x, y, width, height
        g2.setClip(e);                                                          
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(button, 0, 0, null);                                       //paint image it the shape of a circle (ellipse)
        
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.LIGHT_GRAY);
        g2.drawOval(0,0,75,75);                                                 //paint a border around the button
        }
    }
}
