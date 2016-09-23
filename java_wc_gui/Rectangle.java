
public class Rectangle {

	private Point[] P = new Point[4];
	
	public Rectangle(){
		P[0] = new Point();
		P[1] = new Point();
		P[2] = new Point();
		P[3] = new Point();
	}
	
	public Rectangle(Point P, int width, int height){
		this.P[0] = new Point(P.X(), P.Y());					//P1
		this.P[1] = new Point(P.X() + width, P.Y());			//P2
		this.P[2] = new Point(P.X() + width, P.Y() + height);	//P3
		this.P[3] = new Point(P.X(), P.Y() + height);			//P4
	}
	
	public Rectangle(int X1, int Y1, int X2, int Y2, int X3, int Y3, int X4, int Y4){
		P[0] = new Point(X1, Y1);
		P[1] = new Point(X2, Y2);
		P[2] = new Point(X3, Y3);
		P[3] = new Point(X4, Y4);
	}
	
	public Rectangle(Point P1, Point P2, Point P3, Point P4){
		P[0] = P1;
		P[1] = P2;
		P[2] = P3;
		P[3] = P4;
	}
	
	public Point getPoint(int value){
		return P[value];
	}
	
	public Point P1(){
		return P[0];
	}
	
	public Point P2(){
		return P[1];
	}
	
	public Point P3(){
		return P[2];
	}
	
	public Point P4(){
		return P[3];
	}
	
	public void P1(Point P){
		this.P[0] = P;
	}
	
	public void P2(Point P){
		this.P[1] = P;
	}
	
	public void P3(Point P){
		this.P[2] = P;
	}
	
	public void P4(Point P){
		this.P[3] = P;
	}
	
	public void P1(int X, int Y){
		Point P = new Point(X, Y);
		this.P[0] = P;
	}
	
	public void P2(int X, int Y){
		Point P = new Point(X, Y);
		this.P[1] = P;
	}
	
	public void P3(int X, int Y){
		Point P = new Point(X, Y);
		this.P[2] = P;
	}
	
	public void P4(int X, int Y){
		Point P = new Point(X, Y);
		this.P[3] = P;
	}
	
	public void Expand(int value){
		P[0].X(P[0].X() - value);
		P[0].Y(P[0].Y() - value);
		P[1].X(P[1].X() + value);
		P[1].Y(P[1].Y() - value);
		P[2].X(P[2].X() + value);
		P[2].Y(P[2].Y() + value);
		P[3].X(P[3].X() - value);
		P[3].Y(P[3].Y() + value);
	}
	
	public Boolean isOutOfRange(int width, int height){
		Boolean result = false;
		if(P[0].X() < 0 || P[0].Y() < 0){
			result = true;
		}
		if(P[1].X() >= width || P[1].Y() < 0){
			result = true;
		}
		if(P[2].X() >= width || P[2].Y() >= height){
			result = true;
		}
		if(P[3].X() < 0 || P[3].Y() >= height){
			result = true;
		}
		return result;
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
