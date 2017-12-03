
package editor.mapa;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
/**
 *
 * @author djmbdv
 */
public class Lienzo extends JPanel implements MouseListener,MouseMotionListener{
    private int matriz [][];
    
    BufferedImage img[];
    BufferedImage aleatorias[];
    int pincel;
    int ancho;
    int alto;
    public void setPincel(int i){
        pincel = i;
    }
    public int [][] getMatriz(){
        return matriz;
    }
    public void updateSize(){
        setSize(ancho*32,alto*32);
    }
    public Lienzo(){
        ancho = 32;
        alto = 24;
        pincel  = 1;
        matriz =  new int[24][32];
        img = new  BufferedImage[16];
        aleatorias = new BufferedImage [5];
        try {
            for(int i = 0; i <16; i++){
         
            img[i]  = ImageIO.read(getClass().getResource("/img/" + (i+1)+ ".png")); 
         
        }  for(int i = 1; i <=5; i++){
             aleatorias[i-1]  = ImageIO.read(getClass().getResource("/img/-" + i + ".png")); 
    
        }
        } catch (IOException e) {
            System.err.println(e);
        }
        addMouseListener(this);
        addMouseMotionListener(this);
        updateSize();
    }
    
    public void setCasilla(Point p, int n){
        try{
            matriz[p.y/32][p.x/32] = n;
        }catch(Exception e){
         
        }
    }
    public String matrizString(){
        String s = "";
        for(int j = 0; j < alto; j++){
            for(int i = 0; i < ancho; i++){
                s+= matriz[j][i] + " ";
            }
            s+="\r\n";
        }
        return s;
    }
    @Override
    public void paintComponent(Graphics g){
        this.getParent().repaint();
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
       
        
        
        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(1,1,ancho * 32 - 1 ,alto * 32 - 1 );
         g.setColor(Color.white);
        for(int j = 0; j < alto; j++){
            for(int i = 0; i < ancho; i++){
                if(matriz[j][i] == 0){
                    g.fillRect(i*32, j*32, 32, 32);
                }else if(matriz[j][i] > 0){
                    g.drawImage(img[matriz[j][i] -1] , i*32, j*32, 32, 32, this);
                }else g.drawImage(aleatorias[-1*matriz[j][i] -1] , i*32, j*32, 32, 32, this);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setCasilla(e.getPoint(), pincel);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setCasilla(e.getPoint(), pincel);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
