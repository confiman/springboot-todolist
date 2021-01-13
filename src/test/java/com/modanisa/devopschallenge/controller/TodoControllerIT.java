package com.modanisa.devopschallenge.controller;

import com.modanisa.devopschallenge.DevopsChallengeApplication;
import com.modanisa.devopschallenge.model.Todo;
import com.modanisa.devopschallenge.repository.TodoRepository;
import org.json.JSONException;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.boot.context.embedded.LocalServerPort;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        Todo todo = todoRepository
                .save(new Todo(1, "yavuz", true));
        Todo foundTodo = todoRepository.findById(todo.getId()).orElse(null);

        Assert.assertNotNull(foundTodo);
        Assert.assertEquals(todo.getMessage(), foundTodo.getMessage());
    }

    @Test
    public void testRetrieveTodo() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/todos/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1,\"message\":\"yavuz\",\"isActive\":true}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void addTodo() {

        Todo todo = new Todo(2,"modanisa",true);

        HttpEntity<Todo> entity = new HttpEntity<Todo>(todo, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/todos"),
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        Assert.assertTrue(actual.contains("/students/Student1/courses/"));

    }

}
