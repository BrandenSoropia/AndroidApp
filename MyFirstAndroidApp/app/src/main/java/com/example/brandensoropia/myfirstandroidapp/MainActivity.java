package com.example.brandensoropia.myfirstandroidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstandroidapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.PREF_FILE_KEY), Context.MODE_PRIVATE);

        float balance = sharedPref.getFloat(getString(R.string.BALANCE), 0.00f);
        updateBalanceTextView(balance);
    }

    private void updateBalanceTextView (float newBalance) {
        TextView textViewBalance = (TextView) findViewById(R.id.balance);
        textViewBalance.setText(String.valueOf(newBalance));
    }

    public void reduceBalance (View view) {
        EditText editText = (EditText) findViewById(R.id.reduceAmount);
        float amount = Float.valueOf(editText.getText().toString());

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.PREF_FILE_KEY), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        float balance = sharedPref.getFloat(getString(R.string.BALANCE), 0.00f);
        float newBalance = balance - amount;

        editor.putFloat(getString(R.string.BALANCE), newBalance);
        editor.commit();
        updateBalanceTextView(newBalance);
    }

    public void goToAddBalance(View view) {
        Intent intent = new Intent(this, AddBalanceActivity.class);
        startActivity(intent);
    }
}
