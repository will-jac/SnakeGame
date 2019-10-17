import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Game extends JPanel implements ActionListener {
    
    
    class Keys extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) || (key == KeyEvent.VK_A)) {
                direction = Direction.WEST;
            } else if ((key == KeyEvent.VK_RIGHT) || (key == KeyEvent.VK_D)) {
                direction = Direction.EAST;
            } else if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_W)) {
                direction = Direction.NORTH;
            } else if ((key == KeyEvent.VK_DOWN) || (key == KeyEvent.VK_S)) {
                direction = Direction.SOUTH;
            }
            if (key == KeyEvent.VK_ENTER) {
                gameOver = false;
                Driver.main(null);
            }
        }
    }
    
    private static final long serialVersionUID = 1L;

    final private static int BOARDWIDTH = 987;
    final private static int BOARDHEIGHT = 987;
    final private static int PIXELSIZE = 50;
    final private static int GAP_SIZE = 5;
    
    private Board board;
    private Timer timer;
    private Direction direction;
    
    private boolean gameOver;
    
    public Game(int width, int height, int speed) {
        gameOver = false;
        
        addKeyListener(new Keys());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
        
        board = new Board(width, height);
        direction = Direction.EAST;
        timer = new Timer(speed, this);
        timer.start();
    }
    
    // Run constantly as long as we're in game.
    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            board.update(direction);
        }
        catch (GameOverException e1)
        {
            gameOver = true;
        }
        // Repaint or 'render' our screen
        repaint();
    }
    
    void endGame(Graphics g) {
        
        timer.stop();


        // Create a message telling the player the game is over
        String message1 = "GAME OVER";
        String message2 = "You died with a length of " + board.getSnake().size();
        String message3 = "Press Enter to restart";

        // Create a new font instance
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        FontMetrics metrics = getFontMetrics(font);

        // Set the color of the text to white, and set the font
        g.setColor(Color.white);
        g.setFont(font);

        // Draw the message to the board
        g.drawString(message1, (BOARDWIDTH - metrics.stringWidth(message1)) / 2,
                BOARDHEIGHT / 2 - metrics.getHeight());
        g.drawString(message2, (BOARDWIDTH - metrics.stringWidth(message2)) / 2,
                BOARDHEIGHT / 2);
        g.drawString(message3, (BOARDWIDTH - metrics.stringWidth(message3)) / 2,
                BOARDHEIGHT / 2 + metrics.getHeight());
        
        
        System.out.println("Game Ended");
        

    }
    
    // Used to paint components to the screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }
    
    // Draw our Snake & Food (Called on repaint()).
    void draw(Graphics g) {
        //Only draw if the game is running / the snake is alive
        if (gameOver)
           endGame(g);
        
        for (int x = 0; x <= board.width; ++x) {
            for (int y = 0; y <= board.height; ++y) {
                switch (board.board[x][y]) {
                    case border :   g.setColor(Color.blue);
                                    g.fillRect(x * (PIXELSIZE + GAP_SIZE), y * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
                                    break;
                    case snake  :   g.setColor(Color.GREEN);
                                    g.fillRect(x * (PIXELSIZE + GAP_SIZE), y * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
                                    break;
                    case food   :   g.setColor(Color.red);
                                    g.fillRect(x * (PIXELSIZE + GAP_SIZE), y * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
                                    break;
                    case empty  :   break;
                }
            }
        }
        /*
        g.setColor(Color.blue);
        for (int i = 0; i <= game.width; ++i) {
            g.fillRect(i * (PIXELSIZE + GAP_SIZE), (game.width) * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
            g.fillRect(i * (PIXELSIZE + GAP_SIZE), 0, PIXELSIZE, PIXELSIZE);
        }
        for (int i = 0; i <= game.height; ++i) {
            g.fillRect((game.height) * (PIXELSIZE + GAP_SIZE), i * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
            g.fillRect(0, i * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
        }
        
        g.setColor(Color.red);
        Point f = game.getFood();
        g.fillRect(f.x * (PIXELSIZE + GAP_SIZE), f.y * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE); // food
        
        g.setColor(Color.GREEN);
        // Draw our snake.
        String snakeLoc = "";
        for (Point s : game.getSnake()) {
            g.fillRect(s.x * (PIXELSIZE + GAP_SIZE), s.y * (PIXELSIZE + GAP_SIZE), PIXELSIZE, PIXELSIZE);
            snakeLoc += "(" + s.x + "," + s.y + ")  ";
        }
        System.out.println(snakeLoc);
        */
        // Sync our graphics together
        Toolkit.getDefaultToolkit().sync();
    }
    void restart() {
        Driver.frame.dispatchEvent(new WindowEvent(Driver.frame, WindowEvent.WINDOW_CLOSING));
        Driver.main(null);
    }
}
