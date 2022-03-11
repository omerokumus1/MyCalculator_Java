package com.example.mycalculator_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);

    }

    public void onDigit(View view) {
        inputTextView.append(((Button) view).getText());
    }

    public void onOperator(View view) {
        if (inputTextView.getText().toString().isEmpty() && ((Button) view).getText().equals("-"))
            inputTextView.append(((Button) view).getText());

        else if (!inputContainsOperator() && isLastEntryDigit())
            inputTextView.append(((Button) view).getText());


    }


    private boolean isLastEntryDigit() {
        String input = inputTextView.getText().toString();
        return Character.isDigit(input.charAt(input.length()-1));

    }

    private boolean inputContainsOperator() {
        String input = getInputString();
        return input.contains("*") || input.contains("/") || input.contains("-") || input.contains("+");
    }

    @NonNull
    private String getInputString() {
        String input;
        if (inputTextView.getText().charAt(0) == '-')
            input = inputTextView.getText().toString().substring(1);
        else
            input = inputTextView.getText().toString();
        return input;
    }

    public void onEqual(View view) {

    }

    public void onDot(View view) {
        inputTextView.append(((Button) view).getText());

    }

    public void onClear(View view) {
        inputTextView.setText("");
    }
}