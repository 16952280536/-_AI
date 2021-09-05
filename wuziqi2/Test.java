package wuziqi2;

import java.util.Scanner;

public class Test {

	public static void main(String []args) {
		int i = 1;
    	System.out.println("Input:");
    	Scanner input = new Scanner(System.in);
    	int XuanZe = input.nextInt();
    	int using = 0;
    	double r = Math.random();
    	System.out.println(r);
    	if(XuanZe == 12) {
    	    if(r<0.5) using = 1;
    	    else using = 2; }
    	else if(XuanZe == 13) {
        	if(r<0.5) using = 1;
        	else using = 3; }
    	else if(XuanZe == 23) {
        	if(r<0.5) using = 2;
        	else using = 3; }
    	else System.out.println("输入错误！");
        Main_frame my=new Main_frame(XuanZe,using);
        Thread thread=new Thread(my);
        thread.start();
    }
}
