package exercise.android.reemh.todo_items;

import android.app.Application;

import java.util.ArrayList;

public class TodoApplication extends Application {

    private static TodoItemsHolder holder = null;
    public static TodoItemsHolder getHolder(){return holder;}

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        holder = new TodoItemsHolderImpl(new ArrayList<>(), this);}

    private static TodoApplication instance = null;
    public static TodoApplication getTodoApplication(){return instance;}
}
