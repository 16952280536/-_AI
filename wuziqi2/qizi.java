package wuziqi2;

import java.awt.Color;
import java.awt.Graphics;

public class qizi implements CS{
	Graphics g;
	Color color;
	int x;
	int y;
	
	public qizi(Graphics g,Color color,int x,int y) {
		this.g=g;
		this.color=color;
		this.x=x;
		this.y=y;
	}
	
	//获取x坐标
	public int getX() {
		return x;
	}
	
	//获取y坐标
	public int getY() {
		return y;
	}
	
	//获取颜色
	public Color getColor() {
		return color;
	}
	
	public void drawq(){

		g.setColor(color);
		g.fillOval(x0+x*size-size/2, y0+y*size-size/2, size, size);

	}

}
