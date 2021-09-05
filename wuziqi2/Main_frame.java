package wuziqi2;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class Main_frame extends JFrame implements MouseListener,Runnable{
    Mypan  pan;
    int width=450;
    int height=450;
    int using;
    int XuanZe;
    
    public Main_frame(int XuanZe,int using) {
    	this.XuanZe = XuanZe;
    	this.using = using;
        pan=new Mypan(width,height);
        pan.addMouseListener(this);
        this.add(pan);
        this.setTitle("五子棋"); 
        this.setSize(width+47, height+69);
        this.setVisible(true);
        this.setLocation(200, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override 
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
         
    }
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
         
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
         
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //获得坐标后转换维数组坐标
        int x=(arg0.getX()+14)/30-1;
        int y=(arg0.getY()+14)/30-1;
         
    }
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
         
    }
    @Override
    public void run() {
   while(true)
   {
        try {
        	if(XuanZe == 12) {
            Thread.sleep(1000);
            if(using==1){
                //pan.chess[y][x]=1;
                pan.randomChess(1);
                pan.repaint();
                //判断是否五颗一条线
                if(pan.judge(pan.x,pan.y,1)){
                      
                    JOptionPane.showMessageDialog(this, "已经五子"+"A"+"胜出");
                    using=1;
                    pan.clearChess();
                     
                }else {
                    using=2;
                }
            }
            Thread.sleep(1000);
            if(using==2){
                //自动完成自动落子
            	
                pan.randomChess1(2);
                pan.repaint();
                //判断是否五颗一条线
                if(pan.judge1(pan.x, pan.y,2)){
                     
                    JOptionPane.showMessageDialog(this, "已经五子"+"B"+"胜出");
                    using=1;
                    pan.clearChess1();
                     
                }else {
                    using=1;
                }
                 
            }}
        	if(XuanZe == 13) {
                Thread.sleep(1000);
                if(using==1){
                    //pan.chess[y][x]=1;
                    pan.randomChess(1);
                    pan.repaint();
                    //判断是否五颗一条线
                    if(pan.judge(pan.x,pan.y,1)){
                          
                        JOptionPane.showMessageDialog(this, "已经五子"+"A"+"胜出");
                        using=1;
                        pan.clearChess();
                         
                    }else {
                        using=3;
                    }
                }
                Thread.sleep(1000);
                if(using==3){
                    //自动完成自动落子
                	
                    pan.randomChess2(3);
                    pan.repaint();
                    //判断是否五颗一条线
                    if(pan.judge2(pan.x, pan.y,3)){
                         
                        JOptionPane.showMessageDialog(this, "已经五子"+"C"+"胜出");
                        using=3;
                        pan.clearChess2();
                         
                    }else {
                        using=1;
                    }
                     
                }}
        	if(XuanZe == 23) {
                Thread.sleep(1000);
                if(using==2){
                    //pan.chess[y][x]=1;
                    pan.randomChess1(2);
                    pan.repaint();
                    //判断是否五颗一条线
                    if(pan.judge1(pan.x,pan.y,2)){
                          
                        JOptionPane.showMessageDialog(this, "已经五子"+"A"+"胜出");
                        using=2;
                        pan.clearChess1();
                         
                    }else {
                        using=3;
                    }
                }
                Thread.sleep(1000);
                if(using==3){
                    //自动完成自动落子
                	
                    pan.randomChess2(3);
                    pan.repaint();
                    //判断是否五颗一条线
                    if(pan.judge2(pan.x, pan.y,3)){
                         
                        JOptionPane.showMessageDialog(this, "已经五子"+"C"+"胜出");
                        using=3;
                        pan.clearChess2();
                         
                    }else {
                        using=2;
                    }
                     
                }}
            pan.repaint();
        } catch (InterruptedException e) {
         // TODO Auto-generated catch block
            e.printStackTrace();
      }
   }
    
         
    }
 
}
 
