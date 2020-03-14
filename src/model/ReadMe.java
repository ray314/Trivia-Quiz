/*
Description: Creates a String Array containing instructions + Database Metadata
Date: 1/4/19
Author: James Eason
 */

package model;

public class ReadMe{
    private String[] readMe;
    
    public ReadMe(InputOutput inputOutput){
        readMe = new String[11];
    }
     
    public String[] getReadMeString(){
        return readMe;
    }
    
    public void setInstructions(){
        readMe[0] = "\nCorrect answer ";
        readMe[1] = "+10";
        readMe[2] = " points to score.\nIncorrect answer ";
        readMe[3] = "-5";
        readMe[4] = " points to score.\nTime limit expired (10 seconds) ";
        readMe[5] = "-3";
        readMe[6] = " points to score.\nClick the 'start' button at the bottom of the Frame to confirm an answer.\n"
                + "Click the 'start' button to go to the next question.\n\n";
    }
    
    public void setMetaData(InputOutput inputOutput){
        String[] metaData = new String[4];
        metaData = inputOutput.getMetaData();
        readMe[7] = metaData[0];                                                //Product name
        readMe[8] = metaData[1];                                                //URL
        readMe[9] = metaData[2];                                                //Driver name
        readMe[10] = metaData[3];                                               //User name
    }
}
