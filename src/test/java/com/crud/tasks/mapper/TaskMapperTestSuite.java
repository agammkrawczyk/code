package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;
    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task", "test");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals( taskDto.getContent(),task.getContent() );
        assertEquals( taskDto.getTitle(),task.getTitle() );
    }
    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "task", "test");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals( task.getContent(),taskDto.getContent() );
        assertEquals( task.getTitle(),taskDto.getTitle() );
    }
    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "task1", "test1"));
        taskList.add(new Task(2L, "task2", "test2"));
        //When
        List<TaskDto>taskDtoList=taskMapper.mapToTaskDtoList(taskList);
        assertEquals(2, taskDtoList.size());
        assertEquals(taskList.get(0).getId(), taskDtoList.get(0).getId());
        assertEquals( "task1",taskList.get( 0 ).getTitle());
        assertEquals( taskList.get( 1 ).getTitle(),taskDtoList.get( 1 ).getTitle() );
        assertEquals( taskList.get( 1 ).getId(),taskDtoList.get( 1 ).getId() );
        assertEquals( "test2",taskList.get( 1 ).getContent());

    }
}
