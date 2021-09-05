package wuziqi2;

import java.awt.Graphics;

public interface My_Pan {

	public void randomChess(int type);
	public void clearChess();
	public boolean judge(int x,int y,int type);
	public void paint(Graphics g);
}
