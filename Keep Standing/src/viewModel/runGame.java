/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import javax.swing.JPanel;
import Model.User;
import java.util.Random;
import static Model.GameOption.GAME_SPEED;
import static Model.GameOption.GAME_WIDTH;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static viewModel.runGame.STATE.Game;
import view.Objects;
import static viewModel.runGame.STATE.Game;
import Model.Tabel;
import view.Menu;
/**
 *
 * @author elsan
 */
public class runGame extends JPanel implements Runnable {
    private Thread gametr;
    private boolean run = false; // tanda game sedang run
    private int i = 0;
    private final User user; // player
    private final setPijakan pijakan; // obstacle
    private String username; // username
    private int skor = 0; // skor adapt
    private int standing =0; // skor fall
    private Objects window;
   
    public runGame(){
        Random rand = new Random();
        int playerPos = rand.nextInt(1000 - 800) + 800;
        this.user = new User(GAME_WIDTH - playerPos, 100);
        this.pijakan = new setPijakan();
    }
    public enum STATE{
        Game,
        GameOver
    }
    public STATE gameState = STATE.Game;
    
    public synchronized void StartGame(Objects gw){ //mulai run game
        gametr = new Thread(this); // buat thread baru
        gametr.start(); //jalankan thread
        this.window = gw; // buat window
        run = true; // run ubah menjadi true tanda game sedang berjalan
    }
    public void paint(Graphics g){
        super.paint(g); // deklar parent
        user.render(g); // render player
        pijakan.renderPi(g); // me render objek obstacle
    }
     @Override
    public void run() {
        while(true){
            try {
                i++; //increment untuk tanda pijakan
                updateGame(); // update objek game
                repaint(); // update paint
                Thread.sleep(1000L/60L); // pause thread
                this.skor = user.getSkor(); // mengambil skor 
                this.standing = user.getStanding(); // mengambil skor standing
                if(this.user.getBatasKanan().y > 1000) { //player keluar dari frame
                   this.gameState = STATE.GameOver;
                }
                if(gameState == STATE.GameOver) { //jika gameover
                    saveScore(); // simpan skor
                    close(); // tutup window
                    new Menu().setVisible(true); // menampilkan menu
                    stopGame(); // stop game
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(runGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateGame(){
        if(i > 50){ //jika jarak sudah 50 (celah)
        pijakan.Add(); //maka tambah pijakan 
        i = 0; //set 0 lagi
        }
        pijakan.updatePijakan(); // mengupdate pijakan
        user.update(pijakan.getPijakan()); // mengupdate kondisi player
    }
    public synchronized void stopGame() {// menghentikan game 
        try{
            gametr.join(); //hentikan thread game
            run = false; // set tidak berjalan
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void close() {
        window.CloseWindow();
    }

    public User getUser(){ //get user
        return this.user;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
     public void setScore(int skor, int standing) {
        this.user.setSkor(skor);
        this.user.setStanding(standing);
    }
       public void saveScore() { //simpan skor saat game over  
        try {
            Tabel tbl = new Tabel(); //new method tabel
            tbl.query(this.username, this.skor, this.standing); //ambil fungsi query dan masukan atribur usn, skor dan standing
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        }
        //dialog ketika game over menampilkan username dan skor
        JOptionPane.showMessageDialog(null, "Username : " + this.username + "\nSkor : " + this.skor + "\nStanding : " + this.standing, " GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
