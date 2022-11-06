package com.example.kw2_vaschenkov_pv021.Threads_classes;

import android.util.Log;

import androidx.annotation.NonNull;

public class MyThread extends Thread{
    private final static String THR_TAG = "MyThread";
    private String msg;

    public MyThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(750);
                Log.d(THR_TAG, this.msg + "(" +
                        Thread.currentThread().getName() +
                        ")");
            }
        }
        catch (InterruptedException e){
            Log.d(THR_TAG, "Поток прерван : " +
                    Thread.currentThread().getName());
        }
    }
}
