package com.example.speedshooter.wordcloud;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by SpeedShooter on 2016/10/16.
 */
public class WordCloud extends Thread{

    private Activity main;
    private ImageView img;
    private Button button;
    private int MaxW = 0;
    private int MaxH = 0;
    private ArrayList<WordCloud_StringInfo> Words = new ArrayList<WordCloud_StringInfo>();
    private Rect org = new Rect();
    private int dot_local = 0;
    private Point initial_dot = new Point();
    private Point dot = new Point();

    private int loop_step = 1;

    public WordCloud(ArrayList<WordCloud_StringInfo> Words, int MaxW, int MaxH, ImageView img, Button button, Activity main){

        this.main = main;
        this.img = img;
        this.button = button;
        this.MaxW = MaxW;
        this.MaxH = MaxH;
        this.Words = Words;

        Words.get(0).setLocation(((MaxW - Words.get(0).getWidth()) / 2),
                ((MaxH - Words.get(0).getHeight()) / 2));
        org = new Rect(Words.get(0).X() - Words.get(1).getWidth(),
                Words.get(0).Y() - Words.get(1).getHeight(),
                Words.get(0).X() + Words.get(0).getWidth(),
                Words.get(0).Y() + Words.get(0).getHeight()
        );

    }

    public ArrayList<WordCloud_StringInfo> getWords(){
        return Words;
    }

    @Override
    public void run() {
        super.run();

        Paint paint = new Paint();
        paint.setTextSize(Words.get(0).getSize());
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create("Serif", Typeface.BOLD));

        for(int i = 1; i < Words.size(); i++){
            Boolean isRun = true;
            do{
                if(( (dot.x == Rect_getPoint(org, 0, org_add())) &&
                        (dot.y == Rect_getPoint(org, 1, org_add())) ) &&
                        (loop_step != 5) ){
                    loop_step++;					//arrival next border
                }
                else if(((dot.x == initial_dot.x) && (dot.y == initial_dot.y)) &&
                        (loop_step == 5)){
                    if(!isOutOfRange(org, MaxW, MaxH, Words.get(i))){
                        loop_step = 1;				//all border have been a tour, set new border
                        //set new border
                        org.set(Expand(org, 2));
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
                            dot.set(dot.x + 1, dot.y);
                            break;
                        case 2:
                            dot.set(dot.x - 1, dot.y);
                            break;
                        case 1:
                            dot.set(dot.x, dot.y + 1);
                            break;
                        case 3:
                            dot.set(dot.x, dot.y - 1);
                            break;
                        default:
                            break;
                    }
                    Words.get(i).setLocation(dot.x, dot.y);
                    Boolean Collision = false;
                    for(int j = 0; j < i; j++){
                        if(WordCloud_StringInfo.isCollision(Words.get(i), Words.get(j))){
                            Collision = true;
                        }
                    }
                    if(!Collision){
                        paint.setTextSize(Words.get(i).getSize());
                        final WordCloud_Draw wcd = new WordCloud_Draw(img);
                        wcd.setWords(Words, i);
                        main.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageBitmap(wcd.onDrawImg());
                            }
                        });
                        isRun = false;
                    }
                }
            }while(isRun);
            if((i + 1) < Words.size()){
                dot_reset(i + 1);
            }
        }
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setEnabled(true);
                button.setText("SHOW");
            }
        });
    }

    public int org_add(){
        return ((dot_local + loop_step) % 4);
    }

    public void dot_reset(int new_Rect){
        org = new Rect(Words.get(0).X() - Words.get(new_Rect).getWidth(),
                        Words.get(0).Y() - Words.get(new_Rect).getHeight(),
                        Words.get(0).X() + Words.get(0).getWidth(),
                        Words.get(0).Y() + Words.get(0).getHeight()
                    );
        init_dot_set();
        loop_step = 1;
    }

    public void init_dot_set(){
        //set initial choose dot value
        dot_local = (int)(Math.random() * 4);
        if((dot_local == 0) || (dot_local == 2)){
            dot.set((int) (Math.random() * (Rect_getPoint(org, 0, 1) - Rect_getPoint(org, 0, 0) + 1)) + Rect_getPoint(org, 0, 0),
                    Rect_getPoint(org, 1, dot_local));
        }
        else if((dot_local == 1) || (dot_local == 3)){
            dot.set(Rect_getPoint(org, 0, dot_local),
                    (int)(Math.random() * (Rect_getPoint(org, 1, 3) - Rect_getPoint(org, 1, 0) + 1)) + Rect_getPoint(org, 1, 0));
        }
        initial_dot = new Point(dot.x, dot.y);
    }

    public Rect Expand(Rect rect, int value){
        Rect r = new Rect();
        r.set(rect.left - value, rect.top - value, rect.right + value, rect.bottom + value);
        return r;
    }

    public Boolean isOutOfRange(Rect rect, int width, int height, WordCloud_StringInfo word){
        Boolean result = false;
        if(rect.left < 0 || rect.top < 0){
            result = true;
        }
        if(rect.left + rect.width() + word.getWidth() >= width || rect.top < 0){
            result = true;
        }
        if(rect.right + word.getWidth() >= width || rect.bottom + word.getHeight() >= height){
            result = true;
        }
        if(rect.left < 0 || rect.top + rect.height() >= height){
            result = true;
        }
        return result;
    }

    public int Rect_getPoint(Rect rect, int xy, int value){
        Point P = new Point(0, 0);
        int result = 0;
        switch (value){
            case 0:
                P = new Point(rect.left, rect.top);
                break;
            case 1:
                P = new Point(rect.left + rect.width(), rect.top);
                break;
            case 2:
                P = new Point(rect.right, rect.bottom);
                break;
            case 3:
                P = new Point(rect.left, rect.top + rect.height());
                break;
        }
        if(xy == 0){
            result = P.x;
        }
        else{
            result = P.y;
        }
        return result;
    }
}
