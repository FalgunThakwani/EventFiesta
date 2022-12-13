package group11.EventFiesta.UserEventChecklistTest;

import group11.EventFiesta.UserEventChecklist.UserEventChecklistHandler;
import group11.EventFiesta.model.TodoChecklist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserEventChecklistTest
{
    @Test
    public void getChecklistTest()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist(2);

            Assertions.assertEquals(1, todoChecklists.size());

            TodoChecklist todo = todoChecklists.get(0);

            Assertions.assertEquals(1, todo.getId());
            Assertions.assertEquals("one", todo.getName());
            Assertions.assertEquals(1, todo.getStatus());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void addItemToChecklistTest()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            userEventChecklistHandler.addItemToChecklist(2, "two");
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist(2);

            Assertions.assertEquals(3, todoChecklists.size());

            TodoChecklist todo = todoChecklists.get(0);

            Assertions.assertEquals(1, todo.getId());
            Assertions.assertEquals("two", todo.getName());
            Assertions.assertEquals(0, todo.getStatus());

            todo = todoChecklists.get(1);

            Assertions.assertEquals(2, todo.getId());
            Assertions.assertEquals("one", todo.getName());
            Assertions.assertEquals(1, todo.getStatus());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void removeChecklistItemTest()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist(2);
            Assertions.assertEquals(1, todoChecklists.size());
            boolean result = userEventChecklistHandler.removeItem(1);
            Assertions.assertTrue(result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    @Test
    public void getChecklistItemNotMatchingTest()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist(10);
            System.out.println(todoChecklists);
            Assertions.assertNotEquals(10, todoChecklists.get(0).getEventID());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void removeChecklistItemNotPresentTest()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist(2);
            Assertions.assertEquals(1, todoChecklists.size());
            boolean result = userEventChecklistHandler.removeItem(10);
            Assertions.assertFalse(result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
