package com.example.brandensoropia.myfirstandroidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddBalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);
    }

    public void goToMainActivity (View view) {
        _gotToMainActivity();
    }

    public void _gotToMainActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addBalance (View view) {
        EditText editText = (EditText) findViewById(R.id.topUpAmount);
        float amount = Float.valueOf(editText.getText().toString());
        _addBalance(amount);
    }

    public void _addBalance (float amount) {
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.PREF_FILE_KEY), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        float balance = sharedPref.getFloat(getString(R.string.BALANCE), 0.00f);
        float newBalance = balance + amount;

        editor.putFloat(getString(R.string.BALANCE), newBalance);
        editor.commit();

        _gotToMainActivity();
    }
}
