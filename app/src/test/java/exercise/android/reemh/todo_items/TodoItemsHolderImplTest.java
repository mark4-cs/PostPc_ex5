package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TodoItemsHolderImplTest{

  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  // TODO: add at least 10 more tests to verify correct behavior of your implementation of `TodoItemsHolderImpl` class

  @Test
  public void when_adding_and_removing_size_zero(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());

  }

  @Test
  public void when_adding_two_size_two(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping two");

    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_changing_to_done_state_done(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals("Done", holderUnderTest.getCurrentItems().get(0).state);
  }

  @Test
  public void when_changing_to_InProgress_state(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals("InProgress", holderUnderTest.getCurrentItems().get(0).state);
  }

  @Test
  public void when_changing_to_InProgress_state_while_inProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals("InProgress", holderUnderTest.getCurrentItems().get(0).state);
  }

  @Test
  public void when_changing_to_Done_state_while_Done(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    // verify
    assertEquals("Done", holderUnderTest.getCurrentItems().get(0).state);
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    // verify
    assertEquals("Done", holderUnderTest.getCurrentItems().get(0).state);
  }

  @Test
  public void check_todoItem_sorted_by_date(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping2");
    // verify
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).description);
  }

  @Test
  public void check_todoItem_sorted_by_done(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping2");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    // verify
    assertEquals("do shopping2", holderUnderTest.getCurrentItems().get(0).description);
  }

  @Test
  public void check_sorted_correct_after_changing_state_swice(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl(new ArrayList<>());
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping2");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(1));
    // verify
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).description);
  }
}