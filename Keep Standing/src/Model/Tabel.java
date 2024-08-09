/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author elsan
 */
/*
Saya [Elsa Nabiilah] [2108805] mengerjakan Tugas Masa Depan dalam mata kuliah Desain Pemrograman Berorientasi Objek 
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
*/
public class Tabel extends DB{ //mewarisi class DB 
    public String query;
    public Tabel(){
        super();
    }
    public void query(String username, int score, int standing){
        try{ //dipanggil jika username sudah ada, tinggal update score sama standing 
             query = "UPDATE menu SET score = score + " + score + ", standing = standing + " + standing + " WHERE usn = '" + username + "'";
            if(updateQuery(query) == 0){ //jika belum maka insert semuanya
                query = "INSERT INTO menu VALUES(NULL, '" + username + "', "+ Integer.toString(score) +", " + Integer.toString(standing) + ")";
                updateQuery(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void getdata(String usn){ //mengambil data dari row tabel berdasarkan mouse clicked user 
       String query = "SELECT * from menu WHERE usn='" + usn + "'";
        try{
            selectQuery(query);
        }catch(Exception ex){
            Logger.getLogger(Tabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
