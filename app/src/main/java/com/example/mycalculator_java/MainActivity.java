package com.example.mycalculator_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;
    private int operatorIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);

    }

    public void onDigit(View view) {
        handleDivByZero();
        inputTextView.append(((Button) view).getText());
    }

    private void handleDivByZero() {
        if (inputTextView.getText().toString().equals("DivByZero"))
            inputTextView.setText("");
    }

    public void onOperator(View view) {
        handleDivByZero();
        if (inputTextView.getText().toString().isEmpty() && ((Button) view).getText().equals("-")) {
            inputTextView.append(((Button) view).getText());
            operatorIndex = inputTextView.getText().length() - 1;
        } else if (!inputContainsOperator() && isLastEntryDigit()) {
            inputTextView.append(((Button) view).getText());
            operatorIndex = inputTextView.getText().length() - 1;
        }


    }


    private boolean isLastEntryDigit() {
        String input = inputTextView.getText().toString();
        return Character.isDigit(input.charAt(input.length() - 1));

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
        String input = inputTextView.getText().toString();
        int firstNumber = Integer.parseInt(input.substring(0, operatorIndex));
        int secondNumber = Integer.parseInt(input.substring(operatorIndex+1));
        String operator = getOperator();
        switch (operator) {
            case "+": inputTextView.setText(String.valueOf(firstNumber + secondNumber)); break;
            case "-": inputTextView.setText(String.valueOf(firstNumber - secondNumber)); break;
            case "*": inputTextView.setText(String.valueOf(firstNumber * secondNumber)); break;
            case "/":
                if (secondNumber != 0)
                    inputTextView.setText(String.valueOf(firstNumber / secondNumber));
                else
                    inputTextView.setText("DivByZero");
                break;
        }

    }

    private String getOperator() {
        return inputTextView.getText().charAt(operatorIndex) + "";
    }

    public void onDot(View view) {
        handleDivByZero();
        if (!inputTextView.getText().toString().contains("."))
            inputTextView.append(((Button) view).getText());

    }


    public void onClear(View view) {
        inputTextView.setText("");
    }
}