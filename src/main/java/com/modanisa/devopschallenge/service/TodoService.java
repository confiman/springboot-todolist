package com.modanisa.devopschallenge.service;

import com.modanisa.devopschallenge.model.Todo;
import com.modanisa.devopschallenge.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    public List<Todo> listAllTodo() {
        return todoRepository.findAll();
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodo(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Boolean deleteTodo(int id) {
        todoRepository.deleteById(id);
        return true;
    }

}
