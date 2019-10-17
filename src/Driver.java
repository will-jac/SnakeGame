import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Driver extends JFrame {

    static JFrame frame;
    
    private static final long serialVersionUID = 1L;

    Component game;
    
    Driver() {
        game = new Game(17, 17, 100);
        add(game);
        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        // Creates a new thread so our GUI can process itself
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new Driver();
                frame.setVisible(true);
                
            }
        });
    }
}

