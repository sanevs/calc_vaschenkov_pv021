package com.example.kw2_vaschenkov_pv021;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity3 extends AppCompatActivity {
    private TextView textView;

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
                        textView.setText(tab.getText() + " text here");
                        //textView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
        tabLayout.selectTab(tabLayout.getTabAt(1));
    }

    public void onClickToFirstActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
