package com.chefcast.breakfast.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText phoneNumber=(EditText)findViewById(R.id.phone_number);

        Button save=(Button)findViewById(R.id.btn_save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact=new Contact(name.getText().toString(),phoneNumber.getText().toString());
                DatabaseHandler dbhandler=new DatabaseHandler(MainActivity.this);
                dbhandler.addContacts(contact);
                Contact contact1=dbhandler.getContact(4);
                Log.d("name",contact1.getName());
                Log.d("Phone",contact1.getPhone_number());

            }
        });
    }
}
