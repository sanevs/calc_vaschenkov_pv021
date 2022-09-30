package com.example.kw2_vaschenkov_pv021;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kw2_vaschenkov_pv021.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_second);
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
            case R.id.btn_one:
                checkAction(1);
                break;
            case R.id.btn_two:
                checkAction(2);
                break;
            case R.id.btn_three:
                checkAction(3);
                break;
            case R.id.btn_four:
                checkAction(4);
                break;
            case R.id.btn_five:
                checkAction(5);
                break;
            case R.id.btn_six:
                checkAction(6);
                break;
            case R.id.btn_seven:
                checkAction(7);
                break;
            case R.id.btn_eight:
                checkAction(8);
                break;
            case R.id.btn_nine:
                checkAction(9);
                break;
            case R.id.btn_zero:
                checkAction(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }

}