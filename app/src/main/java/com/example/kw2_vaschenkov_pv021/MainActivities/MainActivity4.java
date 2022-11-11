package com.example.kw2_vaschenkov_pv021.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.kw2_vaschenkov_pv021.R;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private ListView lvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_coordinator);
        setListView();
        setFloatingButtonClick();
        this.lvList = (ListView) this.findViewById(R.id.lvList);
        setScrollingEventHandler();
    }

    private void setListView(){
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 150; i++)
        {
            arr.add("Hello World " + (i + 1));
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        arr);
        ListView lvList = (ListView) this.findViewById(R.id.lvList);
        lvList.setAdapter(adapter);
    }

    private void setFloatingButtonClick(){
        findViewById(R.id.fab).setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Toast.makeText(MainActivity4.this,
                                "Floating Action Button Pressed!",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setScrollingEventHandler(){
        final CoordinatorLayout CL = (CoordinatorLayout) this.lvList.getParent();
        this.lvList.setOnScrollListener(
                new AbsListView.OnScrollListener()
                {
                    private int prevFirstVisible = 0;

                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        switch (scrollState){
                            case 1:
                                CL.onStartNestedScroll(
                                        MainActivity4.this.lvList, CL, 0);
                                break;
                            case 0:
                                CL.onStopNestedScroll(CL);
                                break;
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        CL.onNestedScroll(MainActivity4.this.
                                lvList, 0, (firstVisibleItem - this.
                                prevFirstVisible), 0, 0);
                        this.prevFirstVisible = firstVisibleItem;
                    }
                });
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
