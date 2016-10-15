package wc_gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class test {

	private static String[] inputword = {
		"10", "父親", "8", "看見", "7", "我的", "6", "桔子", "6", "了一",
		"5", "他們", "5", "他的", "5", "北京", "5", "的背影", "5", "終於",
		"4", "自己", "4", "茶房", "4", "那邊", "4", "鐵道", "4", "一日",
		"4", "一會", "4", "不能", "4", "不要", "4", "他的背影", "4", "他終於",
		"4", "喪事", "4", "我看", "4", "給我", "4", "自然", "3", "走了",
		"3", "走到", "3", "送我", "3", "過鐵道", "3", "那邊月台", "3", "黑布"
		};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("123");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true); //not outer frame
		frame.setLocation(300, 150);
		frame.setSize(600, 600);
		frame.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.setVisible(true);
		
		
		ArrayList<String> InitWords = new ArrayList<String>();
		for(int i = 0; i < inputword.length; i++){
			InitWords.add(inputword[i]);
		}
		
		ArrayList<StringInfo> words = new ArrayList<StringInfo>();
		int maxcount = 0;
		for(int i = 0; i < InitWords.size(); i++){
			int wordcount = 0;
			if(i % 2 == 0){
				wordcount = Integer.parseInt(InitWords.get(i));
				if(wordcount > maxcount){
					maxcount = wordcount;
				}
			}
		}
		int wordcount = 0;
		for(int i = 0; i < InitWords.size(); i++){
			if(i % 2 == 0){
				wordcount = Integer.parseInt(InitWords.get(i));
			}
			else{
				Double countTosize =  Math.pow(((double)wordcount / (double)maxcount), 1.25) * 86;
				StringInfo inputword = new StringInfo(InitWords.get(i), countTosize.intValue(), 4);
				words.add(inputword);
			}
		}
		
		gui_loop guiloop = new gui_loop(frame, words);
		guiloop.init_dot_set();
		guiloop.run();
	}

}
