package exercise.android.reemh.todo_items;

import android.content.Context;
import android.view.View;

import java.util.List;


// TODO: feel free to add/change/remove methods as you want
public interface TodoItemsHolder{

  /** Get a copy of the current items list */
  List<TodoItem> getCurrentItems();

  /**
   * Creates a new TodoItem and adds it to the list, with the @param description and status=IN-PROGRESS
   * Subsequent calls to [getCurrentItems()] should have this new TodoItem in the list
   */
  int addNewInProgressItem(String description);

  /** mark the @param item as DONE */
  int markItemDone(TodoItem item);

  /** mark the @param item as IN-PROGRESS */
  int markItemInProgress(TodoItem item);

  /** delete the @param item */
  void deleteItem(TodoItem item);

  void setContext(Context ctx);

  void changeDescription(TodoItem item, String newDescription);
}
