package com.todo.test.controller;

import com.todo.test.DTO.ToDoDTO;
import com.todo.test.entity.ToDo;
import com.todo.test.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")

public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    @Operation(summary = "Create a new ToDo")
    @PostMapping
    public ToDo createToDo(@RequestBody ToDoDTO toDoDTO) {
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoDTO.getTitle());
        toDo.setCompleted(toDoDTO.isCompleted());
        return toDoRepository.save(toDo);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable Long id, @RequestBody ToDo toDoDetails) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(()->new RuntimeException("ToDo not found with id: "+id));
        toDo.setTitle(toDoDetails.getTitle());
        toDo.setCompleted(toDoDetails.isCompleted());
        return toDoRepository.save(toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable Long id) {
        toDoRepository.deleteById(id);
    }
}
