package com.example.kw2_vaschenkov_pv021.MainActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kw2_vaschenkov_pv021.R;

public class MainActivity6 extends AppCompatActivity {
    class MyThreadResizer extends Thread{
        private Handler handler;
        private int what;

        public MyThreadResizer(Handler handler, int what) {
            this.handler = handler;
            this.what = what;
        }

        @Override
        public void run()
        {
            int       width     = 100;
            int       height    = 100;
            boolean   isIncrease = true;
            try
            {
                while (true)
                {
                    Thread.sleep(10);

                    if (isIncrease)
                    {
                        width++;
                        height++;
                        if (width > 400)
                            isIncrease = false;
                    }
                    else
                    {
                        width--;
                        height--;
                        if (width < 50)
                            isIncrease = true;
                    }

                    //send to handler
                    Message msg = new Message();
                    msg.arg1    = width;
                    msg.arg2    = height;
                    msg.what    = this.what;
                    handler.sendMessage(msg);
                }
            }
            catch (InterruptedException ie) { }
        }
    }

    private static Handler handler;
    private static Activity activity;
    private static MyThreadResizer thr_resizer1;
    private static MyThreadResizer thr_resizer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_threads);
        setHandler();
    }
    private void setHandler(){
        activity = this;
        if(handler == null)
        {
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Button button = activity.findViewById(msg.what);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);
                    params.setMarginStart(400);
                    params.width = msg.arg1;
                    params.height = msg.arg2;
                    if(button != null){
                        button.setLayoutParams(params);
                        button.setText(msg.arg1 + " x " + msg.arg2);
                    }
                }
            };
        }

        if(thr_resizer1 == null)
        {
            thr_resizer1 = new MyThreadResizer(handler, R.id.btn6_one);
            thr_resizer1.start();
        }
        if(thr_resizer2 == null)
        {
            thr_resizer2 = new MyThreadResizer(handler, R.id.btn6_two);
            thr_resizer2.start();
        }
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
