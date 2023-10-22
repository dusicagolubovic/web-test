package com.springboott.myfirstwebapp.todo.Todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // /list-todo
    @RequestMapping("list-todo")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("dusica");
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value= "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap map) {
        String username = (String)map.get("name");
        Todo todo = new Todo(0, username, "Default description", LocalDate.now().plusYears(1), false);
        map.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value= "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        // TODO: add processing a new todo item

        if (result.hasErrors()) {
            // if there's error return to the todo page
            return "todo";
        }
        // dodajemo redirekciju, da bismo imali informacije o pre, da se ne bi pravio novi reques
        todoService.addTodo((String)model.get("name"), todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:list-todo";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        // delete to-do with specific id
        todoService.deleteById(id);
        return "redirect:list-todo";
    }


    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showeUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo",todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = (String)model.get("name");

        todo.setUsername(username);

        todoService.updateTodo(todo);
        return "redirect:list-todo";
    }
}
