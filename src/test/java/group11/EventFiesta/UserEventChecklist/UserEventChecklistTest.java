package group11.EventFiesta.UserEventChecklist;

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
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist("1");

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
    public void addItemToChecklist()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            userEventChecklistHandler.addItemToChecklist("1", "1");
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist("1");

            Assertions.assertEquals(3, todoChecklists.size());

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
    public void removeChecklistItem()
    {
        MockUserEventChecklistPersistence mockDb = new MockUserEventChecklistPersistence();
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(mockDb);

        try
        {
            List<TodoChecklist> todoChecklists = userEventChecklistHandler.getChecklist("1");
            Assertions.assertEquals(1, todoChecklists.size());
            boolean result = userEventChecklistHandler.removeItem(1);
            Assertions.assertTrue(result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
