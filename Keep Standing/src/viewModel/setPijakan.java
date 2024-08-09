/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;
import static Model.GameOption.GAME_HEIGHT;
import static Model.GameOption.GAME_WIDTH;
import Model.Pijakan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 *
 * @author elsan
 */
public class setPijakan {
     private final Random rand = new Random(); 
        private final int max = 20; // jumlah maks pijakan dalam 1 window
        private int height_pi = 40; // tinggi pijakan
        private int count = 0; // jumlah obstacle
        private final ArrayList<Pijakan> pijakan = new ArrayList<>();
        public void Pijakan(){
            count++; //increment pijakan (tanda pijakan bertambah)
        }
        public void updatePijakan(){
            Iterator<Pijakan> itr = pijakan.iterator();
            while(itr.hasNext()){
                Pijakan pi = itr.next();
                if(pi.getY() < max){ //tidak lebih dari banyak maksimal pijakan
                    itr.remove(); //hapus sesuai iterator
                    count--; //decrement (hapus pijakan paling awal yg melebihi layar 
                } else{
                    pi.update(); //update piakan
                }
            }
        }
        public void renderPi(Graphics g){
            for (Pijakan pi : pijakan){
                pi.render(g); //render pijakan
                g.setColor(Color.WHITE); //warna tulisan bobot nilai perpijakan 
                g.setFont(new Font("Segoe UI", Font.BOLD, 12)); //set font 
                int weight = Math.round(pi.getPoint()); //get skor per pijakan 
                String weightText = "" + weight*10; //kali 10 agar menjadi bilangan bulat
                //set skor  per panjang pijakan
                int textX = (int) (pi.getX() + pi.getWidth() / 2 - g.getFontMetrics().stringWidth(weightText) / 2);
                int textY = (int) (pi.getY() + pi.getHeight() / 2);
                g.drawString(weightText, textX, textY); //masukan skor
            }
        }  
        private int calculateWeight(int height) {
            int minHeight = 40; // Minimum height of the obstacle
            int maxHeight = GAME_HEIGHT - 50; // Maximum height of the obstacle
            int minWeight = 1; // Minimum weight value
            int maxWeight = 10; // Maximum weight value
            double weight = ((double) (height - minHeight) / (maxHeight - minHeight)) * (maxWeight - minWeight) + minWeight;
            return (int) Math.round(weight);
        }
        public void Add(){
            if(count < max){
                int minWidth = height_pi; // Minimum width 
                int maxWidth = GAME_WIDTH - 150; // Maximum width
                int minHeight = 40; // Minimum height 
                int maxHeight = GAME_HEIGHT - 50; // Maximum height 
                // set random panjang dan lebar sesuai spesifikasi 
                int width = rand.nextInt(maxWidth - minWidth + 1) + minWidth;
                int height = rand.nextInt(maxHeight - minHeight + 1) + minHeight;
                int panjang = (int)(rand.nextInt(((GAME_WIDTH-150)- height_pi)+1)+ height_pi); //set random panjang obstacle 
                float x = 0; // set x 0 untuk berada di koordinat sesuai spesifikasi 
                float y =GAME_HEIGHT - 50;    //posisi awal player        
                Pijakan obstacle = new Pijakan(x, y, panjang, height_pi,  calculateWeight(height)); //set pijakan
                pijakan.add(obstacle); // tambahkan ke list
                count++;
            }
        }
        public ArrayList<Pijakan> getPijakan(){
            return pijakan;
        }
}
