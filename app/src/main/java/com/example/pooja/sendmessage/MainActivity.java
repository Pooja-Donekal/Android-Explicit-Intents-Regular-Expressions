package com.example.pooja.sendmessage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText phonenum = (EditText) findViewById(R.id.editText); //Edittext to enter phone num
        Button ok = (Button) findViewById(R.id.button); // Submit button
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = phonenum.getText().toString(); // retrieve the string format of phone number
                    if(isValid(message)) { // checks if phone num confirms to the required format
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + message)); //if format matches takes to message activity
                        startActivityForResult(i,1); //sending code as 1, to identify the call
                    } // if no match do nothing
                }

            });
    }
    public void onActivityResult(int requestcode,int res,Intent i)
    {
        super.onActivityResult(requestcode,res,i);
        setContentView(R.layout.activity_main); //reload the same layout
        EditText phonenum = (EditText) findViewById(R.id.editText);
        phonenum.setText("Returning from Compose Message Activity"); // on returning from message activity display the corresponding message
    }
    public boolean isValid(String temp)
    {
        boolean isvalid=false;
        temp=temp.replace("\n",""); //eliminate newline characters
        String numformat="\\([0-9]{3}\\)\\s?[0-9]{3}-[0-9]{4}"; //define the format for phonenum using regular expression
        CharSequence s=temp;
        Pattern pattern=Pattern.compile(numformat,Pattern.DOTALL);
        Matcher matcher=pattern.matcher(temp);
        if(matcher.matches())
        {
            isvalid=true; // if match occurs set it to true and return
        }
       return isvalid;
    }
}

