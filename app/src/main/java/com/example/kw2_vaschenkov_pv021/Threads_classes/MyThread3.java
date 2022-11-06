package com.example.kw2_vaschenkov_pv021.Threads_classes;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MyThread3 extends Thread{
    private final static String THR_TAG = "MyThread3";
    private String msg;
    private TextView tvInfo;
    private Activity activity;

    public MyThread3(String msg, TextView tvInfo, Activity activity) {
        this.msg = msg;
        this.tvInfo = tvInfo;
        this.activity = activity;
    }

    @Override
    public void run() {
        try
        {
            int		cnt	= 0;
            while (true)
            {
                Thread.sleep(750);
                cnt++;
                final	String	str	= this.msg + " " + cnt + "(" + Thread.currentThread().getName() + ")";
                Log.d(THR_TAG, str);

                this.activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tvInfo.setText(str);
                    }
                });
            }
        }
        catch (InterruptedException ie)
        {
            Log.d(THR_TAG, "Thread interrupted : " +
                    Thread.currentThread().getName());
        }
    }
    public	void	setTextView(TextView tvInfo, Activity activity)
    {
        this.tvInfo		= tvInfo;
        this.activity	= activity;
    }
}
