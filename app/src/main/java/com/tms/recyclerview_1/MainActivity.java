package com.tms.recyclerview_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;

    private EditText insertText;
    private EditText removeText;

    private ArrayList<ExampleItem> exampleItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerViews();
        registerButtonListeners();
        createExampleList();
        buildRecyclerView();

    }

    private void registerViews() {
        buttonInsert = findViewById(R.id.button);
        buttonRemove = findViewById(R.id.button_Remove);
        insertText = findViewById(R.id.edittext_insert);
        removeText = findViewById(R.id.edittext_remove);
    }

    private void registerButtonListeners() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(Integer.parseInt(insertText.getText().toString()));
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(Integer.parseInt(removeText.getText().toString()));
            }
        });
    }

    private void createExampleList() {
        exampleItems = new ArrayList<>();

        exampleItems.add(new ExampleItem(R.drawable.ic_android, "Android" , "subTextAndroid"));
        exampleItems.add(new ExampleItem(R.drawable.ic_aspect, "Line 2" , "subTextAspect line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_battery, "Line 3" , "subTextBattery line 2"));

       /* exampleItems.add(new ExampleItem(R.drawable.ic_android, "Android" , "subTextAndroid"));
        exampleItems.add(new ExampleItem(R.drawable.ic_aspect, "Line 2" , "subTextAspect line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_battery, "Line 3" , "subTextBattery line 2"));

        exampleItems.add(new ExampleItem(R.drawable.ic_android, "Android" , "subTextAndroid"));
        exampleItems.add(new ExampleItem(R.drawable.ic_aspect, "Line 2" , "subTextAspect line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_battery, "Line 3" , "subTextBattery line 2"));*/
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
        });
    }

    private void insertItem(int position) {
         exampleItems.add(position, new ExampleItem(R.drawable.ic_battery, "item " + position, "iutem position " + position));
         mAdapter.notifyItemInserted(position);
    }

    private void removeItem(int position) {
        exampleItems.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private void changeItem(int position, String text) {
        exampleItems.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    private void deleteItem(int position) {
        exampleItems.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
