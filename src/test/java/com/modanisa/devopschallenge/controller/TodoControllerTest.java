package com.modanisa.devopschallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modanisa.devopschallenge.DevopsChallengeApplication;
import com.modanisa.devopschallenge.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = DevopsChallengeApplication.class)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addTodo() throws Exception {

        Todo todo = new Todo();
        todo.setMessage("modanisa");
        todo.setIsActive(true);
       String result = mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType("application/json").content(objectMapper.writeValueAsString(todo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
       Todo expectedTodo = objectMapper.readValue(result,Todo.class);
       Assertions.assertTrue(expectedTodo.getId()>0);

    }
    @Test
    public void getTodo() throws Exception {
        Todo todo = new Todo();
        todo.setMessage("modanisa-2");
        todo.setIsActive(true);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType("application/json").content(objectMapper.writeValueAsString(todo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Todo expectedTodo=objectMapper.readValue(result,Todo.class);
        result = mockMvc.perform(MockMvcRequestBuilders.get("/todos/"+expectedTodo.getId()).contentType("application/json"))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Todo expectedResult = objectMapper.readValue(result,Todo.class);
        Assertions.assertTrue(expectedResult.getMessage().equals(todo.getMessage()));

    }
    @Test
    public void updateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setMessage("modanisa-2");
        todo.setIsActive(true);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType("application/json").content(objectMapper.writeValueAsString(todo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Todo expectedTodo = objectMapper.readValue(result,Todo.class);
        expectedTodo.setMessage("Yavuz");
        result = mockMvc.perform(MockMvcRequestBuilders.put("/todos/"+expectedTodo.getId()).contentType("application/json").content(objectMapper.writeValueAsString(expectedTodo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Todo expectedResult = objectMapper.readValue(result,Todo.class);
        Assertions.assertTrue(expectedResult.getMessage().equals(expectedTodo.getMessage()));

    }

    @Test
    public void deleteTodo() throws Exception {
        Todo todo = new Todo();
        todo.setMessage("modanisa-2");
        todo.setIsActive(true);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType("application/json").content(objectMapper.writeValueAsString(todo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Todo expectedTodo=objectMapper.readValue(result,Todo.class);
        result = mockMvc.perform(MockMvcRequestBuilders.delete("/todos/"+expectedTodo.getId()).contentType("application/json").content(objectMapper.writeValueAsString(expectedTodo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Boolean expectedResult = objectMapper.readValue(result,Boolean.class);
        Assertions.assertTrue(expectedResult);

    }
    @Test
    public void listTodo() throws Exception {
        Todo todo = new Todo();
        todo.setMessage("modanisa-2");
        todo.setIsActive(true);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType("application/json").content(objectMapper.writeValueAsString(todo)))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        Todo expectedTodo = objectMapper.readValue(result,Todo.class);
        result= mockMvc.perform(MockMvcRequestBuilders.get("/todos").contentType("application/json"))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        List<Todo> expectedResult=objectMapper.readValue(result, ArrayList.class);
        Assertions.assertTrue(expectedResult.size()>0);

    }
}
