package com.springboott.myfirstwebapp.todo.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String showNewTodoPage() {

        return "todo";
    }

    @RequestMapping(value= "add-todo", method = RequestMethod.POST)
    public String addNewTodo(@RequestParam String description, ModelMap model) {

        // TODO: add processing a new todo item
        // dodajemo redirekciju, da bismo imali informacije o pre, da se ne bi pravio novi reques
        todoService.addTodo((String)model.get("name"), description, LocalDate.now().plusYears(1), false);
        return "redirect:list-todo";
    }
}
