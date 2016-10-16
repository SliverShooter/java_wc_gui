package com.example.speedshooter.wordcloud;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

/**
 * Created by SpeedShooter on 2016/10/15.
 */
public class WordCloud_StringInfo{

    private String Word = "";
    private int Size = 0;
    private Rect rect = new Rect(0, 0, 0, 0);
    private int Height = 0;
    private int Width = 0;
    private int CenterLocation = 0;
    private int padding = 0;

    public WordCloud_StringInfo(){
        Word = "";
        Size = 0;
        Width = 0;
        Height = 0;
    }

    public WordCloud_StringInfo(String Word, int Size, int padding){
        this.Word = Word;
        this.Size = Size;
        this.padding = padding;

        TextPaint paint = new TextPaint();
        paint.setTextSize(Size);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create("Serif", Typeface.BOLD));
        Paint.FontMetrics fonM = paint.getFontMetrics();

        Height = (int)Math.abs(fonM.top) + (int)Math.abs(fonM.bottom) + (2 * padding);
        Width = (int)paint.measureText(Word) + padding;
        CenterLocation = (int)fonM.descent + padding;
    }

    public String getWord(){
        return Word;
    }

    public int getSize(){
        return Size;
    }

    public int getPadding(){
        return padding;
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
        rect.set(X, Y, X + Width, Y + Height);
    }

    public void setLocation(Point P){
        rect.set(P.x, P.y, P.x + Width, P.y + Height);
    }

    public Point getLocation(){
        return new Point(rect.left, rect.top);
    }

    public int X(){
        return rect.left;
    }

    public int Y(){
        return rect.top;
    }

    public static Boolean isCollision(WordCloud_StringInfo ObjectA, WordCloud_StringInfo ObjectB){
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
