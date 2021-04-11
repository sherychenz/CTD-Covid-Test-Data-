package com.example.ctdcovidtestdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewTestKitActivity extends AppCompatActivity {

    private EditText TestKitNameText, StockText;
    private String TestKitName, Stock;

    testkit testkit = new testkit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test_kit);

        TestKitNameText       = findViewById(R.id.TestKitNameText);
        StockText            = findViewById(R.id.StockText);
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), ManageTestKitStockActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void testkit(View view) {
        TestKitName = TestKitNameText.getText().toString().trim();
        Stock = StockText.getText().toString().trim();

        if (TestKitName.isEmpty() && Stock.isEmpty()){
            TestKitNameText.setError("Can't be Empty");
            StockText.setError("Can't be Empty");
        }else  if(TestKitName.isEmpty()){
            TestKitNameText.setError("Can't be Empty");
        }else  if(Stock.isEmpty()){
            StockText.setError("Can't be Empty");
        }else{
            testkit.addNewTestKit(getApplicationContext(), TestKitName, Stock);
        }
    }
}