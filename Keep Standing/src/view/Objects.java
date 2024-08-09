package view;
import java.awt.*;

import javax.swing.JFrame;
import viewModel.runGame;
import viewModel.setKey;
import Model.GameOption;


public class Objects extends Canvas {
    GameOption go;
    JFrame window;
    public Objects(runGame game){
        String title = "KEEP STANDING"; // set judul frame game
        window = new JFrame(title); // buat window game
        window.addKeyListener(new setKey(game)); 
        window.add(game); // menambah game ke window
        window.setSize(go.GAME_WIDTH, go.GAME_HEIGHT); // set ukuran window
        window.setLocationRelativeTo(null); // set lokasi frame dibuat
        window.setResizable(false); // set resizeable frame
        window.setVisible(true); // set agar visible
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void CloseWindow() {
        // menutup window
        window.setVisible(false); // set window jadi tidak visible
        window.dispose(); // membersihkan
    }
}
