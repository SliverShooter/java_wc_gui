package wc_gui;

public class Point {
	
	private int X;
	private int Y;

	public Point(){
		this.X = 0;
		this.Y = 0;
	}
	
	public Point(int X, int Y){
		this.X = X;
		this.Y = Y;
	}
	
	public int X(){
		return X;
	}
	
	public int Y(){
		return Y;
	}
	
	public void X(int X){
		this.X = X;
	}
	
	public void Y(int Y){
		this.Y = Y;
	}
	
}
