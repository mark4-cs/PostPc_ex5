package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EditTodoActivity extends AppCompatActivity {

    public TodoItemsHolder holder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        if (holder == null){holder = TodoApplication.getHolder();}
        Intent intentOpenedMe = getIntent();
//        TodoItem item = (TodoItem) intentOpenedMe.getSerializableExtra("item");
        int itemIdx = intentOpenedMe.getIntExtra("item_idx", -1);
        TodoItem item = holder.getCurrentItems().get(itemIdx);

        TextView created_date = findViewById(R.id.created_date);
        TextView modified_date = findViewById(R.id.modified_date);
        Switch switch_InProgress_Done = findViewById(R.id.switch_InProgress_Done);
        EditText editTextInsertTask = findViewById(R.id.editTextInsertTask);
        FloatingActionButton buttonEditTodoItem = findViewById(R.id.buttonEditTodoItem);

        //setup current info
        created_date.setText("Created on: " + item.createdOn.toString());
        modified_date.setText("Modified on: " + getModifiedDate(item.lastModified));
        switch_InProgress_Done.setChecked(item.state.equals("Done"));
        editTextInsertTask.setText(item.description);

        buttonEditTodoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldDesc = item.description;
                String newDesc = editTextInsertTask.getText().toString();
                if (oldDesc.equals(newDesc)){
                    Toast.makeText(TodoApplication.getTodoApplication(), "Description did not change!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(TodoApplication.getTodoApplication(), "Changes saved!", Toast.LENGTH_LONG).show();
                    holder.changeDescription(item, newDesc);
                    item.updateLastModified(new Date());
                    modified_date.setText("Modified on: " + getModifiedDate(item.lastModified));
                }
            }
        });

        switch_InProgress_Done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){holder.markItemDone(item);}
                else{holder.markItemInProgress(item);}
                item.updateLastModified(new Date());
                modified_date.setText("Modified on: " + getModifiedDate(item.lastModified));
            }
        });
    }

    private String getModifiedDate(Date d){
        Date cur = new Date();
        Calendar cur_cal = Calendar.getInstance();

        long diffinMillis = Math.abs(d.getTime() - cur.getTime());
        int diff = (int) TimeUnit.MINUTES.convert(diffinMillis, TimeUnit.MILLISECONDS);
        if (diff < 60) {return diff + " minutes ago";}

        cur_cal.setTime(cur);
        int cur_day = cur_cal.get(Calendar.DAY_OF_WEEK);
        cur_cal.setTime(d);
        int last_day = cur_cal.get(Calendar.DAY_OF_WEEK);

        if ((cur_day == last_day) & diff < 60*25){return "Today at " + (cur_cal.get(Calendar.HOUR_OF_DAY) + 1);}
        return cur_cal.get(Calendar.DATE) + " at " + cur_cal.get(Calendar.HOUR_OF_DAY);
    }
}
