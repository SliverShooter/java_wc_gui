package com.example.speedshooter.wordcloud;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String[] inputword = {
            "10", "父親", "8", "看見", "7", "我的", "6", "桔子", "6", "了一",
            "5", "他們", "5", "他的", "5", "北京", "5", "的背影", "5", "終於",
            "4", "自己", "4", "茶房", "4", "那邊", "4", "鐵道", "4", "一日",
            "4", "一會", "4", "不能", "4", "不要", "4", "他的背影", "4", "他終於",
            "4", "喪事", "4", "我看", "4", "給我", "4", "自然", "3", "走了",
            "3", "走到", "3", "送我", "3", "過鐵道", "3", "那邊月台", "3", "黑布"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> InitWords = new ArrayList<String>();
        for(int i = 0; i < inputword.length; i++){
            InitWords.add(inputword[i]);
        }

        ArrayList<WordCloud_StringInfo> words = new ArrayList<WordCloud_StringInfo>();
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
                WordCloud_StringInfo inputword = new WordCloud_StringInfo(InitWords.get(i), countTosize.intValue(), 4);
                words.add(inputword);
            }
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int MaxW = metrics.widthPixels;
        int MaxH = metrics.heightPixels;

        WordCloud wc = new WordCloud(this, words, MaxW, MaxH);
        wc.init_dot_set();
        setContentView(wc);
    }
}
