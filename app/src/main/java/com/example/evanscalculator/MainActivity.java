package com.example.evanscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.security.InvalidParameterException;

import android.view.View;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String displayString = "0";
    public Double previousValue = null;
    public Double currentValue = null;
    public Character symbol = null;
    public boolean hasDecimal = false;
    public boolean inErrorState = false;

    public TextView mainTextView = null;
    public TextView operationTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = (TextView) findViewById(R.id.mainTextView);
        operationTextView = (TextView) findViewById(R.id.operationTextView);
        display();
    }

    public void calculate(View view){
        if (inErrorState || previousValue == null || currentValue == null || symbol == null) {return;}

        Double result = null;

        switch(symbol){
            case '+':
                result = add(previousValue, currentValue);
                break;
            case '-':
                result = subtract(previousValue, currentValue);
                break;
            case '*':
                result = multiply(previousValue, currentValue);
                break;
            case '/':
                if (currentValue == 0) {
                    inErrorState = true;
                    break;
                }
                else {
                    result = divide(previousValue, currentValue);
                    break;
                }
            default:
                displayString = "Error";
        }

        if (result != null) {
            displayString = result.toString();
            previousValue = result;
            currentValue = null;
        }

        display();
    }

    public double add(double a, double b){
        return a + b;
    }

    public double subtract(double a, double b){
        return a - b;
    }

    public double multiply(double a, double b){
        return a * b;
    }

    public double divide(double a, double b){
        if (b == 0) {
            inErrorState = true;
            return 0;
        }
        return a / b;
    }

    // press AC
    public void cleanUp(View view){
        displayString = null;
        previousValue = null;
        currentValue = null;
        symbol = null;
        hasDecimal = false;
        inErrorState = false;
        display();
    }

    public void pressDelete(View view) {
        if (displayString != null && displayString.length() > 0){
            displayString = displayString.substring(0, displayString.length()-1);
        }
        if (displayString == null || displayString.isEmpty()){
            currentValue = null;
        } else {
            currentValue = Double.parseDouble(displayString);
        }
        display();
    }

    // press decimal button
    public void pressDecimal(View view){
        if (hasDecimal == false) {
            hasDecimal = true;
            displayString = displayString == null ? "0" : displayString + ".";
        }
        display();
    }

    // press sign button
    public void pressSign(View view){
        if (displayString != null){
            currentValue = Double.parseDouble(displayString);
            currentValue = 0 - currentValue;
            displayString = currentValue.toString();
        }
        display();
    }

    public void pressAdd(View view){
        symbol = '+';
        if (currentValue != null){
            previousValue = currentValue;
            currentValue = null;
        }
        display();
    }

    public void pressSubtract(View view){
        symbol = '-';
        if (currentValue != null){
            previousValue = currentValue;
            currentValue = null;
        }
        display();
    }

    public void pressMultiply(View view){
        symbol = '*';
        if (currentValue != null){
            previousValue = currentValue;
            currentValue = null;
        }
        display();
    }

    public void pressDivide(View view){
        symbol = '/';
        if (currentValue != null){
            previousValue = currentValue;
            currentValue = null;
        }
        display();
    }

    // press digit
    public void pressDigit(int i){
        if(displayString == null || displayString.equals("0") || currentValue == null) {
            displayString = "" + i;
        } else {
            displayString = displayString + i;
        }

        currentValue = Double.parseDouble(displayString);
        display();
    }

    public void press0(View view){
        pressDigit(0);
    }

    public void press1(View view){
        pressDigit(1);
    }

    public void press2(View view){
        pressDigit(2);
    }

    public void press3(View view){
        pressDigit(3);
    }

    public void press4(View view){
        pressDigit(4);
    }

    public void press5(View view){
        pressDigit(5);
    }

    public void press6(View view){
        pressDigit(6);
    }

    public void press7(View view){
        pressDigit(7);
    }

    public void press8(View view){
        pressDigit(8);
    }

    public void press9(View view){
        pressDigit(9);
    }

    public void display(){
        if (inErrorState) {
            mainTextView.setText("Error. Please press AC to reset.");
            operationTextView.setText("");
        }
        else {
            mainTextView.setText((displayString == null || displayString.isEmpty()) ? "0" : displayString);
            operationTextView.setText(symbol == null ? "" : symbol.toString());
        }
    }
}
