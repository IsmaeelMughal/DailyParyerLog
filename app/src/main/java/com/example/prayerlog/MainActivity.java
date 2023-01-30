package com.example.prayerlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText prayerName, prayerDate, numberOfRakats;
    CheckBox isPray, isBajamat;
    Button addBtn, gitBtn;
    ListView listView;


    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prayerName = findViewById(R.id.prayerName);
        prayerDate = findViewById(R.id.prayerDate);
        numberOfRakats = findViewById(R.id.numOfRakats);
        isPray = findViewById(R.id.yesNo);
        isBajamat = findViewById(R.id.bajamat);

        addBtn = findViewById(R.id.BtnAdd);
        listView = findViewById(R.id.list_view);

        db = new DbHandler(this);
        RefreshGrid();

        gitBtn = findViewById(R.id.gitBtn);

        gitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/IsmaeelMughal/DailyParyerLog/commits/master";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = prayerName.getText().toString();
                String date = prayerDate.getText().toString();
                int rakats = Integer.parseInt(numberOfRakats.getText().toString());
                boolean pray = isPray.isChecked();
                boolean bajamat = isBajamat.isChecked();

                if (name.isEmpty() || date.isEmpty() || rakats == 0) {
                    Toast.makeText(MainActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Prayer prayer = new Prayer(name,date,pray,bajamat,rakats);
                db.insertPrayer(prayer);
                RefreshGrid();
            }
        });

    }

    public void RefreshGrid(){
        List<Prayer> prayers = db.selectAllPrayers();
        ArrayAdapter arrayAdapter = new ArrayAdapter<Prayer>(MainActivity.this, android.R.layout.simple_list_item_1,prayers);
        listView.setAdapter(arrayAdapter);
    }
}