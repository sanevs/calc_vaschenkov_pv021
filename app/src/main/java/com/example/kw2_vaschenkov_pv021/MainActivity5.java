package com.example.kw2_vaschenkov_pv021;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kw2_vaschenkov_pv021.Threads_classes.*;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity5 extends AppCompatActivity {
    // ----- Inner class
    class MyThread4 extends Thread{
        private	final	static	String	THR_TAG	= "MyThread4";

        private		TextView	tvInfo;
        private		boolean		isPause	= false;

        public		MyThread4(TextView tvInfo)
        {
            this.tvInfo	= tvInfo;
        }

        @Override
        public	void run()
        {
            try
            {
                int	cnt	= 0;
                while(true)
                {
                    cnt++;

                    // ----- 1. Step -------------------------------------------------------
                    this.putMessage("1. Baker \nkneads the dough. (" + cnt + ")");
                    Thread.sleep(2000);		// The duration of this step
                    this.checkPause();		// May be thread suspend?

                    // ----- 2. Step -------------------------------------------------------
                    this.putMessage("2. The baker opens the oven door. (" + cnt + ")");
                    Thread.sleep(2000);		// The duration of this step
                    this.checkPause();		// May be thread suspend?

                    // ----- 3. Step -------------------------------------------------------
                    this.putMessage("3. The baker opens the gas valve of the oven. (" + cnt + ")");
                    Thread.sleep(2000);		// The duration of this step
                    // Suspension is forbidden here

                    // ----- 4. Step -------------------------------------------------------
                    this.putMessage("4. Baker lights a match and sets fire to the gas in the oven. (" + cnt + ")");
                    Thread.sleep(2000);		// The duration of this step
                    this.checkPause();		// May be thread suspend?

                    // ----- 5. Step -------------------------------------------------------
                    this.putMessage("5. Baker puts the dough in the oven. (" + cnt + ")");
                    Thread.sleep(2000);		// The duration of this step
                    // Suspension is forbidden here
                    // ----- 6. Step -------------------------------------------------------
                    this.putMessage("6. The baker closes the oven door. Baking started. (" + cnt + ")");
                    Thread.sleep(2000);		// The duration of this step
                    this.checkPause();		// May be thread suspend?

                }
            }
            catch (InterruptedException ie)
            {
                this.putMessage("Thread Interrupted : " + ie.getMessage());
            }
        }

        public 	synchronized void	suspendWork()
        {
            this.isPause	= true;
        }

        public	synchronized void	resumeWork()
        {
            this.isPause	= false;

            // ----- Сообщаем текущему потоку что необходимо проснуться ------------
            this.notify();
        }

        private	synchronized void	checkPause() throws InterruptedException
        {
            while (this.isPause)
            {
                Log.d(THR_TAG, "Thread Suspended");

                // ----- Текущий поток останавливается ---------------------------------
                this.wait();
            }
        }

        private	void	putMessage(final String msg)
        {
            Log.d(THR_TAG, msg);

            MainActivity5.this.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    MyThread4.this.tvInfo.setText(msg);
                }
            });
        }

        public void	setTextView(TextView tvInfo)
        {
            this.tvInfo	= tvInfo;
        }
    }

    private View curView;
    private LinearLayout mainLL;
    private DrawerLayout drawerLayout;
    private TextView tvInfo1;
    private TextView tvInfo2;
    private TextView tvInfo3;
    private TextView tvInfo4;
    private static MyThread T1 = null;
    private static MyThread2 T2 = null;
    private static MyThread3 T3 = null;
    private static MyThread4 T4 = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_threads);

        this.mainLL = (LinearLayout) this.findViewById(R.id.mainLL);
        this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawerLayout5);
        this.curView = this.findViewById(R.id.exampleOne);
        this.tvInfo1 = this.findViewById(R.id.tvInfo1);
        this.tvInfo2 = this.findViewById(R.id.tvInfo2);
        this.tvInfo3 = this.findViewById(R.id.tvInfo3);
        this.tvInfo4 = this.findViewById(R.id.tvInfo4);

        setItemSelectListener();

        if(T3 != null)
            T3.setTextView(tvInfo3, this);
        if(T4 != null)
            T4.setTextView(tvInfo4);
    }


    private void setItemSelectListener(){
        final NavigationView naviView = (NavigationView) this.findViewById(R.id.naviView);
        naviView.setNavigationItemSelectedListener(
                new NavigationView.
                        OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        MainActivity5.this.drawerLayout.
                                closeDrawers();

                        // ----- Прячем предыдущий видимый контейнер
                        ObjectAnimator anim =
                                new ObjectAnimator();
                        anim.setPropertyName("alpha");
                        anim.setDuration(300);
                        anim.setFloatValues(1.0f, 0.0f);
                        anim.setTarget(MainActivity5.this.curView);
                        anim.addListener(new Animator.
                                AnimatorListener() {
                            @Override
                            public void onAnimationStart(
                                    Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(
                                    Animator animation) {
                                ((ViewGroup) ((ObjectAnimator)
                                        animation)
                                        .getTarget())
                                        .setVisibility(View.
                                                INVISIBLE);
                            }

                            @Override
                            public void onAnimationCancel(
                                    Animator animation) {
                            }

                            @Override
                            public void onAnimationRepeat(
                                    Animator animation) {
                            }
                        });
                        anim.start();

                        // ----- Show the current layout
                        switch (item.getItemId()) {
                            case R.id.navigation_item_1:
                                MainActivity5.this.curView =
                                        MainActivity5.this.
                                                findViewById(R.
                                                        id.exampleOne);
                                break;
                            case R.id.navigation_item_2:
                                MainActivity5.this.curView =
                                        MainActivity5.this.
                                                findViewById(
                                                        R.id.exampleTwo);
                                break;
                            case R.id.navigation_item_3:
                                MainActivity5.this.curView =
                                        MainActivity5.this.
                                                findViewById(
                                                        R.id.exampleThree);
                                break;
                            case R.id.navigation_item_4:
                                MainActivity5.this.curView =
                                        MainActivity5.this.
                                                findViewById(
                                                        R.id.exampleFour);
                                break;
                        }
                        MainActivity5.this.curView.setVisibility(View.VISIBLE);
                        ObjectAnimator anim2 = new ObjectAnimator();
                        anim2.setPropertyName("alpha");
                        anim2.setDuration(300);
                        anim2.setStartDelay(300);
                        anim2.setFloatValues(0.0f, 1.0f);
                        anim2.setTarget(MainActivity5.this.curView);
                        anim2.start();
                        return true;
                    }
                });
    }

    public void btnExampleOneClick(View v)
    {
        if (T1 != null && T1.isAlive() == false)
        {
            T1 = null;
        }
        switch (v.getId())
        {
// ----- Start ThrExampleOne thread --------------
            case R.id.btnStart :
            {
                if (T1 == null)
                {
                    T1 = new MyThread(
                            "Android forever!");
                    T1.start();
                    Snackbar.make(mainLL,
                            "Thread started successfully",
                            Snackbar.LENGTH_LONG).show();
                    this.tvInfo1.setText("Thread working");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "Thread already running",
                                    Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.
                                    OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                }
                            }).show();
                }
            }
            break;
