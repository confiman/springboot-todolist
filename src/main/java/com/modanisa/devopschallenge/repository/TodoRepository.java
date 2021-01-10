package com.modanisa.devopschallenge.repository;

import com.modanisa.devopschallenge.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
