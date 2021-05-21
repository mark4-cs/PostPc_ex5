package exercise.android.reemh.todo_items;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable, Comparable<TodoItem>{
  // TODO: edit this class as you want
    public String description, state;
    public Date createdOn;
    public Date lastModified;

    TodoItem(String description, String status){
        this.description = description;
        this.state = status;
        this.createdOn = new Date();
        this.lastModified = this.createdOn;
    }

    void updateLastModified(Date newDate){
        this.lastModified = newDate;
    }

    @Override
    public int compareTo(TodoItem todoItem) {
        if (this.state.equals("InProgress") & !todoItem.state.equals("InProgress")){
            return -1;
        }
        else if (!this.state.equals("InProgress") & todoItem.state.equals("InProgress")){
            return 1;
        }
        return todoItem.createdOn.compareTo(createdOn);
//        return this.createdOn.compareTo(todoItem.createdOn);
    }

}
