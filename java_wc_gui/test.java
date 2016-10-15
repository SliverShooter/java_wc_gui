package wc_gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class test {

	private static String[] inputword = {
		"10", "����", "8", "�ݨ�", "7", "�ڪ�", "6", "�ܤl", "6", "�F�@",
		"5", "�L��", "5", "�L��", "5", "�_��", "5", "���I�v", "5", "�ש�",
		"4", "�ۤv", "4", "����", "4", "����", "4", "�K�D", "4", "�@��",
		"4", "�@�|", "4", "����", "4", "���n", "4", "�L���I�v", "4", "�L�ש�",
		"4", "���", "4", "�ڬ�", "4", "����", "4", "�۵M", "3", "���F",
		"3", "����", "3", "�e��", "3", "�L�K�D", "3", "�����x", "3", "�¥�"
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
