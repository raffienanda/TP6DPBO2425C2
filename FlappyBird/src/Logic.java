import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360; int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    int score = 0;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean isGameOver = false;

    int pipeVelocityX = -2;

    public Logic(){
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void setView(View view){this.view = view;}

    public Player getPlayer(){return player;}

    public ArrayList<Pipe> getPipes(){ return pipes;}

    public int getScore() {return score;}

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        if(player.getPosY() + player.getHeight() >= frameHeight){
            // posisikan persis di batas bawah (opsional)
            player.setPosY(frameHeight - player.getHeight());
            gameOver();
        }

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            if (playerRect.intersects(pipeRect)) {
                gameOver();
                return;
            }

            // cek apakah pipa sudah dilewati oleh burung
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);

                // hanya tambahkan skor jika ini pipa atas (biar gak double)
                if (i % 2 == 0) {
                    score++;
                    System.out.println("Score: " + score);
                }
            }
        }
    }

    public void gameOver() {
        if (isGameOver) return;
        isGameOver = true;

        // hentikan semua timer
        if (gameLoop != null) gameLoop.stop();
        if (pipesCooldown != null) pipesCooldown.stop();

        System.out.println("Game Over!");

        // repaint layar untuk menampilkan tulisan Game Over
        if (view != null) {
            view.repaint();
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }


    public void restart(){
        score = 0;

        // reset posisi & kondisi
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        pipes.clear();

        isGameOver = false;
        pipesCooldown.start();
        gameLoop.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if(view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_R && isGameOver()){
            restart();
            return;
        }

        if(e.getKeyCode() == KeyEvent.VK_M && isGameOver()){
            // Ambil parent JFrame dari komponen View
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view);

            // Bersihkan isi frame (hapus View)
            frame.getContentPane().removeAll();

            // Buat menu baru dan tampilkan
            StartMenu menu = new StartMenu(frame);
            frame.add(menu.getMainPanel());

            // Refresh tampilan
            frame.revalidate();
            frame.repaint();
        }

        if(isGameOver) return;

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);
        }
    }
    public void keyReleased(KeyEvent e){}

}
