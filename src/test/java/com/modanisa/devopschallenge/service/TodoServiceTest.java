package com.modanisa.devopschallenge.service;

import com.modanisa.devopschallenge.DevopsChallengeApplication;
import com.modanisa.devopschallenge.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = DevopsChallengeApplication.class)
public class TodoServiceTest {
    @Autowired
    TodoService todoService;
    @Test
    public  void listAllTodo(){
        Todo todo = new Todo();
        todo.setMessage("test");
        todoService.saveTodo(todo);
        todo = new Todo();
        todo.setMessage("test-2");
        todoService.saveTodo(todo);
        List<Todo> result = todoService.listAllTodo();
        Assertions.assertTrue(result.size()>0);
    }
    @Test
    public  void saveTodo(){
        Todo todo=new Todo();
        todo.setMessage("test");
        Todo expectedTodo = todoService.saveTodo(todo);
        Assertions.assertTrue(todo.getMessage().equals(expectedTodo.getMessage()));
        Assertions.assertTrue(todo.getId()>0);
    }
    @Test
    public void getTodo(){
        Todo todo=new Todo();
        todo.setMessage("test");
        Todo result = todoService.saveTodo(todo);
        Todo expectedResult = todoService.getTodo(result.getId());
        Assertions.assertTrue(expectedResult.getId()==result.getId());
    }
    @Test
    public void deleteTodo(){
        Todo todo=new Todo();
        todo.setMessage("test");
        Todo result = todoService.saveTodo(todo);
        todoService.deleteTodo(result.getId());
        Todo expectedResult = todoService.getTodo(result.getId());
        Assertions.assertTrue(expectedResult==null);
    }
}
