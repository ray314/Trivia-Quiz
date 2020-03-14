/*
Description:  Paint JButton background and layer text and border over the top
Date:  1/7/19
Author:  James Eason      
 */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class JButtonGradient extends JButton{
    private int width;
    private int height;
    private Color borderColor;
    private Color color1;
    private Color color2;
    
    public JButtonGradient(String text, int buttonWidth, int buttonHeight){     //set button text and size
        setText(text);
        setContentAreaFilled(false);                        //true button will paint automatically, false will not
        
        width = buttonWidth;
        height = buttonHeight;
        borderColor = new Color(73,56,48);                  //brown border
        
        if (text == "Continue" || text == "Begin Game"){
            color1 = new Color(192,152,42);
            color2 = new Color(241,211,103);
        } else{
            color1 = new Color(77,255,166);
            color2 = new Color(255,89,89);
        }
        
        setMaximumSize(new Dimension(width, height));
        setFont(new Font("calibri", Font.PLAIN, 30));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create(); //cast g as Graphics2D.  g.create() creates a new Grpahics objects that is a copy of this Graphics object
        //setPaint(Paint paint) sets paint attribute
        
        g2.setPaint(new GradientPaint(new Point(0, 0), color1, new Point(width, 0), color2));
        //GradientPaint(x1, y1), Color1, (x2, y2), Color2)
        
        g2.fillRect(0, 0, width, height);
 
        super.paintComponent(g);                            //customize JButton before painting (only paints the text)  
        setBorder();
    }
    
    public void setBorder(){
        setBorder(new LineBorder(borderColor, 1));
    }
}
