/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author elsan
 */
/*
Saya [Elsa Nabiilah] [2108805] mengerjakan Tugas Masa Depan dalam mata kuliah Desain Pemrograman Berorientasi Objek 
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
*/
public class User extends GameObject{
    GameOption op;
    private int standing;
    private int skor;
    private int simpen;
    
    private float podongspeed = 3.0f;
    private float udara = 0;
    private float gravity = 0.1f;
    private float speed = 0;
    
    private boolean kiri;
    private boolean atas;
    private boolean kanan;
    private boolean bawah;
    
    private boolean melayang = true;
    
    public User(int x, int y){
        super(x, y, 50, 50); //ukuran player 
    } 
    public void render(Graphics g){
        // background
        Image imagebc = Toolkit.getDefaultToolkit().getImage(getClass().getResource("hijau.jpg"));
        g.drawImage(imagebc, 0, 0, op.GAME_WIDTH, op.GAME_HEIGHT, null);
        //player
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("podong.png"));
        int w = 50;
        int h = 50;
        g.drawImage(image, (int) x, (int) y, w, h, null);
        //tulisan score dan standing di pojok kiri atas sesuai dengan koordinat yang telah diset dibawah 
        g.setFont(new java.awt.Font("Segoe UI", 1, 13));
        g.setColor(Color.decode("#008000"));
        g.drawString("Skor : " + Integer.toString(this.skor), 20, 20);
        g.drawString("Standing : " + Integer.toString(this.standing), 20, 50);
    }
    public void updatelayar(ArrayList<Pijakan> arraypi){ //update kondisi pijakan dan player (podong)
         //atur kecepatan
         if(speed > 2) {
            // kecepatan maks 2
            speed = 2;
        } else if(speed < -2) {
            // kecepatan min -2
            speed = -2;
        }
        if(kanan && kiri) {//jika klik arrow kanan atau kiri 
            // kecepatan player mengikuti game
            speed = op.GAME_SPEED;
        } else if(kiri) {//jika klik kiri
            speed -= podongspeed; //kecepatan ke berkurang
        } else if(kanan) { //jika klik kanan
            speed += podongspeed; //kecepatan bertambah
        } 
        if(melayang){// melayang
            udara += gravity; //kecepatan udara bertambah sesuai dengan nilai gravitas 
        } 
         if(!melayang){  //set gerak jatuh bebas player 
            melayang = true; //atur bahwa tidak berada dipijakan 
        }
        for(Pijakan pi : arraypi){
            // untuk setiap Pijakan
            if(getBatasBawah().intersects(pi.getHitbox())){
                melayang = false;
                udara = 0; // kecepatan udara set 0 
               y = pi.getHitbox().y - height; //berpijak pada pijakan 
                 if(pi.getTandaPijak() == 0){  //tanda bahwa pijakan belum terhitung (belum di set 0)=  pertama kali di pijak 
                     standing++; //skor standing (setiap player hit pijakan)
                     simpen += pi.getPoint(); //get nilai bobot per pijakan dan saling tambahkan untuk mendapatkan nilai score
                     skor = simpen * 10; //kali 10 karena nilai bobot nilai satuan (1,2,3..) 
                     pi.setTandaPijak(1); //tandai bahwa pijakan sudah dipijak 
                 }
            }
            if(getBatasKanan().intersects(pi.getHitbox())){
                // jika nabrak kanan
                speed = op.GAME_SPEED;
                x = pi.getHitbox().x - width - 1;
            }
            if(getBatasKiri().intersects(pi.getHitbox())){
                // jika nabrak kiri
                speed = op.GAME_SPEED;
                x = pi.getHitbox().x + pi.getHitbox().width + 1;
            }
            if(getBatasKiri().x < 0){
                // jika nabrak sampai mentok 
                x = 0;
            }
        }
        x += speed;
        y += udara;
    }
    public void update(ArrayList<Pijakan> ob){
        updatelayar(ob);
        updateHitbox();
        
    }
   //  @Override
      public boolean lantai(ArrayList<Pijakan> arraypi){
        for(Pijakan pi : arraypi){
            if(getBatasBawah().intersects(pi.getHitbox()))
            {
                return true;
            }
        }
        return false;
    }
    public void setKiri(boolean kiri) {
        this.kiri = kiri;
    }
    public void setAtas(boolean atas) {
        this.atas = atas;
    }

    public void setKanan(boolean kanan) {
        this.kanan = kanan;
    }
     public void setBawah(boolean bawah) {
        this.bawah = bawah;
    }
    public void setSkor(int skor) {
        this.skor = skor;
    }
    public void setStanding(int standing) {
        this.standing = standing;
    }
    
    public int getSkor() {
        return this.skor;
    }
    
    public int getStanding() {
        return this.standing;
    }
    public Rectangle getBatasBawah(){
        // membuat batas bawah
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2);
    }
    
    public Rectangle getBatasAtas(){
        // membuat batas atas
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y), width/2, height/2);
    }
    
    public Rectangle getBatasKanan(){
        // membuat batas kanan
        return new Rectangle((int) x+width-5, (int)y + 5, 5, height-10);
    }
    
    public Rectangle getBatasKiri(){
        // membuat batas kiri
        return new Rectangle((int) x, (int)y + 5, 5, height-10);
    }
}
