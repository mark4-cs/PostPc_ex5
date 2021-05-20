package exercise.android.reemh.todo_items;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Application;
import android.content.Context;

import com.google.android.material.button.MaterialButton;

// TODO: implement!
public class TodoItemsHolderImpl  implements TodoItemsHolder, Serializable{
  private List<TodoItem> items;


  TodoItemsHolderImpl(List<TodoItem> items){this.items = new ArrayList<>(items);}

  @Override
  public List<TodoItem> getCurrentItems() {return new ArrayList<>(this.items);}

  @Override
  public int addNewInProgressItem(String description) {
    TodoItem i = new TodoItem(description, "InProgress");
    items.add(i);
    Collections.sort(this.items);
    return this.items.indexOf(i);
  }

  @Override
  public int markItemDone(TodoItem item) {
    items.get(items.indexOf(item)).state = "Done";
    Collections.sort(this.items);
    return this.items.indexOf(item);
  }

  @Override
  public int markItemInProgress(TodoItem item) {
    items.get(items.indexOf(item)).state = "InProgress";
    Collections.sort(this.items);
    return this.items.indexOf(item);
  }

  @Override
  public void deleteItem(TodoItem item) {
    items.remove(item);
  }

};

class MyHolder extends RecyclerView.ViewHolder{
  public  CheckBox checkBox;
  public TextView checkBoxText;
  public MyHolder(@NonNull View itemView) {
    super(itemView);
    checkBox = itemView.findViewById(R.id.checkBox);
    checkBoxText = itemView.findViewById(R.id.checkBoxTextView);
  }
};

class MyAdapter extends RecyclerView.Adapter<MyHolder>{
  private TodoItemsHolder itemsHolder;
  private Context ctx;

  public MyAdapter(TodoItemsHolder todoItemsHolder, Context ctx){
    this.itemsHolder = todoItemsHolder; this.ctx = ctx;
  }

  @NonNull
  @Override
  public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull MyHolder holder, int position) {
    position = holder.getLayoutPosition();
    TodoItem item = itemsHolder.getCurrentItems().get(position);
    holder.checkBoxText.setText(item.description);
    if (!itemsHolder.getCurrentItems().get(position).state.equals("InProgress")){
      holder.checkBoxText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
      holder.checkBox.setChecked(true);
    }
    else{
      holder.checkBoxText.setPaintFlags(0);
      holder.checkBox.setChecked(false);
    }

    holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int position = holder.getLayoutPosition();
        if (itemsHolder.getCurrentItems().get(position).state.equals("InProgress")){
          int newPos = itemsHolder.markItemDone(itemsHolder.getCurrentItems().get(position));
          holder.checkBoxText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
          notifyItemMoved(position, newPos);
        }
        else{
          int newPos = itemsHolder.markItemInProgress(itemsHolder.getCurrentItems().get(position));
          holder.checkBoxText.setPaintFlags(0);
          notifyItemMoved(position, newPos);
        }
      }
    });

    holder.checkBoxText.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        int pos = holder.getLayoutPosition();
        TodoItem item = itemsHolder.getCurrentItems().get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle("Do you want to remove this task?");
        builder.setMessage(item.description);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    itemsHolder.deleteItem(item);
                    notifyItemRemoved(pos);
                  }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
          }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
      }
    });

    holder.checkBoxText.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int pos = holder.getLayoutPosition();
        TodoItem item = itemsHolder.getCurrentItems().get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle("blablalblasldasda");
        builder.setMessage(item.description);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
          }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
          }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return itemsHolder.getCurrentItems().size();
  }
};


