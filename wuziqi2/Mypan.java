package wuziqi2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

public class Mypan extends JPanel implements My_Pan{
    
   int width;
   int height;
   int x,y;
   //冲二冲三冲四
   int er=4,san=8,si=7;
   int chess[][]=new int[15][15];
    
   public  Mypan(int width,int height){
       this.height=height;
       this.width=width;
       this.setBackground(Color.green);
       this.setSize(width+17,height+139);
       this.setVisible(true);
   }
    
   public void paint(Graphics g){
       super.paint(g);
       g.setColor(Color.black);
       //画出横
       for(int i=0;i<15;i++) {
           g.setColor(Color.BLACK);
           g.drawString(String.valueOf(i+1), 15, i*30+30);
           g.drawLine(30, i*30+30, this.width, i*30+30);
       }
       //画出竖
       for(int i=0;i<15;i++){
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(i+1), i*30+15,30);
        g.drawLine(i*30+30, 30,  i*30+30,this.height);
       }
       //画出棋子
       
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               //System.out.print(chess[i][j]+" ");
               if(chess[j][i]!=0) {
                   //正数自己的棋子
                   if(chess[j][i]==1) {
                        g.setColor(Color.BLACK);
                       g.fillOval((i+1)*30-13, (j+1)*30-13, 26, 26);
                   }else if(chess[j][i]==2) {
                       //别人的棋子
                       g.setColor(Color.white);
                       g.fillOval((i+1)*30-13, (j+1)*30-13, 26, 26);
                   }else if(chess[j][i]==3){
                	   g.setColor(Color.yellow);
                       g.fillOval((i+1)*30-13, (j+1)*30-13, 26, 26);
                   }
               }
           }
           //System.out.println();
       }
   }
   public boolean judge(int x,int y,int type){
       boolean count=false;
       for(int i=0;i<4;i++) {
           if(find(x,y,i,type)>=5){
               count=true;
               break;
           }
       }
       return count;
   }
   //找到连接的棋子数
   public int find(int x,int y,int dir,int type){
       int sum1=0,sum2=0;
       int y1=y,x1=x;
       switch(dir) 
       {
       case 0:
           //两半查找    横  左右
           for(int i=0;i<5;i++) {
               if(--x1<0) {
                   break;
               }
               if(chess[y][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(++x>14) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
           break;
       case 1:
           //两半查找   竖 上下
           for(int i=0;i<5;i++) {
               if(++y1>14){
                   break;
               }
               if(chess[y1][x]!=type) {
                   break;
               }else{
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--y<0) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else{
                   sum2++;
               }
           }
           break;
       case 2:
           //两半查找   撇  
           for(int i=0;i<5;i++) 
           {
               if(++x1>14||++y1>14) {
                   break;
               }
               if(chess[y1][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--x<0||--y<0) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
           break;
       case 3: 
           //两半查找   捺
           for(int i=0;i<5;i++) 
           {
               if(++x1>14||--y1<0) {
                   break;
               }
               if(chess[y1][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--x<0||++y>14) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
            
           break;
        }
       return sum1+sum2+1;
        
   }
   //电脑自由下棋
   public void randomChess(int type){
        
       int cumputer[][][]=new int [15][15][4];
       int user[][][]=new int [15][15][4];
       int max1=0,max2=0;
       int location[][]=new int [2][2];
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               if(this.chess[j][i]==0)
               {   
                   int sum=0;
                   for(int k=0;k<4;k++){
                       cumputer[j][i][k]=this.find(i, j, k, 2);
                       if(cumputer[j][i][k]==3) {
                           sum+=this.er+(int)Math.random()*2;
                       }else if(cumputer[j][i][k]==4) {
                           sum+=this.san+10+(int)Math.random()*3;
                       }else if(cumputer[j][i][k]==5){
                           sum+=this.si+15+(int)Math.random()*4;
                       }else {
                           sum+=cumputer[j][i][k];
                       }
                        
                   }
                   if(max1<sum) {
                       max1=sum;
                       location[0][0]=j;
                       location[0][1]=i;
                   }
               }
                
           }
       }
        
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               if(this.chess[j][i]==0)
               {
                   int sum=0;
                   for(int k=0;k<4;k++) {
                       user[j][i][k]=this.find(i, j, k, 1);
                       if(user[j][i][k]==3) {
                           sum+=this.er+(int)Math.random()*2;
                       }else if(user[j][i][k]==4) {
                           sum+=this.san+10+(int)Math.random()*3;
                       }else if(user[j][i][k]==5){
                           sum+=this.si+7+(int)Math.random()*4;
                       }else{
                           sum+=user[j][i][k];
                       }
                        
                   }
                   if(max2<sum) {
                       max2=sum;
                       location[1][0]=j;
                       location[1][1]=i;
                   }
               }
                
           }
       }
       //贪心决策
       //都为零自动下子
       if(max2==4&&max1==4) {
           int x=(int)(Math.random()*100)%15;
           int y=(int)(Math.random()*100)%15;
           chess[y][x]=type;
           this.x=x;
           this.y=y;
       }else if(max1>=max2){
           chess[location[0][0]][location[0][1]]=type;
           x=location[0][1];
           y=location[0][0];
       }else{
           chess[location[1][0]][location[1][1]]=type;
           x=location[1][1];
           y=location[1][0];
       }
        
        
        
   }
   public void clearChess() {
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               this.chess[i][j]=0;
           }
       }
        
   }
   
   
   
   public boolean judge1(int x,int y,int type){
       boolean count=false;
       for(int i=0;i<4;i++) {
           if(find1(x,y,i,type)>=5){
               count=true;
               break;
           }
       }
       return count;
   }
   //找到连接的棋子数
   public int find1(int x,int y,int dir,int type){
       int sum1=0,sum2=0;
       int y1=y,x1=x;
       switch(dir) 
       {
       case 0:
           //两半查找    横  左右
           for(int i=0;i<5;i++) {
               if(--x1<0) {
                   break;
               }
               if(chess[y][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(++x>14) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
           break;
       case 1:
           //两半查找   竖 上下
           for(int i=0;i<5;i++) {
               if(++y1>14){
                   break;
               }
               if(chess[y1][x]!=type) {
                   break;
               }else{
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--y<0) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else{
                   sum2++;
               }
           }
           break;
       case 2:
           //两半查找   撇  
           for(int i=0;i<5;i++) 
           {
               if(++x1>14||++y1>14) {
                   break;
               }
               if(chess[y1][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--x<0||--y<0) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
           break;
       case 3: 
           //两半查找   捺
           for(int i=0;i<5;i++) 
           {
               if(++x1>14||--y1<0) {
                   break;
               }
               if(chess[y1][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--x<0||++y>14) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
            
           break;
        }
       return sum1+sum2+1;
        
   }
   //电脑自由下棋
   public void randomChess1(int type){
        
       int cumputer[][][]=new int [15][15][4];
       int user[][][]=new int [15][15][4];
       int max1=0,max2=0;
       int location[][]=new int [2][2];
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               if(this.chess[j][i]==0)
               {   
                   int sum=0;
                   for(int k=0;k<4;k++){
                       cumputer[j][i][k]=this.find(i, j, k, 2);
                       if(cumputer[j][i][k]==3) {
                           sum+=this.er+(int)Math.random()*2;
                       }else if(cumputer[j][i][k]==4) {
                           sum+=this.san+10+(int)Math.random()*3;
                       }else if(cumputer[j][i][k]==5){
                           sum+=this.si+15+(int)Math.random()*4;
                       }else {
                           sum+=cumputer[j][i][k];
                       }
                        
                   }
                   if(max1<sum) {
                       max1=sum;
                       location[0][0]=j;
                       location[0][1]=i;
                   }
               }
                
           }
       }
        
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               if(this.chess[j][i]==0)
               {
                   int sum=0;
                   for(int k=0;k<4;k++) {
                       user[j][i][k]=this.find(i, j, k, 1);
                       if(user[j][i][k]==3) {
                           sum+=this.er+(int)Math.random()*2;
                       }else if(user[j][i][k]==4) {
                           sum+=this.san+10+(int)Math.random()*3;
                       }else if(user[j][i][k]==5){
                           sum+=this.si+7+(int)Math.random()*4;
                       }else{
                           sum+=user[j][i][k];
                       }
                        
                   }
                   if(max2<sum) {
                       max2=sum;
                       location[1][0]=j;
                       location[1][1]=i;
                   }
               }
                
           }
       }
       //贪心决策
       //都为零自动下子
       if(max2==4&&max1==4) {
           int x=(int)(Math.random()*100)%15;
           int y=(int)(Math.random()*100)%15;
           chess[y][x]=type;
           this.x=x;
           this.y=y;
       }else if(max1>=max2){
           chess[location[0][0]][location[0][1]]=type;
           x=location[0][1];
           y=location[0][0];
       }else{
           chess[location[1][0]][location[1][1]]=type;
           x=location[1][1];
           y=location[1][0];
       }
        
        
        
   }
   public void clearChess1() {
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               this.chess[i][j]=0;
           }
       }
        
   }

   
   
   public boolean judge2(int x,int y,int type){
       boolean count=false;
       for(int i=0;i<4;i++) {
           if(find2(x,y,i,type)>=5){
               count=true;
               break;
           }
       }
       return count;
   }
   //找到连接的棋子数
   public int find2(int x,int y,int dir,int type){
       int sum1=0,sum2=0;
       int y1=y,x1=x;
       switch(dir) 
       {
       case 0:
           //两半查找    横  左右
           for(int i=0;i<5;i++) {
               if(--x1<0) {
                   break;
               }
               if(chess[y][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(++x>14) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
           break;
       case 1:
           //两半查找   竖 上下
           for(int i=0;i<5;i++) {
               if(++y1>14){
                   break;
               }
               if(chess[y1][x]!=type) {
                   break;
               }else{
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--y<0) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else{
                   sum2++;
               }
           }
           break;
       case 2:
           //两半查找   撇  
           for(int i=0;i<5;i++) 
           {
               if(++x1>14||++y1>14) {
                   break;
               }
               if(chess[y1][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--x<0||--y<0) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
           break;
       case 3: 
           //两半查找   捺
           for(int i=0;i<5;i++) 
           {
               if(++x1>14||--y1<0) {
                   break;
               }
               if(chess[y1][x1]!=type) {
                   break;
               }else {
                   sum1++;
               }
           }
           for(int i=0;i<5;i++) {
               if(--x<0||++y>14) {
                   break;
               }
               if(chess[y][x]!=type) {
                   break;
               }else {
                   sum2++;
               }
           }
            
           break;
        }
       return sum1+sum2+1;
        
   }
   //电脑自由下棋
   
   public void clearChess2() {
       for(int i=0;i<15;i++) {
           for(int j=0;j<15;j++) {
               this.chess[i][j]=0;
           }
       }
        
   }

   
   public static HashMap<String,Integer> map = new HashMap<String,Integer>();//设置不同落子情况和相应权值的数组
   static {

       //被堵住
       map.put("01", 25);//眠1连
       map.put("02", 22);//眠1连
       map.put("001", 17);//眠1连
       map.put("002", 12);//眠1连
       map.put("0001", 17);//眠1连
       map.put("0002", 12);//眠1连

       map.put("0102",25);//眠1连，15
       map.put("0201",22);//眠1连，10
       map.put("0012",15);//眠1连，15
       map.put("0021",10);//眠1连，10
       map.put("01002",25);//眠1连，15
       map.put("02001",22);//眠1连，10
       map.put("00102",17);//眠1连，15
       map.put("00201",12);//眠1连，10
       map.put("00012",15);//眠1连，15
       map.put("00021",10);//眠1连，10

       map.put("01000",25);//活1连，15
       map.put("02000",22);//活1连，10
       map.put("00100",19);//活1连，15
       map.put("00200",14);//活1连，10
       map.put("00010",17);//活1连，15
       map.put("00020",12);//活1连，10
       map.put("00001",15);//活1连，15
       map.put("00002",10);//活1连，10

       //被墙堵住
       map.put("0101",65);//眠2连，40
       map.put("0202",60);//眠2连，30
       map.put("0110",80);//眠2连，40
       map.put("0220",76);//眠2连，30
       map.put("011",80);//眠2连，40
       map.put("022",76);//眠2连，30
       map.put("0011",65);//眠2连，40
       map.put("0022",60);//眠2连，30

       map.put("01012",65);//眠2连，40
       map.put("02021",60);//眠2连，30
       map.put("01102",80);//眠2连，40
       map.put("02201",76);//眠2连，30
       map.put("01120",80);//眠2连，40
       map.put("02210",76);//眠2连，30
       map.put("00112",65);//眠2连，40
       map.put("00221",60);//眠2连，30

       map.put("01100",80);//活2连，40
       map.put("02200",76);//活2连，30
       map.put("01010",75);//活2连，40
       map.put("02020",70);//活2连，30
       map.put("00110",75);//活2连，40
       map.put("00220",70);//活2连，30
       map.put("00011",75);//活2连，40
       map.put("00022",70);//活2连，30

       //被堵住
       map.put("0111",150);//眠3连，100
       map.put("0222",140);//眠3连，80

       map.put("01112",150);//眠3连，100
       map.put("02221",140);//眠3连，80

       map.put("01110", 1100);//活3连
       map.put("02220", 1050);//活3连
       map.put("01101",1000);//活3连，130
       map.put("02202",800);//活3连，110
       map.put("01011",1000);//活3连，130
       map.put("02022",800);//活3连，110

       map.put("01111",3000);//4连，300
       map.put("02222",3500);//4连，280
   }

   //找到连接的棋子数(估价函数)
   public int getValue(int x, int y, int dir, int type) {
       int sum1 = 0, sum2 = 0;
       String weight = "";
       int value = 0;
       int y1 = y, x1 = x;
       switch (dir) {
           case 0:
               //两半查找    横  左右
               for (int i = 4; i < 8; i++) {
                   for (int j = -i+1, k = 0; k < 4; j++, k++){
                       if (x1+j >= 0 && x1+j <= 14){
                           if(j == 0)
                               weight += type +'0';
                           else
                               weight += chess[y][x1+j] + '0';
                       }
                   }
                   if(map.get(weight) != null){
                       value += map.get(weight);
                   }
               }
               break;
           case 1:
               //两半查找   竖 上下
               for (int i = 4; i < 8; i++) {
                   for (int j = -i+1, k = 0; k < 4; j++, k++){
                       if (y1+j >= 0 && y1+j <= 14){
                           if(j == 0)
                               weight += type +'0';
                           else
                               weight += chess[y1+j][x] + '0';
                       }
                   }
                   if(map.get(weight) != null){
                       value += map.get(weight);
                   }
               }
               break;
           case 2:
               //两半查找   撇
               for (int i = 4; i < 8; i++) {
                   for (int j = -i+1, k = 0; k < 4; j++, k++){
                       if (y1+j >= 0 && y1+j <= 14 && x1+j >= 0 && x1+j <= 14){
                           if(j == 0)
                               weight += type +'0';
                           else
                               weight += chess[y1+j][x] + '0';
                       }
                   }
                   if(map.get(weight) != null){
                       value += map.get(weight);
                   }
               }
               break;
           case 3:
               //两半查找   捺
               for (int i = 4; i < 8; i++) {
                   for (int j = -i+1, k = 0; k < 4; j++, k++){
                       if (y1-j >= 0 && y1-j <= 14 && x1+j >= 0 && x1+j <= 14){
                           if(j == 0)
                               weight += type +'0';
                           else
                               weight += chess[y1-j][x] + '0';
                       }
                   }
                   if(map.get(weight) != null){
                       value += map.get(weight);
                   }
               }
               break;
       }
       return value;

   }

   //电脑自由下棋
   public void randomChess2(int type) {

       int computer[][] = new int[15][15];
       int max = 0;
       for (int i = 0; i < 15; i++) {
           for (int j = 0; j < 15; j++) {
               if (this.chess[j][i] == 0) {
                   for (int k = 0; k < 4; k++) {
                       computer[j][i] += this.getValue(i, j, k, type);
                   }

                   if (max < computer[j][i]) {
                       max = computer[j][i];
                       x = j;
                       y = i;
                   }
               }

           }
       }

       //贪心决策
       //都为零自动下子
       if (max == 0) {
           int x = (int) (Math.random() * 100) % 15;
           int y = (int) (Math.random() * 100) % 15;
           chess[y][x] = type;
           this.x = x;
           this.y = y;
       }
       else {
           chess[y][x] = type;
       }


   }
   
   
   public void computerChess(int type) {
   	HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
   	hashMap.put("10000", 15);//眠1连
   	hashMap.put("20000", 10);//眠1连
   	
   	hashMap.put("20100",17);//眠1连，15
   	hashMap.put("10200",12);//眠1连，10
   	hashMap.put("21000",15);//眠1连，15
   	hashMap.put("12000",10);//眠1连，10
   	hashMap.put("20010",19);//眠1连，15
   	hashMap.put("10020",14);//眠1连，10
   	hashMap.put("20100",17);//眠1连，15
   	hashMap.put("10200",12);//眠1连，10
   //
//   	hashMap.put("00010",21);//活1连，15
//   	hashMap.put("00020",16);//活1连，10
//   	hashMap.put("00100",19);//活1连，15
//   	hashMap.put("00200",14);//活1连，10
//   	hashMap.put("01000",17);//活1连，15
//   	hashMap.put("02000",12);//活1连，10
   //
   	//被堵住
   	hashMap.put("10100",65);//眠2连，40
   	hashMap.put("20200",60);//眠2连，30
   	hashMap.put("01100",65);//眠2连，40
   	hashMap.put("02200",60);//眠2连，30
   	hashMap.put("11000",65);//眠2连，40
   	hashMap.put("22000",60);//眠2连，30
   	
   	hashMap.put("21010",65);//眠2连，40
   	hashMap.put("12020",60);//眠2连，30
   	hashMap.put("20110",65);//眠2连，40
   	hashMap.put("10220",60);//眠2连，30
   	hashMap.put("21100",65);//眠2连，40
   	hashMap.put("12200",60);//眠2连，30

//   	hashMap.put("01010",75);//活2连，40
//   	hashMap.put("02020",70);//活2连，30
//   	hashMap.put("00110",75);//活2连，40
//   	hashMap.put("00220",70);//活2连，30
//   	hashMap.put("01100",75);//活2连，40
//   	hashMap.put("02200",70);//活2连，30
//   	hashMap.put("11000",75);//活2连，40
//   	hashMap.put("00022",70);//活2连，30
   //	
//   	//被堵住
   	hashMap.put("11100",150);//眠3连，100
   	hashMap.put("22200",140);//眠3连，80
   	hashMap.put("21110",150);//眠3连，100
   	hashMap.put("12220",140);//眠3连，80
   //	
//   	hashMap.put("10110",1000);//活3连，130
//   	hashMap.put("20220",800);//活3连，110
//   	hashMap.put("11010",1000);//活3连，130
//   	hashMap.put("22020",800);//活3连，110
//   	hashMap.put("01110", 1000);//活3连
//   	hashMap.put("02220", 800);//活3连
   	
   	hashMap.put("11110",3000);//4连，300
   	hashMap.put("11112",3000);//4连，300
   	hashMap.put("22220",3500);//4连，280
   	hashMap.put("22221",3500);//4连，280


   	int a;
   	int b;
   	Color c=Color.black;
   	int line=15;
   	qizi qizilarry[][]=new qizi[line][line];
   	int chessValue[][]=new int[line][line];//权值表
   	String zuo="";
   	String you="";
   	String shang="";
   	String xia="";
   	String zuoshang="";
   	String zuoxia="";
   	String youshang="";
   	String youxia="";
   	for(int y=0;y<line;y++) {
   		for(int x=0;x<line;x++) {
   			if(qizilarry[x][y]==null) {
   				//向左
   				a=x;
   				for(int i=1;i<6;i++) {
   					a--;
   					if(a<0)
   						break;
   					if(qizilarry[a][y]!=null) {
   						if(qizilarry[a][y].getColor()==c) {
   							zuo+='2';
   						}else
   							zuo+='1';
   					}else
   						zuo+=0;
   				}
   				Integer integer=hashMap.get(zuo);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;

   				//向右
   				a=x;
   				for(int i=1;i<6;i++) {
   					a++;
   					if(a==line)
   						break;
   					if(qizilarry[a][y]!=null) {
   						if(qizilarry[a][y].getColor()==c) {
   							you+='2';
   						}else
   							you+='1';
   					}else
   						you+=0;
   				}
   				integer=hashMap.get(you);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				
   				//向上
   				b=y;
   				for(int i=1;i<6;i++) {
   					b--;
   					if(b<0)
   						break;
   					if(qizilarry[x][b]!=null) {
   						if(qizilarry[x][b].getColor()==c) {
   							shang+='2';
   						}else
   							shang+='1';
   					}else
   						shang+=0;
   				}
   				integer=hashMap.get(shang);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				
   				//向下
   				b=y;
   				for(int i=1;i<6;i++) {
   					b++;
   					if(b==line)
   						break;
   					if(qizilarry[x][b]!=null) {
   						if(qizilarry[x][b].getColor()==c) {
   							xia+='2';
   						}else
   							xia+='1';
   					}else
   						xia+=0;
   				}
   				integer=hashMap.get(xia);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				
   				//向左上
   				a=x;
   				b=y;
   				for(int i=1;i<6;i++) {
   					a--;
   					b--;
   					if(a<0||b<0)
   						break;
   					if(qizilarry[a][b]!=null) {
   						if(qizilarry[a][b].getColor()==c) {
   							zuoshang+='2';
   						}else
   							zuoshang+='1';
   					}else
   						zuoshang+=0;
   				}
   				integer=hashMap.get(zuoshang);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				
   				//向右下
   				a=x;
   				b=y;
   				for(int i=1;i<6;i++) {
   					a++;
   					b++;
   					if(a==line||b==line)
   						break;
   					if(qizilarry[a][b]!=null) {
   						if(qizilarry[a][b].getColor()==c) {
   							youxia+='2';
   						}else
   							youxia+='1';
   					}else
   						youxia+=0;
   				}
   				integer=hashMap.get(youxia);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				
   				//向左下
   				a=x;
   				b=y;
   				for(int i=1;i<6;i++) {
   					a--;
   					b++;
   					if(a<0||b==line)
   						break;
   					if(qizilarry[a][b]!=null) {
   						if(qizilarry[a][b].getColor()==c) {
   							zuoxia+='2';
   						}else
   							zuoxia+='1';
   					}else
   						zuoxia+=0;
   				}
   				integer=hashMap.get(zuoxia);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				
   				//向右上
   				a=x;
   				b=y;
   				for(int i=1;i<6;i++) {
   					a++;
   					b--;
   					if(a==line||b<0)
   						break;
   					if(qizilarry[a][b]!=null) {
   						if(qizilarry[a][b].getColor()==c) {
   							youshang+='2';
   						}else
   							youshang+='1';
   					}else
   						youshang+=0;
   				}
   				integer=hashMap.get(youshang);
   				if(integer!=null) 
   					chessValue[x][y]+=integer;
   				zuo="";
   				you="";
   				shang="";
   				xia="";
   				zuoshang="";
   				zuoxia="";
   				youshang="";
   				youxia="";
   			}
   		}
   	}
   }
   
}
