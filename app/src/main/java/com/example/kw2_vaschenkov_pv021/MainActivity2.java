package com.example.kw2_vaschenkov_pv021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private MySQLiteHelper sqLiteHelper;
    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkAndAddUserPermissionForExternalStorage();
        sqLiteHelper = new MySQLiteHelper(this);
        ListView lvProducts = (ListView) this.findViewById(R.id.lvProducts);
        ArrayList<Product> products = new ArrayList<>();
        final SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor cursor = db.query(MySQLiteHelper.tableProducts,
                null, null, null, null, null, "id");
        if (cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex("id");
            int indexName = cursor.getColumnIndex("name");
            int indexPrice = cursor.getColumnIndex("price");
            int indexWeight = cursor.getColumnIndex("weight");
            do {
                products.add(new Product
                        (cursor.getInt(indexId),
                                cursor.getString(indexName),
                                cursor.getDouble(indexPrice),
                                cursor.getInt(indexWeight))
                );
            }
            while (cursor.moveToNext());
            cursor.close();
        } else
            Toast.makeText(this, "Unable to move next cursor", Toast.LENGTH_SHORT).show();

        adapter = new ArrayAdapter<Product>(this, R.layout.my_text_view_adapter,
                R.id.text1, products);
        lvProducts.setAdapter(adapter);
    }

    private void checkAndAddUserPermissionForExternalStorage()
    {
        /*
         * Проверка, что у приложения есть разрешения
         * на чтение/запись файлов на внешнем носителе!
         * ------------------------------------------------
         */
        int permission = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            //-- Разрешения нет, запросим это разрешение
            //-- у пользователя ------------------------------
            ActivityCompat.requestPermissions(
                    this,
                    new String[]
                            {
                                    //Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, 1);
            String externalStorageState = Environment.getExternalStorageState();
            permission = ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            Toast.makeText(this, permission + ", " + externalStorageState, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickToFirstActivity(View view){
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
    }
    public void onClickCreateFile(View view){
        File esMainDir = Environment.getExternalStorageDirectory();
        ArrayList<String> files = new ArrayList<>();
        for (int i = 0; i < esMainDir.listFiles().length; i++) {
            files.add(esMainDir.listFiles()[i].getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_single_choice,
                files);

        //create dialog window
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, android.R.style.Theme_Dialog);
        builder.setTitle("Create file");

        //create content for dialog window
        LayoutInflater inflater = this.getLayoutInflater();
        View create_file_dialog_view = inflater.inflate(
                R.layout.create_file_dialog,null, false);

        ListView lvDirList = (ListView) create_file_dialog_view.findViewById(R.id.lvDirList);
        lvDirList.setAdapter(adapter);
        lvDirList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        builder.setView(create_file_dialog_view);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File esMainDir = Environment.
                        getExternalStorageDirectory();
                ListView lvDirList = (ListView)((AlertDialog) dialog).findViewById(R.id.lvDirList);
                int index = lvDirList.getCheckedItemPosition();
                String strDir = "";
                if (index != -1)
                {
                    strDir = lvDirList.getAdapter().getItem(index).toString();
                    //strDir = strDir.replaceAll("[\\[\\]]", "");
                }
                File dir = (strDir.isEmpty()) ? esMainDir : (new File(esMainDir, strDir));

                //set filename
                EditText etFileName = (EditText) ((AlertDialog) dialog).findViewById(R.id.etFileName);
                String strFileName = etFileName.getText().toString();
                if (strFileName.isEmpty()){
                    strFileName = "noname" + ((int)(Math.random() * 1000)) +".txt";
                }

                //set file content
                EditText etFileCont = (EditText)((AlertDialog) dialog).findViewById(R.id.etFileContent);
                String strContent = etFileCont.getText().toString();
                if (strContent.isEmpty()){
                    strContent = "no content";
                }

                //create file
                try
                {
                    File file = new File(dir, strFileName);
                    String fileName = file.getAbsolutePath() + ".txt";
                    checkAndAddUserPermissionForExternalStorage();
                    FileWriter f = new FileWriter(fileName );
                    f.write(strContent + "\r\n");
                    f.flush();
                    f.close();
                    Toast.makeText(MainActivity2.this,
                            "Файл создан успешно: \n" + strFileName,
                            Toast.LENGTH_LONG).show();
                }
                catch (IOException ioe)
                {
                    Toast.makeText(MainActivity2.this,
                            "Ошибка записи в файл: \n" + ioe.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Отменить", null);
        builder.create().show();
    }
}