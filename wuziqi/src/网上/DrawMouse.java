package 网上;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class DrawMouse extends MouseAdapter implements Config,ActionListener{
    //添加动作监听器（监听按钮）和鼠标监听器（鼠标所点位置画棋子）
    private Graphics g;
    private int x,y,CO=1,index=0;
    private int point[][];
    private int pointweight[][]=new int [X0+(LINE+1)*WID][Y0+(LINE+1)*WID];
    private int orderx[]=new int [X0+(LINE+1)*WID],ordery[]=new int [Y0+(LINE+1)*WID];
    private Fivebord fb;
    private int pc=0;
    public HashMap <String,Integer> hm = new HashMap <String,Integer>();
    //哈希表用来存放不同棋子布局下的不同权值


    DrawMouse(Fivebord fb) {
        this.g = fb.getGraphics();
        this.fb=fb;
        sethashmap();
    }
    //传棋子数组
    public void setpoint(int point[][]){
        this.point=point;
    }
    public void sethashmap(){
        hm.put("1", 1);
        //某一方向线上只有一个黑棋
        hm.put("12", 5);
        //某一方向线上紧接着一个黑棋有一个白棋
        hm.put("11", 10);
        hm.put("112", 15);
        //某一方向线上紧接着两个相邻的黑棋后有一个白棋（以此类推）
        hm.put("111", 100);
        hm.put("1112", 105);
        hm.put("1111", 1000);

        hm.put("2", 1);
        hm.put("21", 5);
        hm.put("22", 10);
        hm.put("221", 15);
        hm.put("222", 100);
        hm.put("2221", 105);
        hm.put("2222", 1000);
    }

    public void actionPerformed(ActionEvent e){
        //悔棋操作，将棋子数目减一，然后重绘界面即可
        if("悔棋".equals(e.getActionCommand())&&index>0){
            System.out.println("悔棋");
            index--;
            point[orderx[index]][ordery[index]]=0;
            fb.paint(g);
        }
        //人机模式一旦点击，界面所有棋子清零，开始人机对战（pc=1)
        if("人机".equals(e.getActionCommand())){
            System.out.println("人机");
            pc=1;
            index=0;
            for(int i=X0;i<=X0+WID*LINE;i+=WID){
                for(int j=Y0;j<=Y0+WID*LINE;j+=WID){
                    point[i][j]=0;
                }
            }
            fb.paint(g);
        }
        //人人对战，也是点击按钮棋子清零，开始人人对战（pc=0)
        if("人人".equals(e.getActionCommand())){
            System.out.println("人机");
            pc=0;
            index=0;
            for(int i=X0;i<=X0+WID*LINE;i+=WID){
                for(int j=Y0;j<=Y0+WID*LINE;j+=WID){
                    point[i][j]=0;
                }
            }
            fb.paint(g);
        }
    }

    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        //得到点击的点
        if((x-X0)%WID>=WID/2){
            x=x-(x-X0)%WID+WID;
        }
        else{
            x=x-(x-X0)%WID;
        }
        if((y-Y0)%WID>=WID/2){
            y=y-(y-Y0)%WID+WID;
        }
        else{
            y=y-(y-Y0)%WID;
        }
        //对点的位置进行修正(保证每次点击都正好下在网格交汇处）
        if(point[x][y]==0&&x>=X0&&x<=X0+WID*LINE&&y>=Y0&&y<=Y0+WID*LINE){
            //人人对战：直接用鼠标检测，依次变换颜色黑或白
            if(pc==0){
                if(g.getColor()==Color.black){
                    g.setColor(Color.WHITE);
                    CO=2;
                }
                else{
                    g.setColor(Color.BLACK);
                    CO=1;
                }
            }
            //人机对战，每次人下过棋子之后，计算机根据现有棋盘布局对棋局分析和总和并判断机器需要下的位置
            else if(pc==1){
                g.setColor(Color.BLACK);
                CO=1;
            }
            g.fillOval(x-WID/2, y-WID/2, WID, WID);
            point[x][y]=CO;
            System.out.println(index+ " "+ x+" "+y);
            orderx[index]=x;
            ordery[index]=y;
            index++;
            if(exam()==0){
                //自己敲代码过程中的验证、、、、、、可以不用在意这类输出。
                System.out.println("hahahahhhaahhahah");
                if(pc==1){
                    System.out.println("HEHEHEHEHEHEHEHEHEHEHE");
                    g.setColor(Color.WHITE);
                    CO=2;
                    AI();
                    exam();
                }
            }
        }

    }
    //检测是否有一方获胜，跳出提示框提示某一方获胜
    public int exam(){
        int w=0;
        for(int i=X0-WID;i<=X0+WID*(LINE+1);i+=WID){
            for(int j=Y0-WID;j<=Y0+WID*(LINE+1);j+=WID){
                if(point[i][j]!=0){
                    int exam1=0,exam2=0,exam3=0,exam4=0;
                    //水平、竖直、左斜、右斜四个方向上同色棋子相连最多的个数
                    for(int t=WID;t<5*WID;t+=WID){
                        if(i+t<=X0+WID*(LINE+1)&&point[i+t][j]==point[i][j]){
                            exam1++;
                        }
                        if(j+t<=Y0+WID*(LINE+1)&&point[i][j+t]==point[i][j]){
                            exam2++;
                        }
                        if(i+t<=X0+WID*(LINE+1)&&j+t<=Y0+WID*(LINE+1)&&point[i+t][j+t]==point[i][j]){
                            exam3++;
                        }
                        if(i+t<=X0+WID*(LINE+1)&&j>=t&&point[i+t][j-t]==point[i][j]){
                            exam4++;
                        }
                    }
                    System.out.println(exam1+" "+exam2+" " +exam3+" "+exam4);
                    if(exam1==4||exam2==4||exam3==4||exam4==4){//某一方向上同色5子相连，一方获胜
                        if(point[i][j]==1){
                            w=1;
                            //弹出提示框
                            JOptionPane.showMessageDialog(null, "黑子胜");
                        }
                        else{
                            w=2;
                            JOptionPane.showMessageDialog(null, "白子胜");
                        }
                        i=X0+WID*(LINE+1)+1;
                        break;
                    }
                }
            }
        }
        return w;
    }
    //AI算法
    //分别向左、香油、左下、、、、、等8个方向检测棋子布局情况并累加在该点的权值上
    //再找出图片上没有棋子并且权值最大的点下棋子
    //记得每次下棋将各个空位置的权值归0，以便下一次计算权值累加
    public void AI(){
        for(int i=X0;i<X0+WID*(LINE+1);i+=WID){
            for(int j=Y0;j<Y0+WID*(LINE+1);j+=WID){
                if(point[i][j]==0){
                    //像右寻找
                    //System.out.print("pointweight["+(i-X0)/WID+"]["+(j-Y0)/WID+"]:");
                    int color=0;
                    String code="";
                    for(int k=i+WID;k<=X0+WID*LINE;k+=WID){
                        if(point[k][j]!=0){
                            if(color==0){
                                color=point[k][j];
                                code+=point[k][j];
                            }
                            else{
                                if(point[k][j]==color){
                                    code+=point[k][j];
                                }
                                else{
                                    code+=point[k][j];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    Integer value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
                    //向下寻找
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=j+WID;k<=X0+WID*LINE;k+=WID){
                        if(point[i][k]!=0){
                            if(color==0){
                                color=point[i][k];
                                code+=point[i][k];
                            }
                            else{
                                if(point[i][k]==color){
                                    code+=point[i][k];
                                }
                                else{
                                    code+=point[i][k];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
                    //向左
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=i-WID;k>=X0;k-=WID){
                        if(point[k][j]!=0){
                            if(color==0){
                                color=point[k][j];
                                code+=point[k][j];
                            }
                            else{
                                if(point[k][j]==color){
                                    code+=point[k][j];
                                }
                                else{
                                    code+=point[k][j];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
                    //向上
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=j-WID;k>=Y0;k-=WID){
                        if(point[i][k]!=0){
                            if(color==0){
                                color=point[i][k];
                                code+=point[i][k];
                            }
                            else{
                                if(point[i][k]==color){
                                    code+=point[i][k];
                                }
                                else{
                                    code+=point[i][k];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
                    //向右上寻找
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=i+WID,w=j+WID;k<=X0+WID*LINE&&w<=Y0+WID*LINE;k+=WID,w+=WID){
                        if(point[k][w]!=0){
                            if(color==0){
                                color=point[k][w];
                                code+=point[k][w];
                            }
                            else{
                                if(point[k][w]==color){
                                    code+=point[k][w];
                                }
                                else{
                                    code+=point[k][w];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=i-WID,w=j-WID;k>=X0&&w>=Y0;k-=WID,w-=WID){
                        if(point[k][w]!=0){
                            if(color==0){
                                color=point[k][w];
                                code+=point[k][w];
                            }
                            else{
                                if(point[k][w]==color){
                                    code+=point[k][w];
                                }
                                else{
                                    code+=point[k][w];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=i+WID,w=j-WID;k<=X0+LINE*WID&&w>=Y0;k+=WID,w-=WID){
                        if(point[k][w]!=0){
                            if(color==0){
                                color=point[k][w];
                                code+=point[k][w];
                            }
                            else{
                                if(point[k][w]==color){
                                    code+=point[k][w];
                                }
                                else{
                                    code+=point[k][w];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
//  System.out.print(pointweight[i][j]+" ");
                    code="";
                    color=0;
                    for(int k=i-WID,w=j+WID;k>=X0&&w<=Y0+LINE*WID;k-=WID,w+=WID){
                        if(point[k][w]!=0){
                            if(color==0){
                                color=point[k][w];
                                code+=point[k][w];
                            }
                            else{
                                if(point[k][w]==color){
                                    code+=point[k][w];
                                }
                                else{
                                    code+=point[k][w];
                                    break;
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    value=hm.get(code);
                    if(value != null){
                        pointweight[i][j] += value;
                    }
//  System.out.println(pointweight[i][j]);
                }
            }
        }
        //寻找最大权值的点并画棋子
        int maxx=X0,maxy=Y0;
        for(int i=X0;i<=X0+WID*LINE;i+=WID){
            for(int j=Y0;j<=Y0+WID*LINE;j+=WID){
                System.out.print(pointweight[i][j]+" ");
                if(pointweight[i][j]>pointweight[maxx][maxy]){
                    maxx=i;
                    maxy=j;
                }

            }
            System.out.println();
        }
        g.fillOval(maxx-WID/2, maxy-WID/2, WID, WID);
        point[maxx][maxy]=CO;
        System.out.println(index+ " "+ maxx+" "+maxy);
        orderx[index]=maxx;
        ordery[index]=maxy;
        index++;
        //全部权值归零方便下次使用
        for(int i=X0;i<=X0+WID*LINE;i+=WID){
            for(int j=Y0;j<=Y0+WID*LINE;j+=WID){
                pointweight[i][j]=0;
            }
        }
    }

}