package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {

    ListView cityList; // Attribute
    ArrayAdapter<String> cityAdapter; // Attribute used to link our visual element (list view) to the array list
    ArrayList<String> dataList; //Attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Necessary
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // Necessary towards the top to setup the UI before you refer to the UI. Have these two statements as some of the first lines of code
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { //optional
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        cityList = findViewById(R.id.city_list);
        Button delete_button = findViewById(R.id.delete_button);

        String[] cities = {"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalVariables.last_position = position;
                if (GlobalVariables.delete_clicked) {
                    dataList.remove(GlobalVariables.last_position);
                    cityAdapter.notifyDataSetChanged();
                    GlobalVariables.delete_clicked = false;
                    }
                }


        });

    }



    public void add_city_visible(View view) {
        EditText edit_text = findViewById(R.id.edit_text);
        Button confirm_button = findViewById(R.id.confirm_button);
        confirm_button.setVisibility(View.VISIBLE);
        edit_text.setVisibility(View.VISIBLE);
    }


        public void click_confirm(View view) {
        EditText edit_text = findViewById(R.id.edit_text);
        Button confirm_button = findViewById(R.id.confirm_button);

        String city = edit_text.getText().toString();
        dataList.add(city);
        cityAdapter.notifyDataSetChanged();
        confirm_button.setVisibility(View.INVISIBLE);
        edit_text.setVisibility(View.INVISIBLE);
        edit_text.setText("");
    }

    public void click_delete(View view) {
        GlobalVariables.delete_clicked = true;
    }

}