// ----- Interrupt ThrExampleOne thread ----------
            case R.id.btnInterrupt :
            {
                if (T1 != null)
                {
                    T1.interrupt();
                    Snackbar.make(mainLL,
                            "Thread interrupted",
                            Snackbar.LENGTH_LONG).show();
                    this.tvInfo1.setText("");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "No thread to interrupt",
                                    Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.
                                    OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                }
                            })
                            .show();
                }
            }
            break;
        }
    }

    public void btnExampleTwoClick(View v){
        if (T2 != null && T2.isAlive() == false)
            T2	= null;

        switch (v.getId())
        {
            // ----- Start 2 thread ------------------------------------
            case R.id.btnStartExampleTwo:
            {
                if (T2 == null)
                {
                    T2 = new MyThread2("Example Two Message");
                    T2.start();

                    Snackbar.make(mainLL,
                            "Thread started successfully", Snackbar.LENGTH_LONG).show();
                    this.tvInfo2.setText("Thread working");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "Thread already running", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                }
                            })
                            .show();
                }
            }
            break;

            // ----- Interrupt 2 thread --------------------------------
            case R.id.btnInterruptExampleTwo:
            {
                if (T2 != null)
                {
                    T2.interrupt();

                    Snackbar.make(mainLL,
                                    "Click 'Know' to find the state of the thread", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Know", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Snackbar.make(mainLL,
                                            ((T2 != null && T2.isAlive()) ?
                                                    "Thread working" : "Thread interrupted"),
                                            Snackbar.LENGTH_LONG).show();
                                }
                            })
                            .show();
                    this.tvInfo2.setText("");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "No thread to interrupt", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                }
                            })
                            .show();
                }
            }
            break;
        }
    }

    public void btnExampleThreeClick(View v){
        if (T3 != null && T3.isAlive() == false)
            T3	= null;

        switch (v.getId())
        {
            // ----- Start 3 thread ----------------------------------
            case R.id.btnStartExampleThree:
            {
                if (T3 == null)
                {
                    T3 = new MyThread3("Message", this.tvInfo3, this);
                    T3.start();

                    Snackbar.make(mainLL,
                            "Thread started successfully", Snackbar.LENGTH_LONG).show();
                    this.tvInfo3.setText("Thread working");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "Thread already running", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                }
                            })
                            .show();
                }
            }
            break;

            // ----- Interrupt ThrExampleOne thread --------------------------------
            case R.id.btnInterruptExampleThree:
            {
                if (T3 != null)
                {
                    T3.interrupt();

                    Snackbar.make(mainLL,
                            "Thread interrupted", Snackbar.LENGTH_LONG).show();
                    this.tvInfo3.setText("");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "No thread to interrupt", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                }
                            })
                            .show();
                }
            }
            break;
        }
    }

    public void btnExampleFourClick(View v){
        if (T4 != null && T4.isAlive() == false)
            T4	= null;

        switch (v.getId())
        {
            case R.id.btnStartExampleFour:
            {
                if (T4 == null)
                {
                    T4 = new MyThread4(this.tvInfo4);
                    T4.start();

                    Snackbar.make(mainLL,
                            "Thread started successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "Thread already running", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                }
                            })
                            .show();
                }
            }
            break;

            case R.id.btnInterruptExampleFour:
            {
                if (T4 != null)
                {
                    T4.interrupt();

                    Snackbar.make(mainLL,
                            "Thread interrupted", Snackbar.LENGTH_LONG).show();
                    this.tvInfo1.setText("");
                }
                else
                {
                    Snackbar.make(mainLL,
                                    "No thread to interrupt", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {

                                }
                            })
                            .show();

                }
            }
            break;

            case R.id.btnSuspendExampleFour :
                if (T4 != null)
                {
                    T4.suspendWork();
                    Snackbar.make(mainLL,
                            "Thread suspended", Snackbar.LENGTH_LONG).show();
                }
                break;

            case R.id.btnResumeExampleFour :
                if (T4 != null)
                {
                    T4.resumeWork();
                    Snackbar.make(mainLL,
                            "Thread resumed", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(T4 != null)
            T4.resumeWork();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(T4 != null)
            T4.suspendWork();
    }

    public void onClickMenu(View view){
        DrawerLayout drawerLayout =
                (DrawerLayout)
                        MainActivity5.this.findViewById(
                                R.id.drawerLayout5);
        drawerLayout.openDrawer(Gravity.LEFT);
    }
    public void onClickBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
