package wc_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class gui_loop implements Runnable{
	
	private JFrame mainFrame;
	private Graphics g;
	
	//words info
	private ArrayList<StringInfo> Words = new ArrayList<StringInfo>();
	
	//initial value
	/*private Point P = new Point(270, 290);
	private int width = 60;
	private int height = 20;*/
	
	//loop bound value
	private Rectangle org = new Rectangle();
	
	//choose dot
	private int dot_local = 0;
	private Point initial_dot = new Point();
	private Point dot = new Point();
	
	private int loop_step = 1;
	
	public gui_loop(JFrame frame, ArrayList<StringInfo> words){
		mainFrame = frame;
		g = mainFrame.getGraphics();
		g.setColor(Color.BLACK);
		
		//set all words info
		Words = words;
		
		//set initial value
		Words.get(0).setLocation(((mainFrame.getWidth() - Words.get(0).getWidth()) / 2),
				((mainFrame.getHeight() - Words.get(0).getHeight()) / 2));
		
		//set loop bound value
		org = new Rectangle(
				new Point(
						Words.get(0).X() - Words.get(1).getWidth(),
						Words.get(0).Y() - Words.get(1).getHeight()
					),
				Words.get(0).getWidth() + Words.get(1).getWidth(),
				Words.get(0).getHeight() + Words.get(1).getHeight()
			);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		g.setFont(new Font("Serif", Font.BOLD, Words.get(0).getSize()));
		g.drawString(
				Words.get(0).getWord(),
				Words.get(0).X(),
				Words.get(0).Y() + Words.get(0).getHeight() - Words.get(0).getCenterLocation()
			);
		/*g.drawRect(
				Words.get(0).X(),
				Words.get(0).Y(),
				Words.get(0).getWidth(),
				Words.get(0).getHeight()
			);*/
		for(int i = 1; i < Words.size(); i++){
			Boolean isRun = true;
			do{
				if(((dot.X() == org.getPoint(org_add()).X()) && (dot.Y() == org.getPoint(org_add()).Y())) &&
						(loop_step != 5)){
					loop_step++;					//arrival next border
				}
				else if(((dot.X() == initial_dot.X()) && (dot.Y() == initial_dot.Y())) &&
						(loop_step == 5)){
					if(org.isOutOfRange(mainFrame.getWidth(), mainFrame.getHeight()) == false){
						loop_step = 1;				//all border have been a tour, set new border
						//set new border
						org.Expand(1);
						//reset choose dot
						init_dot_set();
					}
					else{
						isRun = false;
					}
				}
				else{
					switch((dot_local + loop_step - 1) % 4){
						case 0:
							//g.drawLine(dot.X(), dot.Y(), dot.X() + 1, dot.Y());
							dot.X(dot.X() + 1);
							break;
						case 2:
							//g.drawLine(dot.X(), dot.Y(), dot.X() - 1, dot.Y());
							dot.X(dot.X() - 1);
							break;
						case 1:
							//g.drawLine(dot.X(), dot.Y(), dot.X(), dot.Y() + 1);
							dot.Y(dot.Y() + 1);
							break;
						case 3:
							//g.drawLine(dot.X(), dot.Y(), dot.X(), dot.Y() - 1);
							dot.Y(dot.Y() - 1);
							break;
						default:
							break;
					}
					Words.get(i).setLocation(dot.X(), dot.Y());
				}
				Boolean Collision = false;
				for(int j = 0; j < i; j++){
					if(StringInfo.isCollision(Words.get(i), Words.get(j)) == true){
						Collision = true;
					}
				}
				if(Collision == false){
					g.setFont(new Font("Serif", Font.BOLD, Words.get(i).getSize()));
					g.drawString(
							Words.get(i).getWord(),
							Words.get(i).X(),
							Words.get(i).Y() + Words.get(i).getHeight() - Words.get(i).getCenterLocation()
						);
					/*g.drawRect(
							Words.get(i).X(),
							Words.get(i).Y(),
							Words.get(i).getWidth(),
							Words.get(i).getHeight()
						);*/
					isRun = false;
				}
			}while(isRun);
			if((i + 1) < Words.size()){
				dot_reset(i + 1);
			}
		}
	}
	
	//get now loop border
	public int org_add(){
		return ((dot_local + loop_step) % 4);
	}
	
	public void dot_reset(int new_Rect){
		org = new Rectangle(
				new Point(
						Words.get(0).X() - Words.get(new_Rect).getWidth(),
						Words.get(0).Y() - Words.get(new_Rect).getHeight()
					),
				Words.get(0).getWidth() + Words.get(new_Rect).getWidth(),
				Words.get(0).getHeight() + Words.get(new_Rect).getHeight()
			);
		init_dot_set();
		loop_step = 1;
	}
	
	public void init_dot_set(){
		//set initial choose dot value
		dot_local = (int)(Math.random() * 4);
		if((dot_local == 0) || (dot_local == 2)){
			dot.X((int)(Math.random() * (org.getPoint(1).X() - org.getPoint(0).X() + 1)) + org.getPoint(0).X());
			dot.Y(org.getPoint(dot_local).Y());
		}
		else if((dot_local == 1) || (dot_local == 3)){
			dot.X(org.getPoint(dot_local).X());
			dot.Y((int)(Math.random() * (org.getPoint(3).Y() - org.getPoint(0).Y() + 1)) + org.getPoint(0).Y());
		}
		initial_dot = new Point(dot.X(), dot.Y());
	}
}
