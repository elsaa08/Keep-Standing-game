///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package Model;
//
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
//import GameOption.GAME_SPEED;
///**
// *
// * @author elsan
// */
/*
Saya [Elsa Nabiilah] [2108805] mengerjakan Tugas Masa Depan dalam mata kuliah Desain Pemrograman Berorientasi Objek 
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
*/
public class Pijakan  extends GameObject{
     private final int point;
     private int weight;
     private int pijak = 0;
     GameOption op;
     Random rand = new Random();
     Color color;
    
    public Pijakan(float x, float y, int width, int height, int point) {
        super(x, y, width, height);
        this.point = point;
        // membuat warna Pijakan
        setwarna();
    }
    
    private void setwarna(){
        int r =  rand.nextInt(0, 255); //mengambil warna random
        color = new Color(r, 0, 255); //masukan random hanya pada warna r agar tetap selaras
    }
    public void update() {
        updatePos();
        updateHitbox();
    }
   
    public void render(Graphics g) {
        g.setColor(color); // mengubah warna
        g.fillRect((int)x, (int)y, width, height); // membuat persegi panjang
    }
     private void updatePos(){ //layar bergerak dari bawah ke atas 
        y -= op.GAME_SPEED;
    }
    public int getTandaPijak(){
        // untuk mengembalikan nilai pijak tanda pijakan sudah dipijak 
        return pijak;
    }
    public void setTandaPijak(int setpijak){
        //untuk menandakan bahwa pijakan sudah di pijak 
        pijak = setpijak;
    }
    public int getPoint() {
        // mengembalikan skor per pijakan
        return point;
    }
    public Color getColor(){
        // mengembalikan warna pijakan
        return this.color;
    }
    public float getWeight(){
        // mengembalikan nilai bobot skor per pijakan
        return weight;
    }
   
}
//
//
