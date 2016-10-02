package wc_gui;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;

public class StringInfo {
	
	private String Word;
	private int Size;
	private int Width;
	private int Height;
	private int CenterLocation;
	private Point[] P = new Point[4];
	
	public StringInfo(){
		Word = "";
		Size = 0;
		Width = 0;
        Height = 0;
	}
	
	public StringInfo(String Word, int Size){
		this.Word = Word;
		this.Size = Size;
		
		//set word's width and height
		JLabel lab = new JLabel();
		lab.setFont(new Font("Serif", Font.BOLD, Size));
		FontMetrics fonM = lab.getFontMetrics(lab.getFont());
		CenterLocation = fonM.getDescent();
		Height = fonM.getAscent()/* - fonM.getDescent()*/;
        Width = fonM.stringWidth(Word);
	}

	public String getWord(){
		return Word;
	}
	
	public int getSize(){
		return Size;
	}
	
	public int getWidth(){
		return Width;
	}
	
	public int getHeight(){
		return Height;
	}
	
	public int getCenterLocation(){
		return CenterLocation;
	}
	
	public void setLocation(int X, int Y){
		P[0] = new Point(X, Y);						//P1
		P[1] = new Point(X + Width, Y);				//P2
		P[2] = new Point(X + Width, Y + Height);	//P3
		P[3] = new Point(X, Y + Height);			//P4
	}
	
	public void setLocation(Point P){
		this.P[0] = P;
		this.P[1] = new Point(P.X() + Width, P.Y());
		this.P[2] = new Point(P.X() + Width, P.Y() + Height);
		this.P[3] = new Point(P.X(), P.Y() + Height);
	}
	
	public Point getLocation(){
		return P[0];
	}
	
	public int X(){
		return P[0].X();
	}
	
	public int Y(){
		return P[0].Y();
	}

	public static Boolean isCollision(StringInfo ObjectA, StringInfo ObjectB){
		Boolean result = false;
		int X1, Y1;
		int X2, Y2;
		int Abs1, Abs2;
		int Wvalue, Hvalue;
		X1 = ObjectA.X() + (ObjectA.getWidth() / 2);
		Y1 = ObjectA.Y() + (ObjectA.getHeight() / 2);
		X2 = ObjectB.X() + (ObjectB.getWidth() / 2);
		Y2 = ObjectB.Y() + (ObjectB.getHeight() / 2);
		Abs1 = Math.abs(X1 - X2);
		Abs2 = Math.abs(Y1 - Y2);
		Wvalue = (ObjectA.getWidth() / 2) + (ObjectB.getWidth() / 2);
		Hvalue = (ObjectA.getHeight() / 2) + (ObjectB.getHeight() / 2);
		if((Abs1 < Wvalue) && (Abs2 < Hvalue)){
			result = true;
		}
		return result;
	}
	
}
