package com.springboott.myfirstwebapp.todo.Todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>() {};
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "Dusica",
                "Learn AWS",
                LocalDate.now().plusMonths(6),false));

        todos.add(new Todo(++todosCount, "Dusica",
                "Learn Spring",
                LocalDate.now().plusMonths(3),false));

        todos.add(new Todo(++todosCount, "Dusica",
                "Learn Devops",
                LocalDate.now().plusMonths(7),false));


    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone){
        var todo = new Todo(++todosCount, username, description, targetDate, isDone);
        todos.add(todo);

    }
}
