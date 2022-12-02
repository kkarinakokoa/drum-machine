package edu.skku.kimjuyeob.drummachine2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Lever extends Button {
    int my_num, my_inst;
    boolean chosen;
    public Lever(int instno, int num, Context context) {
        super(context);
        my_num=num;
        my_inst=instno;
        chosen=false;
        switch (my_num%16) {
            case 0:
                this.setText(Integer.toString(my_num/16+1)+"."+Integer.toString(my_num%16+1));
                break;
            default:
                this.setText("."+Integer.toString(my_num%16+1));
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen=!chosen;
                MainActivity.updated(my_inst,my_num,chosen);
                if (chosen) bg_white();
                else bg_grey();
            }
        });
    }

    public Lever(int instno, int num, Context context, AttributeSet attrs) {
        super(context, attrs);
        my_num=num;
        my_inst=instno;
        chosen=false;
        switch (my_num%16) {
            case 0:
                this.setText(Integer.toString(my_num/16+1)+"."+Integer.toString(my_num%16+1));
                break;
                default:
                    this.setText("."+Integer.toString(my_num%16+1));
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen=!chosen;
                MainActivity.updated(my_inst,my_num,chosen);
                if (chosen) bg_white();
                else bg_grey();
            }
        });
    }

    public Lever(int instno, int num, Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        my_num=num;
        my_inst=instno;
        chosen=false;
        switch (my_num%16) {
            case 0:
                this.setText(Integer.toString(my_num/16+1)+"."+Integer.toString(my_num%16+1));
                break;
            default:
                this.setText("."+Integer.toString(my_num%16+1));
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen=!chosen;
                MainActivity.updated(my_inst,my_num,chosen);
                if (chosen) bg_white();
                else bg_grey();
            }
        });
    }

    public void bg_white() {
        switch (my_num%16) {
            case 0:
                this.setBackgroundColor(Color.rgb(255,0,0));
                break;
            case 1:
                this.setBackgroundColor(Color.rgb(220,0,0));
                break;
            case 2:
                this.setBackgroundColor(Color.rgb(190,0,0));
                break;
            case 3:
                this.setBackgroundColor(Color.rgb(160,0,0));
                break;

            case 4:
                this.setBackgroundColor(Color.rgb(255,255,0));
                break;
            case 5:
                this.setBackgroundColor(Color.rgb(220,220,0));
                break;
            case 6:
                this.setBackgroundColor(Color.rgb(190,190,0));
                break;
            case 7:
                this.setBackgroundColor(Color.rgb(160,160,0));
                break;

            case 8:
                this.setBackgroundColor(Color.rgb(0,255,0));
                break;
            case 9:
                this.setBackgroundColor(Color.rgb(0,220,0));
                break;
            case 10:
                this.setBackgroundColor(Color.rgb(0,190,0));
                break;
            case 11:
                this.setBackgroundColor(Color.rgb(0,160,0));
                break;

            case 12:
                this.setBackgroundColor(Color.rgb(255,0,120));
                break;
            case 13:
                this.setBackgroundColor(Color.rgb(240,0,105));
                break;
            case 14:
                this.setBackgroundColor(Color.rgb(190,0,60));
                break;
            case 15:
                this.setBackgroundColor(Color.rgb(160,0,40));
                break;
        }
    }
    public void bg_grey() {
         this.setBackgroundColor(Color.rgb(20,20,20));
    }
}
