/*
Description: Contains SimpleAttributeSet objects used for formatting text in View class
Date: 28/5/19
Author: James Eason
 */

package view;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TextAttributes {
    private SimpleAttributeSet headingSet, textSet, textBoldSet;
     
    public TextAttributes(){
        setHeadingSet();
        setTextBoldSet();
        setTextSet();
    }
    
    public SimpleAttributeSet getHeadingSet() {
        return headingSet;
    }
    
    public SimpleAttributeSet getTextBoldSet() {
        return textBoldSet;
    }
        
    public SimpleAttributeSet getTextSet() {
        return textSet;
    }  
    
    public void setHeadingSet(){
        headingSet = new SimpleAttributeSet();                                  //specifies font to be used for print text on JTextPane in View class
        StyleConstants.setFontFamily(headingSet, "calibri");
        StyleConstants.setFontSize(headingSet, 20);
        StyleConstants.setBold(headingSet, true);
        StyleConstants.setUnderline(headingSet, true);
    }
    
    public void setTextBoldSet(){
        textBoldSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(textBoldSet, "calibri");
        StyleConstants.setBold(textBoldSet, true);
        StyleConstants.setFontSize(textBoldSet, 15);
    }
        
    public void setTextSet(){
        textSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(textSet, "calibri");
        StyleConstants.setFontSize(textSet, 15);
        StyleConstants.setBold(textSet, false);
        StyleConstants.setUnderline(textSet, false);
    }
}
 
