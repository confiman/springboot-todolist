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

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return new ResponseEntity<List<Todo>>(todoService.listAllTodo(), HttpStatus.OK);
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
    @PostMapping
    public ResponseEntity<Todo> add(@RequestBody Todo todo) {
        Todo user=todoService.saveTodo(todo);
        return new ResponseEntity<Todo>(user, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@RequestBody Todo todo, @PathVariable Integer id) {
        try {
            Todo existTodo = todoService.getTodo(id);

            return new ResponseEntity<>( todoService.saveTodo(todo),HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {

      return new  ResponseEntity<Boolean>( todoService.deleteTodo(id),HttpStatus.OK);
    }
}
