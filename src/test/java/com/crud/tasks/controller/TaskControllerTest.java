package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DbService dbService;
    @MockBean
    TaskMapper taskMapper;
    @MockBean
    TaskRepository taskRepository;

    @Test
    public void shouldGetTasks() throws Exception {
        Task task1 = new Task( 1L, "title 1", "cont1" );
        Task task2 = new Task( 2L, "title 2", "cont 2" );
        List<Task> taskskList = new ArrayList<>();
        taskskList.add( task1 );
        taskskList.add( task2 );
        List<TaskDto> dtoTasksList = new ArrayList<>();
        dtoTasksList.add( new TaskDto( 3l, "title 3", "cont 3" ) );

        when( dbService.getAllTasks() ).thenReturn( taskskList );
        when( taskMapper.mapToTaskDtoList( taskskList ) ).thenReturn( dtoTasksList );
        //When&Then
        mockMvc.perform( get( "/v1/task/getTasks" ).contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$", hasSize( 1 ) ) )
                .andExpect( jsonPath( "$[0].id", is( 3 ) ) )
                .andExpect( jsonPath( "$[0].title", is( "title 3" ) ) )
                .andExpect( jsonPath( "$[0].content", is( "cont 3" ) ) );


    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        Task task = new Task( 1L, "test", "cont" );
        TaskDto taskDto = new TaskDto(  1L, "test", "cont" );

        when( dbService.getTask( any( Long.class ) ) ).thenReturn( Optional.of( task ) );
        when( taskMapper.mapToTaskDto( task ) ).thenReturn( taskDto );

        //When & Then
        mockMvc.perform(
                get( "/v1/task/getTask" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .param( "taskId", "5" ) )
                .andExpect( status().is( 200 ) )
                .andExpect( jsonPath( "$.id", is( 1 ) ) )
                .andExpect( jsonPath( "$.title", is( taskDto.getTitle() ) ) )
                .andExpect( jsonPath( "$.content", is( taskDto.getContent() ) ) );
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(  2l , "test", "cont" );
        TaskDto taskDto = new TaskDto( 2l , "test", "cont" );
        List<TaskDto> dtoTasksList = new ArrayList<>();
        dtoTasksList.add( new TaskDto( 3l, "test", "cont" ) );
        dtoTasksList.add( taskDto );
        when( taskMapper.mapToTask( taskDto ) ).thenReturn( task );
        when( dbService.saveTask( task ) ).thenReturn( task );
        when( taskMapper.mapToTaskDto( task ) ).thenReturn( taskDto );

        Gson gson = new Gson();
        String jsonContent = gson.toJson( taskDto );

        //When & Then
        mockMvc.perform( put( "/v1/task/updateTask" )
                .contentType( MediaType.APPLICATION_JSON )
                .characterEncoding( "UTF-8" )
                .content( jsonContent ) )
                .andExpect( status().is( 200 ) );
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task( 3L, "test", "cont" );
        TaskDto taskDto = new TaskDto(  3L , "test", "cont" );

        when( taskMapper.mapToTask( taskDto ) ).thenReturn( task );
        when( dbService.saveTask( task ) ).thenReturn( task );

        Gson gson = new Gson();
        String jsonContent = gson.toJson( task );

        //When & Then
        mockMvc.perform( post( "/v1/task/createTask" )
                .contentType( MediaType.APPLICATION_JSON )
                .param( "taskId", "3" )
                .characterEncoding( "UTF-8" )
                .content( jsonContent ) )
                .andExpect( status().is( 200 ) )

        ;



    }
}






