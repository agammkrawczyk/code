package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuit {
    @InjectMocks
    DbService dbService;
    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task( 1L, "task", "test" );
        taskList.add( task );
        when( taskRepository.findAll() ).thenReturn( taskList );
        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();
        //Then
        assertEquals( 1, fetchedTaskList.size() );
        assertEquals( "task", fetchedTaskList.get( 0 ).getTitle() );
        assertEquals( "test", fetchedTaskList.get( 0 ).getContent() );
        assertEquals( new Long( 1 ), fetchedTaskList.get( 0 ).getId() );
    }


    @Test
    public void getTaskTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task( 1L, "task", "test" );
        Task task2 = new Task( 2L, "title", "test" );
        taskList.add( task1 );
        taskList.add( task2 );
        Long id = 1L;

        when( taskRepository.findById( id ) ).thenReturn( java.util.Optional.ofNullable( task1 ) );
        //When
        Optional<Task> taskFind = dbService.getTask( id );
        //Then
        assertEquals( id, taskFind.get().getId() );
        assertEquals( "task", taskFind.get().getTitle() );
        assertEquals( "test", taskFind.get().getContent() );
        assertEquals( 2, taskList.size() );
    }

    @Test
    public void saveTaskTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task( 1L, "task", "test" );
        Task task2 = new Task( 2L, "title", "test" );
        taskList.add( task1 );
        taskList.add( task2 );
        Long id = 1L;
        when( taskRepository.save( task1 ) ).thenReturn( (task1) );
        //When
        Task taskSaved = dbService.saveTask( task1 );
        //Then
        assertEquals( "task", taskSaved.getTitle() );
        assertEquals( "test", taskSaved.getContent() );
        assertEquals( new Long( 1 ), taskSaved.getId() );

    }

       @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(1l, "task", "test task");
        dbService.saveTask(task);
        Long id = 1L;
        //When
        dbService.deleteTaskID(id);
        //Then
        verify(taskRepository, times(1)).delete(id);
    }
    }
