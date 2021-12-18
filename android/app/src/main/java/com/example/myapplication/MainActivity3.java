package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // creating a variable for tree view.
        TreeView treeView = findViewById(R.id.idTreeView);

        // creating adapter class for our treeview using basetree adapter.
        // inside base tree adapter you have to pass viewholder class along
        // with context and your layout file for treeview node.
        BaseTreeAdapter<Viewholder> adapter = new BaseTreeAdapter<Viewholder>(this, R.layout.tree_view_node) {
            @NonNull
            @Override
            public Viewholder onCreateViewHolder(View view) {
                return new Viewholder(view);
            }

            @Override
            public void onBindViewHolder(Viewholder viewHolder, Object data, int position) {
                // inside our on bind view holder method we
                // are setting data from object to text view.
                viewHolder.textView.setText(data.toString());

            }
        };

        // below line is setting adapter for our tree.
        treeView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        String a = getIntent().getStringExtra(MainActivity1.EXTRA_TEXT2);
        TreeNode r = null;
        try {
            r = main.expressionTree(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.setRootNode(r);
    }
}
