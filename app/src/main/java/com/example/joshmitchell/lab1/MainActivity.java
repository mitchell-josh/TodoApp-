package com.example.joshmitchell.lab1;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex;
    private final String TODO_INDEX_KEY = "com.example.todoIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX_KEY);
        }

        final TextView todoTextView;
        todoTextView = (TextView) findViewById(R.id.textViewTodo);

        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);
        todoTextView.setText(mTodos[mTodoIndex]);

        Button buttonNext;
        buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTodoIndex += 1;

                if(mTodoIndex == mTodos.length)
                    mTodoIndex = 0;

                todoTextView.setText(mTodos[mTodoIndex]);
            }
        });

        Button buttonPrev;
        buttonPrev = (Button) findViewById(R.id.buttonPrev);

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTodoIndex -= 1;

                if (mTodoIndex == -1)
                    mTodoIndex = mTodos.length - 1;

                todoTextView.setText(mTodos[mTodoIndex]);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState){

        outState.putInt(TODO_INDEX_KEY, mTodoIndex);

        super.onSaveInstanceState(outState);
    }


}
