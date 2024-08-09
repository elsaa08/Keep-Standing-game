///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Model;
//
//import java.awt.Graphics;
//import java.awt.Rectangle;
//
///**
// *
// * @author elsan
// */
//public abstract class Koor {
//    private float x;
//    private float y;
//    private int width;
//    private int height;
//    private Rectangle coll;
//    
//    public Koor(float x, float y, int width, int height){
//    this.coll = new Rectangle((int)x, (int)y, width, height);
//    this.x =x;
//    this.y=y;
//    this.width = width;
//    this.height = height;
// 
//}
//    private void updateColl(){
//        this.coll.x = (int) x;
//        this.coll.y = (int) y;
//    }
//    
//    public Rectangle getColl(){
//        return this.coll;
//    }
//    public abstract void render(Graphics graph);
//    
//}
