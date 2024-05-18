package Maingame;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import GameComponent.PanelGame;
public class Maingame extends JFrame 
{
    public Maingame()
    {
        init();
    }
    private void init()
    {
        setTitle("Plane Attack - Earth defender");
        setSize(1366,768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        PanelGame panelGame = new PanelGame();
        add(panelGame);
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowOpened (WindowEvent e)
            {
                panelGame.START();
            }    
        });
    }
    public static void main(String[] args) 
    {
        Maingame maingame = new Maingame();
        maingame.setVisible(true);
    }
    
}
