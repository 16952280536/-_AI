package 网上;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Fivebord extends JPanel implements Config{
    private static final long serialVersionUID = 1L;
    private int point[][]=new int [SIZE][SIZE];

    public static void main(String[] args) {
        Fivebord fb = new Fivebord();
        fb.showFivebord();
    }

    public void showFivebord() {
        //一下是关于界面的常规设置
        javax.swing.JFrame jf = new javax.swing.JFrame();
        jf.setTitle("FIVEBORD");
        jf.setSize(SIZE+100, SIZE);
        jf.setDefaultCloseOperation(3);
        jf.setLocationRelativeTo(null);
        jf.setLayout(new BorderLayout());

        JPanel jp1=new JPanel();
        jp1.setBackground(Color.ORANGE);
        jp1.setPreferredSize(new Dimension(100, SIZE));
        jf.add(jp1,BorderLayout.EAST);

        javax.swing.JButton jbu1 = new javax.swing.JButton("悔棋");
        jp1.add(jbu1);

        javax.swing.JButton jbu2 = new javax.swing.JButton("人机");
        jp1.add(jbu2);

        javax.swing.JButton jbu3 = new javax.swing.JButton("人人");
        jp1.add(jbu3);

        this.setBackground(Color.YELLOW);
        jf.add(this,BorderLayout.CENTER);

        jf.setVisible(true);

        //以下给界面添加监听器，包括画板和按钮
        DrawMouse mouse=new DrawMouse(this);
        jbu1.addActionListener(mouse);
        jbu2.addActionListener(mouse);
        jbu3.addActionListener(mouse);
        this.addMouseListener(mouse);
        //监听器中需要考虑当前棋盘上的棋子和位置
        mouse.setpoint(point);


    }
    public void paint(Graphics g) {
        super.paint(g);
        //super.paint
        //由于paint函数是界面自带的函数且在某些时候会自动调用
        //super.paint(g)表示屏蔽父类的函数内容，换做自己接下来改写的内容
        Graphics2D gr = (Graphics2D)g;
        gr.setStroke(new BasicStroke(1));
        //2D画笔变粗度为1
        for(int i=X0;i<=X0+LINE*WID;i+=WID){
            for(int j=Y0;j<=Y0+LINE*WID;j+=WID){
                g.drawLine(X0, j, X0+LINE*WID, j);
                g.drawLine(i, Y0, i,Y0+LINE*WID);
            }
        }
        //画内部16格
        gr.setStroke(new BasicStroke(2));
        //画笔粗度变为2
        g.drawLine(X0-WID, Y0-WID, X0-WID, Y0+(LINE+1)*WID);
        g.drawLine(X0-WID, Y0-WID, X0+(LINE+1)*WID, Y0-WID);
        g.drawLine(X0+(LINE+1)*WID, Y0-WID, X0+(LINE+1)*WID, Y0+(LINE+1)*WID);
        g.drawLine(X0-WID, Y0+(LINE+1)*WID, X0+(LINE+1)*WID, Y0+(LINE+1)*WID);
        //画四周较粗的边框（美观起见，没有实际意义）
        for(int i=X0;i<=X0+(WID*(LINE+1));i+=WID){
            for(int j=Y0;j<=Y0+(LINE+1)*WID;j+=WID){
                if(point[i][j]==1){
                    //画黑棋
                    g.setColor(Color.BLACK);
                    g.fillOval(i-WID/2, j-WID/2, WID, WID);
                }
                else if(point[i][j]==2){
                    //画白棋
                    g.setColor(Color.WHITE);
                    g.fillOval(i-WID/2, j-WID/2, WID, WID);
                }
            }
        }
        //根据point的内容画出相应的点（即棋子）
    }

}
