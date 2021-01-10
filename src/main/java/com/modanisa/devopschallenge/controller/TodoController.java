package com.modanisa.devopschallenge.controller;

import com.modanisa.devopschallenge.model.Todo;
import com.modanisa.devopschallenge.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("")
    public List<Todo> list() {
        return todoService.listAllTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> get(@PathVariable Integer id) {
        try {
            Todo user = todoService.getTodo(id);
            return new ResponseEntity<Todo>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Todo todo) {
        todoService.saveTodo(todo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Todo todo, @PathVariable Integer id) {
        try {
            Todo existTodo = todoService.getTodo(id);
            todo.setId(id);
            todoService.saveTodo(todo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        todoService.deleteTodo(id);
    }
}
