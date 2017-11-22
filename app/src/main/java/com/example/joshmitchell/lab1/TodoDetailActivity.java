package com.example.joshmitchell.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Josh Mitchell on 22/11/2017.
 */

public class TodoDetailActivity extends AppCompatActivity {

    private static final String TODO_INDEX = "com.example.todoIndex";
    private static final String IS_TODO_COMPLETE = "com.example.isTodoComplete";

    private String[] mTodos;

    private int todoIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_detail_activity);

        int todoIndex = getIntent().getIntExtra(TODO_INDEX, 0);

        //handle display
        final TextView todoTextView;
        todoTextView = findViewById(R.id.textViewTodo);

        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);
        todoTextView.setText(mTodos[todoIndex]);

    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.checkbox_isComplete:
                if (checked) {
                    Toast.makeText(TodoDetailActivity.this, "Hurray, it's done!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TodoDetailActivity.this, "Maybe next time!", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent();
                intent.putExtra(IS_TODO_COMPLETE, checked);
                setResult(RESULT_OK, intent);

            default:
                break;
        }
    }

    public static Intent newIntent(Context packageContext, int todoIndex){
        Intent intent = new Intent(packageContext, TodoDetailActivity.class);
        intent.putExtra(TODO_INDEX, todoIndex);
        return intent;
    }

    public void onSavedInstance(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, todoIndex);
    }


}
