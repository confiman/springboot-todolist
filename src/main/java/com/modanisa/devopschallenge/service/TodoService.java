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

    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo getTodo(Integer id) {
        return todoRepository.findById(id).get();
    }

    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }

}
