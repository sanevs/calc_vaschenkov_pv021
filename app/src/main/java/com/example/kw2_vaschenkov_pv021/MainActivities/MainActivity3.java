package com.example.kw2_vaschenkov_pv021.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kw2_vaschenkov_pv021.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity3 extends AppCompatActivity {
    private TextView textView;
    private final String text1 = "Современные мобильные устройства представляют\n" +
            "из себя карманные персональные компьютеры, которые\n" +
            "по своим техническим характеристикам не так уж силь-\n" +
            "но отстают от настольных персональных компьютеров.\n" +
            "Приложения для мобильных устройств могут решать\n" +
            "задачи такой же сложности, как и задачи, которые реша-\n" +
            "ются приложениями настольных компьютеров. Причем\n" +
            "возможность одним приложением выполнять несколько\n" +
            "задач одновременно (то есть быть многозадачным при-\n" +
            "ложением) является ключевой как для приложений на-\n" +
            "стольных компьютеров, так и для приложений мобиль-\n" +
            "ных устройств. В данном разделе мы рассмотрим как\n" +
            "создаются многозадачные приложения.";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTabLayout();
    }

    private void setTabLayout(){
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        textView = (TextView) findViewById(R.id.text_tab);

        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener()
                {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        Toast.makeText(MainActivity3.this, tab.getText() + " selected", Toast.LENGTH_SHORT).show();
                        if(tab.getPosition() == 0)
                            textView.setText(tab.getText() + " text here\n" + text1);
                        else
                            textView.setText(tab.getText() + " text here\n");
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
        tabLayout.getTabAt(1).select();
        tabLayout.getTabAt(0).select();
    }

    public void onClickToFirstActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
