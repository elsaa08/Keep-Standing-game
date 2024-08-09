/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import viewModel.runGame.STATE;

/**
 *
 * @author elsan
 */
public class setKey implements KeyListener{
    private final runGame game;
    public setKey(runGame game) {
        this.game = game; // set game
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
     @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> game.getUser().setKiri(true); // kiri
            case KeyEvent.VK_RIGHT -> game.getUser().setKanan(true); // kanan
        }
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE) { //jika klik tombol spasi
            game.gameState = STATE.GameOver; // ubah state menjadi game over
        }
    }
     @Override
    public void keyReleased(KeyEvent e) { //release key yang telah di klik (Set ulang)
        switch (e.getKeyCode()) {
          
            case KeyEvent.VK_LEFT -> game.getUser().setKiri(false);
           
            case KeyEvent.VK_RIGHT -> game.getUser().setKanan(false);
        }
    }
}
