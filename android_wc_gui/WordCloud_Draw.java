package com.example.speedshooter.wordcloud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by SpeedShooter on 2016/10/19.
 */
public class WordCloud_Draw{

    private ArrayList<WordCloud_StringInfo> Words = new ArrayList<WordCloud_StringInfo>();
    private ImageView img;
    private int MaxWord;

    public WordCloud_Draw(ImageView img) {
        this.img = img;
    }

    public void setWords(ArrayList<WordCloud_StringInfo> Words, int MaxWord){
        this.Words = Words;
        this.MaxWord = MaxWord;
    }

    public Bitmap onDrawImg() {

        Bitmap bitmap = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create("Serif", Typeface.BOLD));

        for(int i = 0; i < MaxWord; i++){
            paint.setTextSize(Words.get(i).getSize());
            canvas.drawText(Words.get(i).getWord(),
                    Words.get(i).X() + (Words.get(i).getPadding() / 2),
                    Words.get(i).Y() + Words.get(i).getHeight()
                            - Words.get(i).getCenterLocation() + (Words.get(i).getPadding() / 4),
                    paint);
        }

        return bitmap;

    }
}
