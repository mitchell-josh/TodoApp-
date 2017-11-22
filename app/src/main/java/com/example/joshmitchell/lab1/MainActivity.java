package com.example.joshmitchell.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex;

    private final String TODO_INDEX_KEY = "com.example.todoIndex";
    private static final String IS_TODO_COMPLETE = "com.example.isTodoComplete";
    private static final int IS_SUCCESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve mTodoIndex when onCreate is called
        if(savedInstanceState != null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX_KEY);
        }


        //Handle buttons and display
        final TextView todoTextView;
        todoTextView = findViewById(R.id.textViewTodo);

        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);
        todoTextView.setText(mTodos[mTodoIndex]);

        Button buttonNext;
        buttonNext = findViewById(R.id.buttonNext);

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
        buttonPrev = findViewById(R.id.buttonPrev);

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTodoIndex -= 1;

                if (mTodoIndex == -1)
                    mTodoIndex = mTodos.length - 1;

                todoTextView.setText(mTodos[mTodoIndex]);
            }
        });

        Button buttonDetail;
        buttonDetail = findViewById(R.id.buttonDetail);

        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent
                Intent intent = TodoDetailActivity.newIntent(MainActivity.this, mTodoIndex);
                startActivityForResult(intent, IS_SUCCESS);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState){

        outState.putInt(TODO_INDEX_KEY, mTodoIndex);

        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == IS_SUCCESS ){
            if (intent != null) {
                // data in intent from child activity
                boolean isTodoComplete = intent.getBooleanExtra(IS_TODO_COMPLETE, false);
                updateTodoComplete(isTodoComplete);
            } else {
                Toast.makeText(this, R.string.back_button_pressed, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.request_code_mismatch,
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void updateTodoComplete(boolean is_todo_complete) {

        final TextView textViewTodo;
        textViewTodo = findViewById(R.id.textViewTodo);

        if (is_todo_complete) {
            textViewTodo.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.backgroundSuccess));
            textViewTodo.setTextColor(
                    ContextCompat.getColor(this, R.color.colorSuccess));

            setTextViewComplete("\u2713");
        }

    }

    private void setTextViewComplete( String message ){
        final TextView textViewComplete;
        textViewComplete = findViewById(R.id.textViewComplete);

        textViewComplete.setText(message);
    }



}
