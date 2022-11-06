package com.example.kw2_vaschenkov_pv021.Threads_classes;

import android.util.Log;

public class MyThread2 extends Thread{
    private final static String THR_TAG = "MyThread2";
    private String msg;

    public MyThread2(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        int		cnt	= 0;
        while (true)
        {
            try
            {
                Thread.sleep(750);
            }
            catch (InterruptedException ie)
            {
                Log.d(THR_TAG,
                        "attempt to interrupt the thread (ignored) : " +
                                Thread.currentThread().getName());
            }

            cnt++;
            String	str	= this.msg + " " + cnt + "(" + Thread.currentThread().getName() + ")";
            Log.d(THR_TAG, str);
        }
    }
}
