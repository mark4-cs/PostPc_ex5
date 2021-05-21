package exercise.android.reemh.todo_items;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public String toStringRep(){
        return description + "#" + state + "#" + createdOn.toString() + "#" + lastModified.toString();
    }

    public void changeFromString(String repr) throws ParseException {
        String[] vals = repr.split("#", 4);
        this.description = vals[0];
        this.state = vals[1];
        this.createdOn = new SimpleDateFormat("EEE MMMM dd HH:mm:ss zzz yyyy").parse(vals[2]);
        this.lastModified = new SimpleDateFormat("EEE MMMM dd HH:mm:ss zzz yyyy").parse(vals[3]);
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

};


