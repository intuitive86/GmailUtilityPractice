package keypress;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBoardActions {
/*
    public static int VK_ALT;
    
    @Test
    public void keyPressed(KeyEvent evt) {
        evt = new KeyEvent();
        System.out.println(KeyEvent.VK_ALT);

    }*/

    public static void main(String[]args)
    {
        //Combination Alt+F4 is a window close order in windows operating system
        //How to implement combination of Alt and F4 in using Robot class

        try
        {
            Robot a=new Robot();
            a.keyPress(KeyEvent.VK_ALT);
            a.mouseMove();
            a.keyRelease(KeyEvent.VK_ALT);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
