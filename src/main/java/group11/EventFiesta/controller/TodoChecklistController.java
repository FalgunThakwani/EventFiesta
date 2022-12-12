package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.UserEventChecklist.UserEventChecklistHandler;
import group11.EventFiesta.model.TodoChecklist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoChecklistController
{
    private List<TodoChecklist> todoList = new LinkedList<>();

    @GetMapping("/load")
    public String getTodoList(Model model) throws Exception
    {
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(new MySQLDBPersistence());
        List<TodoChecklist> userEventToDoList = userEventChecklistHandler.getChecklist("123");
        todoList.clear();
        for(TodoChecklist item: userEventToDoList)
        {
            if(item.getStatus() == 1)
                continue;
            TodoChecklist todoItem = new TodoChecklist();
            todoItem.setEventID(item.getEventID());
            todoItem.setId(item.getId());
            todoItem.setName(item.getName());
            todoItem.setStatus(item.getStatus());
            todoList.add(todoItem);
        }
        System.out.println("LIST = " + todoList.toString());
        model.addAttribute("todoList", todoList);
        model.addAttribute("todoItem", new TodoChecklist());
        return "TodoChecklist";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("todoItem") TodoChecklist item, Model model) throws Exception
    {
        System.out.println("Todo Item to add " + item.toString());
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(new MySQLDBPersistence());
        List<TodoChecklist> userEventToDoList = userEventChecklistHandler.addItemToChecklist("123", item.getName());
        return "redirect:/todo/load";
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute("todoItem") TodoChecklist item, Model model)
    {
        System.out.println("Item to remove" + item.toString());
        TodoChecklist itemToDelete = null;
        for(TodoChecklist element: todoList)
        {
            if(element.getId() == item.getId())
            {
                itemToDelete = element;
                break;
            }
        }
        UserEventChecklistHandler userEventChecklistHandler = new UserEventChecklistHandler(new MySQLDBPersistence());
        System.out.println("In Todo Controller. Calling removeItem() for " + itemToDelete.getId());
        userEventChecklistHandler.removeItem(itemToDelete.getId());
        return "redirect:/todo/load";
    }
}