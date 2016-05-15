package com.app.renhao.dietwallet;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    public ArrayList<DataPackage> datalist;
    public LayoutInflater inflater;
    public ListBindAadpter listBindAadpter;
    private ListView listView;
    private Realm mRealm;
    private RealmConfiguration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        datalist = new ArrayList<>();
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DBManager db= DBManager.getInstance(this);
        listBindAadpter = new ListBindAadpter(this,db.getDBresult(),true,R.layout.list_layout,inflater);
        listView = (ListView) findViewById(R.id.listView);
        if (db.getDBresult().size()>0){
            listView.setAdapter(listBindAadpter);
        }

        Log.d("onCreate", db.getDBresult()+"");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openDialogMenu();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        config = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        mRealm = Realm.getDefaultInstance();
    }

    private void openDialogMenu(){
        final LinearLayout editData=(LinearLayout) getLayoutInflater().inflate(R.layout.add_data_dialog_layout, null);

        AlertDialog.Builder  builder=new AlertDialog.Builder(this);
        builder.setTitle("What kind of food");

        builder.setView(editData);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager db =DBManager.getInstance(getApplicationContext());
                EditText editmoney = (EditText) editData.findViewById(R.id.edit_money);
//                if(db.getDBresult().size()>0){
//                    editmoney.setText(db.getLastDate().get(0).getMoney()+"");
//                    Log.d("openDialogMenu",db.getLastDate().get(0).getMoney()+"");
//                }

                int money = Integer.parseInt(editmoney.getText()+"");
                EditText editcalorie = (EditText) editData.findViewById(R.id.edit_calorie);
                int calorie = Integer.parseInt(editcalorie.getText()+"");
                EditText  editdate =  (EditText) editData.findViewById(R.id.edit_date);
                String date = editdate.getText()+"";
                EditText  editfood =  (EditText) editData.findViewById(R.id.edit_food);
                String food = editfood.getText()+"";


                db.addDailyDietDate(getApplicationContext(),money,calorie,date,food);
                listView.setAdapter(listBindAadpter);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //为builder对象添加确定按钮，不过这里嵌套了一个函数
       // setPositiveButton(builder);
        //为builder对象添加取消按钮
       // builder=setNegativeButton(builder);

        //builder创建对话框对象AlertDialog
        AlertDialog viewdialog=builder.create();
        viewdialog.show();

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
    protected void onStop() {
        super.onStop();
        mRealm.close();
    }
}
