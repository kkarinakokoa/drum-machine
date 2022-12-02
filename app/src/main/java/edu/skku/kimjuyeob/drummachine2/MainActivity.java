package edu.skku.kimjuyeob.drummachine2;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private View decorView;
    private int uiOption;

    Button playbtn, upbtn, downbtn;
    TextView sizetxt;
    LinearLayout[] ll;

    int period; int tempo; boolean isplaying; int curr;

    float mScale;

    static ArrayList<Boolean>[] seq;
    ArrayList<Lever>[] btns;

    SoundPool s;
    int samples[];

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        mScale = getResources().getDisplayMetrics().density;

        samples=new int[6];
        s=new SoundPool(6, AudioManager.STREAM_MUSIC,0);

        tempo=120;

        loadsamples(1);

        period=1;
        curr=0;
        isplaying=false;

        seq=new ArrayList[6]; btns=new ArrayList[6];
        for(int i=0; i<6; i++) {
            seq[i] = new ArrayList<>();
            btns[i] = new ArrayList<>();
            for(int j=0; j<period*16; j++) {
                seq[i].add(false);
                Lever lever=new Lever(i, j,MainActivity.this);
                lever.setMinimumWidth((int)(5*mScale));
                lever.setWidth((int)(35*mScale));
                btns[i].add(lever);
            }
        }

        sizetxt=findViewById(R.id.sizetxt);
        updatesize();
        ll=new LinearLayout[6];
        ll[0]=findViewById(R.id.ll1); ll[1]=findViewById(R.id.ll2); ll[2]=findViewById(R.id.ll3);
        ll[3]=findViewById(R.id.ll4); ll[4]=findViewById(R.id.ll5); ll[5]=findViewById(R.id.ll6);

        playbtn=findViewById(R.id.playbtn); upbtn=findViewById(R.id.upbtn); downbtn=findViewById(R.id.downbtn);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isplaying) {
                    if(timer==null) timer=new Timer();
                    TimerTask timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            for(int i=0; i<6; i++) {
                                if(seq[i].get(curr)) s.play(samples[i],1,1,0,0,1);
                            }
                            curr=(curr+1)%(period*16);
                        }
                    };
                    timer.schedule(timerTask,500,(long)((float)15000/tempo));
                    isplaying=!isplaying;
                    playbtn.setText("Stop");
                } else {
                    timer.cancel();
                    timer=null;
                    curr=0;
                    playbtn.setText("Play");
                    isplaying=!isplaying;
                }
            }
        });
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                period++;
                updatesize();
                if(seq[0].size()<period*16) {
                    for(int i=0; i<6; i++) {
                        for(int j=(period-1)*16; j<period*16; j++) {
                            seq[i].add(false);
                            Lever lever=new Lever(i, j,MainActivity.this);
                            lever.setMinimumWidth((int)(5*mScale));
                            lever.setWidth((int)(35*mScale));
                            btns[i].add(lever);
                        }
                    }
                }
                paint();
            }
        });
        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(period>1) {
                    period--;
                    updatesize();
                    paint();
                }
            }
        });

        paint();


    }


    private void paint() {
        for(int i=0; i<6; i++) {
            ll[i].removeAllViews();
            for(int j=0; j<period*16; j++) {
                if(seq[i].get(j)) btns[i].get(j).bg_white();
                else btns[i].get(j).bg_grey();
                ll[i].addView(btns[i].get(j));
            }
        }
    }
    private void updatesize() {
        sizetxt.setText(Integer.toString(period));
    }
    static void updated(int instno, int num, boolean chosen) {
        seq[instno].set(num, chosen);
    }
    private void loadsamples(int kitno) {
        if(kitno==1) {
            samples[0]=s.load(MainActivity.this,R.raw.kicktrap,0);
            samples[1]=s.load(MainActivity.this,R.raw.snaretrap,0);
            samples[2]=s.load(MainActivity.this,R.raw.rimtrap,0);
            samples[3]=s.load(MainActivity.this,R.raw.hhtrap,0);
            samples[4]=s.load(MainActivity.this,R.raw.ohhtrap,0);
            samples[5]=s.load(MainActivity.this,R.raw.crashtrap,0);
        }
    }
}
