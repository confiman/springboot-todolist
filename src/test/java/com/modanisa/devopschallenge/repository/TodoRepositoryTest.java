package com.modanisa.devopschallenge.repository;

import com.modanisa.devopschallenge.DevopsChallengeApplication;
import com.modanisa.devopschallenge.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = DevopsChallengeApplication.class)
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;
    @Test
    public void save(){
        Todo todo = new Todo();
        todo.setMessage("test");
        Todo result = todoRepository.save(todo);
        Assertions.assertTrue(todo.getMessage().equals(result.getMessage()));
        Assertions.assertTrue(todo.getId()>0);
    }
    @Test
    public void findById(){
        Todo todo = new Todo();
        todo.setMessage("test");
        Todo result = todoRepository.save(todo);
        Optional<Todo> expectedResult = todoRepository.findById(result.getId());
        Assertions.assertTrue(expectedResult.isPresent());
    }
    @Test
    public void deleteById(){
        Todo todo = new Todo();
        todo.setMessage("test");
        Todo result = todoRepository.save(todo);
         todoRepository.deleteById(result.getId());
        Optional<Todo> expectedResult = todoRepository.findById(result.getId());
        Assertions.assertTrue(!expectedResult.isPresent());
    }
}
