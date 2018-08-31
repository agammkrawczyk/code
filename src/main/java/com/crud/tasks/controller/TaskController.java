package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/v1/task")
@RestController
public class TaskController {
@Autowired
private DbService service;
@Autowired
private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return  taskMapper.mapToTaskDtoList( service.getAllTasks() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask( @RequestParam Long taskId) throws TaskNotFoundException {
          return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) throws TaskNotFoundException{
        service.deleteTask(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }


    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return  taskMapper.mapToTaskDto(service.saveTask((taskMapper.mapToTask(taskDto))));

    }
    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes =APPLICATION_JSON_VALUE )
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}

