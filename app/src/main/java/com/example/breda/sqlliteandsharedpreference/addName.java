package com.example.breda.sqlliteandsharedpreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class addName extends AppCompatActivity {

    EditText add,prior,descs;
    String adds,prios,des;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        add=(EditText)findViewById(R.id.todo);
        prior=(EditText)findViewById(R.id.priority);
        descs=(EditText)findViewById(R.id.desc);
        submit=(Button)findViewById(R.id.button);

        adds=add.getText().toString();
        prios=prior.getText().toString();
        des=descs.getText().toString();
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent();
            intent.putExtra("name",adds);
            intent.putExtra("priority",prios);
            intent.putExtra("desc",des);

            setResult(1,intent);
            finish();
        }
    });
    }

}
