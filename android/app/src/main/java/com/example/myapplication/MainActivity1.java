package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import de.blox.treeview.TreeNode;

public class MainActivity1 extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.application";
    public static final String EXTRA_TEXT2 = "com.application23";
    private EditText calculation;
    private TextView result;
    String curr, res, curr2;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnDivide, btnMultiply, btnMinus, btnSum, btnEqual, btnDot, btnAC, btnSin,
            btnparantez1, btnparantez2, btnpow, btnCos, btnTan, btnPi, btnE, btnSqrt, btnLog, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        calculation = (EditText) findViewById(R.id.calculation);
        result = (TextView) findViewById(R.id.result);
        curr = "";
        res = "";
        getSupportActionBar().setTitle("Calculator");
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnSin = (Button) findViewById(R.id.btnSin);
        btnCos = (Button) findViewById(R.id.btnCos);
        btnTan = (Button) findViewById(R.id.btnTan);
        btnPi = (Button) findViewById(R.id.btnPi);
        btnE = (Button) findViewById(R.id.btnE);
        btnSqrt = (Button) findViewById(R.id.btnSqrt);
        btnLog = (Button) findViewById(R.id.btnLog);
        btnAC = (Button) findViewById(R.id.btnAc);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnparantez1 = (Button) findViewById(R.id.btnParantez1);
        btnpow = (Button) findViewById(R.id.btnPow);
        btnparantez2 = (Button) findViewById(R.id.btnParantez2);
        btnDel = (Button) findViewById(R.id.btnDel);

        calculation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!calculation.getText().toString().isEmpty()) {
                    curr = String.valueOf(calculation.getText());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn0.setOnClickListener(v -> {
            curr = curr + '0';
            displayOne();
            show();
        });
        btn1.setOnClickListener(v -> {
            curr = curr + '1';
            displayOne();
            show();
        });
        btn2.setOnClickListener(v -> {
            curr = curr + '2';
            displayOne();
            show();
        });
        btn3.setOnClickListener(v -> {
            curr = curr + '3';
            displayOne();
            show();
        });
        btn4.setOnClickListener(v -> {
            curr = curr + '4';
            displayOne();
            show();
        });
        btn5.setOnClickListener(v -> {
            curr = curr + '5';
            displayOne();
            show();
        });
        btn6.setOnClickListener(v -> {
            curr = curr + '6';
            displayOne();
            show();
        });
        btn7.setOnClickListener(v -> {
            curr = curr + '7';
            displayOne();
            show();
        });
        btn8.setOnClickListener(v -> {
            curr = curr + '8';
            displayOne();
            show();
        });
        btn9.setOnClickListener(v -> {
            curr = curr + '9';
            displayOne();
            show();
        });
        btnSum.setOnClickListener(v -> {
            curr = curr + '+';
            displayOne();
            show();
        });
        btnMinus.setOnClickListener(v -> {
            curr = curr + '-';
            displayOne();
            show();
        });
        btnMultiply.setOnClickListener(v -> {
            curr = curr + '*';
            displayOne();
            show();
        });
        btnDivide.setOnClickListener(v -> {
            curr = curr + '/';
            displayOne();
            show();
        });
        btnSin.setOnClickListener(v -> {
            curr = curr + "sin(";
            displayOne();
            show();
        });
        btnCos.setOnClickListener(v -> {
            curr = curr + "cos(";
            displayOne();
            show();
        });
        btnTan.setOnClickListener(v -> {
            curr = curr + "tan(";
            displayOne();
            show();
        });
        btnPi.setOnClickListener(v -> {
            curr = curr + "π";
            displayOne();
            show();
        });
        btnE.setOnClickListener(v -> {
            curr = curr + "e";
            displayOne();
            show();
        });
        btnSqrt.setOnClickListener(v -> {
            curr = curr + "sqrt(";
            displayOne();
            show();
        });
        btnLog.setOnClickListener(v -> {
            curr = curr + "log(";
            displayOne();
            show();
        });
        btnDot.setOnClickListener(v -> {
            curr = curr + ".";
            displayOne();
            show();
        });
        btnAC.setOnClickListener(v -> {
            clear();
            displayOne();
            displayTwo();
        });
        btnEqual.setOnClickListener(v -> {
            try {
                String a = main.infixToPostfix(main.make(curr));
                res = String.valueOf(main.evaluatePostfix(a));
                displayOne();
                displayTwo();
                curr2 = curr;
                calculation.setText(res);
                curr = res;
            } catch (Exception e) {
                res = "ERROR";
                displayTwo();
                clear();
                displayOne();
            }
        });
        btnparantez1.setOnClickListener(v -> {
            curr = curr + '(';
            displayOne();
            show();
        });
        btnpow.setOnClickListener(v -> {
            curr = curr + '^';
            displayOne();
            show();
        });
        btnDel.setOnClickListener(v -> {
            backspace();
            displayOne();
            show();
            if (curr.isEmpty()) {
                clear();
                displayTwo();
            }
        });
        btnparantez2.setOnClickListener(v -> {
            curr = curr + ')';
            displayOne();
            show();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                openSteps();
                return true;
            case R.id.item3:
                openTree();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayOne() {
        calculation.setText(curr);
    }

    public void displayTwo() {
        result.setText(res);
    }

    public void show() {

        try {
            String a = main.infixToPostfix(main.make(curr));
            res = String.valueOf(main.evaluatePostfix(a));
            displayOne();
            displayTwo();
        } catch (Exception e) {
            res = "ERROR";
            displayTwo();
        }
    }

    public void clear() {
        curr = "";
        res = "";
    }

    public void backspace() {
        if (!curr.isEmpty())
            curr = curr.substring(0, curr.length() - 1);
    }

    public String show_steps() {
        try {
            String a = main.infixToPostfix(main.make(curr2));
            return main.show_steps(a);
        } catch (Exception e) {
            res = "ERROR";
            displayTwo();
        }
        return null;
    }

    public String  show_tree() {
        TreeNode r;
        try {
            main.st = new StringBuffer();
            String a = main.infixToPostfix(main.make(curr2));
            r = main.expressionTree(a);
            return a;
        } catch (Exception e) {
            res = "ERROR";
            displayTwo();
        }
        return null;
    }

    public void openSteps() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(EXTRA_TEXT, show_steps());
        startActivity(intent);
    }

    public void openTree() {
        System.out.println("enter tree");
        Intent intent = new Intent(this, MainActivity3.class);
          intent.putExtra(EXTRA_TEXT2,show_tree());
        startActivity(intent);

    }
}