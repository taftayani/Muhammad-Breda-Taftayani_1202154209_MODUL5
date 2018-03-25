package com.example.breda.sqlliteandsharedpreference;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<sqlModel> sqlList = new LinkedList<>();
    private RecyclerView mRecylerView;
    private NameRecycle cycle;
    private DatabaseHandler database;

    private Dialog dialog;

    RadioButton rd1,rd2,rd3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DatabaseHandler(this);
        RecycleMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.change_c) {
            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.change_backround);
            dialog.setCancelable(true);
            dialog.setTitle("change color here!");
            rd1 = (RadioButton) dialog.findViewById(R.id.rdRed);
            rd2 = (RadioButton) dialog.findViewById(R.id.rdBlue);
            rd3 = (RadioButton) dialog.findViewById(R.id.rdGreen);
            btn = (Button) dialog.findViewById(R.id.btnChange);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rd1.isChecked()){
                        mRecylerView.setBackgroundResource(R.color.redBackgroud);
                        Toast.makeText(view.getContext(),"Red Choosen",Toast.LENGTH_SHORT).show();
                    }
                   else if (rd2.isChecked()){
                        mRecylerView.setBackgroundResource(R.color.blueBackgroud);
                        Toast.makeText(view.getContext(),"Blue Choosen",Toast.LENGTH_SHORT).show();
                    }
                   else if (rd3.isChecked()){
                        mRecylerView.setBackgroundResource(R.color.greenBackgroud);
                        Toast.makeText(view.getContext(),"Green Choosen",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(view.getContext(),"color is change",Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            // now that the dialog is set up, it's time to show it
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_add(View view) {
        Intent intent=new Intent(this,addName.class);
        startActivityForResult(intent,1);
    }

    public void RecycleMenu(){
        sqlList=database.getModel();

        mRecylerView=(RecyclerView)findViewById(R.id.cycle);

        cycle=new NameRecycle(this,sqlList);

        mRecylerView.setAdapter(cycle);

        mRecylerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
//            Log.d("new name : ",data.getStringExtra("name"));
//            Log.d("new desc : ",data.getStringExtra("desc"));
//            Log.d("new priority : ",data.getStringExtra("priority"));
            database.insert(new sqlModel(data.getStringExtra("name"), data.getStringExtra("desc"), data.getStringExtra("priority")));
        }
        RecycleMenu();
        cycle.notifyDataSetChanged();
    }
}
