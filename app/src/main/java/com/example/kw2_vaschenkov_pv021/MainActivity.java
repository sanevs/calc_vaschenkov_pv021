package com.example.kw2_vaschenkov_pv021;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kw2_vaschenkov_pv021.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    ConstraintLayout layout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_second);
        ActivityCompat.requestPermissions(
                this,
                new String[]
                        {
                                Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        }, 1);

        layout = findViewById(R.id.activity);

        if(savedInstanceState != null)
        {
            textResultCur = savedInstanceState.getString("result");
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        nameTexts.put("Ann", "This is Ann");
        nameTexts.put("John", "This is John");
        nameTexts.put("Frank", "This is Frank");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = this.findViewById(R.id.text_result);
        if(textView == null)
            return;
        textView.setText(textResultCur);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.action_animation){
            ImageView human = (ImageView) findViewById(R.id.imgHuman);
            human.setBackgroundResource(R.drawable.frame_animation_running_man);
            AnimationDrawable animationDrawable = (AnimationDrawable) human.getBackground();
            if(animationDrawable.isRunning())
                human.setBackgroundResource(0);
            else
                animationDrawable.start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", textResultCur);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private String action = "";
    private String textResultCur = "";
    private void checkAction(Integer current){
        TextView result = this.findViewById(R.id.text_result);
        Integer resInt = 0;
        if (result.getText() != "")
            resInt = Integer.parseInt(result.getText().toString());
        if(action == "+")
        {
            Integer resSum = resInt + current;
            result.setText(resSum.toString());
            action = "";
            return;
        }
        if(action == "-")
        {
            Integer resSub = resInt - current;
            result.setText(resSub.toString());
            action = "";
            return;
        }
        if(action == "*")
        {
            Integer resMul = resInt * current;
            result.setText(resMul.toString());
            action = "";
            return;
        }
        if(action == "/" && current != 0)
        {
            Integer resDiv = resInt / current;
            result.setText(resDiv.toString());
            action = "";
            return;
        }
        result.setText(result.getText() + current.toString());
        textResultCur = result.getText().toString();
    }
    public void onClickButtonCalc(View view)
    {
        Button button = (Button) view;
        Integer buttonValue = 0;
        try {
            buttonValue = Integer.parseInt(button.getText().toString());
        }
        catch (NumberFormatException exception) {}
        int id = view.getId();
        switch (id){
            case R.id.btn_plus:
                action = "+";
                break;
            case R.id.btn_minus:
                action = "-";
                break;
            case R.id.btn_mul:
                action = "*";
                break;
            case R.id.btn_div:
                action = "/";
                break;
            case R.id.btn_clear:
                ((TextView)this.findViewById(R.id.text_result)).setText("");
                break;
            default:
                checkAction(buttonValue);
        }
    }

    public void onClickGridButton1(View view)
    {
        GridLayout gridLayout = this.findViewById(R.id.grid);
        Button b1 = new Button(this);
        b1.setText("new button");
        gridLayout.addView(b1);

        //fragment 1610
        Toast.makeText(this, "New button", Toast.LENGTH_LONG).show();
    }

    private Map<String, String> nameTexts = new HashMap<String, String>();
    public void onClickShowName(View view){
        TextView text = findViewById(R.id.nameText);
        text.setText(nameTexts.get(
                ((Button)view).getText()));
    }

    public void onClickShowDays(View view){

        ListView lvDaysOfweek = (ListView)
                this.findViewById(R.id.lvDaysOfWeek);
        String[] arrDaysOfWeek ={
                "Понедельник", "Вторник", "Среда",
                "Четверг", "Пятница", "Суббота",
                "Воскресенье"
        };
        Log.println(Log.INFO, "1", "msg");
        ArrayAdapter<String> lvAdapter =
                new ArrayAdapter<>(this,
                        R.layout.my_text_view_adapter
                                , arrDaysOfWeek);
        //--Назначение Адаптера Данных списку ListView----
        //--с днями недели--------------------------------
        lvDaysOfweek.setAdapter(lvAdapter);
    }

    public void onClickShowFilesDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, android.R.style.Widget_DeviceDefault);
        builder.setTitle("Files dialog");
        //builder.setView(R.id.textView);
        ArrayList<String> files = new ArrayList<>();

        //files urok 3, 5
        String externalStorageState = Environment.getExternalStorageState();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.d(MainActivity.PRINT_SERVICE,
                "File storage dir: " + externalStorageDirectory.getAbsolutePath());
        Toast.makeText(this, externalStorageDirectory.getAbsolutePath(), Toast.LENGTH_LONG).show();

        String[] filesStr = new String[externalStorageDirectory.listFiles().length];
        for (int i = 0; i < filesStr.length; i++) {
            filesStr[i] = externalStorageDirectory.listFiles()[i].getName();
        }

        builder.setItems(filesStr, null);
        builder.setNegativeButton("Close", null);
        builder.create().show();
    }

    public void onClickActivity2(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void onClickActivity3(View view){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}