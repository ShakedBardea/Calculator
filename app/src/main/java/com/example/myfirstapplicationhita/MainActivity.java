 package com.example.myfirstapplicationhita;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView result;
    int num1, num2, res;
    String action;
    boolean isNewCalculation;
    boolean isActionClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result = findViewById(R.id.textViewResult);
        result.setText("");
        num1 = 0;
        num2 = 0;
        action = "";
        res = 0;
        isNewCalculation = false;
        isActionClicked = false;
    }

    public void numFunc(View view) {

        if (isNewCalculation) {
            result.setText("");
            isNewCalculation = false;
        }
        Button button = (Button) view;
        result.append(button.getText().toString());
        if (!action.isEmpty()) {
            num2 = num2 * 10 + Integer.parseInt(button.getText().toString());
        } else {
            num1 = num1 * 10 + Integer.parseInt(button.getText().toString());
        }
    }

    public void actFunc(View view) {

        Button button = (Button) view;
        if (isActionClicked) {
            result.setText("Invalid Operation");
            setGlobal();
            return;
        }
        result.append(button.getText().toString());
        action = button.getText().toString();
        isActionClicked = true;
    }


    public void clrFunc(View view) {
        result.setText("");
        setGlobal();
    }


    public void equalFunc(View view) {
        result.append("=");
        if (action.equals("+")) {
            res = num1 + num2;
        } else if (action.equals("-")) {
            res = num1 - num2;
        } else if (action.equals("X")) {
            res = num1 * num2;
        } else if (action.equals(":")) {
            if (num2 == 0) {
                result.append("Error!");
                setGlobal();
                return;
            } else {
                res = num1 / num2;
            }
        } else {
            result.append("Invalid Operation");
            setGlobal();
            return;
        }
        result.append(res + "");
        setGlobal();
    }

    public void setGlobal(){
        isNewCalculation = true;
        isActionClicked = false;
        num1 = 0;
        num2 = 0;
        action = "";
        res = 0;
    }
}