package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    RestTemplate restTemplate;
    @Mock
    SimpleEmailService simpleEmailService;
    @Mock
    AdminConfig adminConfig;

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "dsc", "top", "id");
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com"
        );
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");
        //When
        CreatedTrelloCardDto newCard = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

   // public List<TrelloBoardDto> fetchTrelloBoards(){
     //   return  trelloClient.getTrelloBoards();
    @Test
    public void fetchTrelloBoardsTest(){
        //Given
        List<TrelloBoardDto> listTrello= new ArrayList<>(  );
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto( "1","test",new ArrayList<>(  ) );
        listTrello.add( trelloBoardDto );
        //When
        when( trelloClient.getTrelloBoards() ).thenReturn( listTrello );
        List<TrelloBoardDto> fetchedTrelloBoardsList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals( 1, fetchedTrelloBoardsList.size() );
        assertEquals( "1",fetchedTrelloBoardsList.get( 0 ).getId() );
        assertEquals( "test",fetchedTrelloBoardsList.get( 0 ).getName() );

    }

}
