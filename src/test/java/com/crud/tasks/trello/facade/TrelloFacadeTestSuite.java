package com.crud.tasks.trello.facade;
import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.mappper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {
    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add( new TrelloListDto( "1", "test_list", false ) );

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add( new TrelloBoardDto( "1", "test", trelloLists ) );

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add( new TrelloList( "1", "test_list", false ) );

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add( new TrelloBoard( "1", "test", mappedTrelloLists ) );

        when( trelloService.fetchTrelloBoards() ).thenReturn( trelloBoards );
        when( trelloMapper.mapToBoards( trelloBoards ) ).thenReturn( mappedTrelloBoards );
        when( trelloMapper.mapToBoardsDto( anyList() ) ).thenReturn( new ArrayList<>() );
        when( trelloValidator.validateTrelloBoards( mappedTrelloBoards ) ).thenReturn( new ArrayList<>() );

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull( trelloBoardDtos );
        assertEquals( 0, trelloBoardDtos.size() );
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add( new TrelloListDto( "1", "my_list", false ) );

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add( new TrelloBoardDto( "1", "my_task", trelloLists ) );

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add( new TrelloList( "1", "my_list", false ) );

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add( new TrelloBoard( "1", "my_task", mappedTrelloLists ) );

        when( trelloService.fetchTrelloBoards() ).thenReturn( trelloBoards );
        when( trelloMapper.mapToBoards( trelloBoards ) ).thenReturn( mappedTrelloBoards );
        when( trelloMapper.mapToBoardsDto( anyList() ) ).thenReturn( trelloBoards );
        when( trelloValidator.validateTrelloBoards( mappedTrelloBoards ) ).thenReturn( mappedTrelloBoards );

        //When
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull( trelloBoardDtos );
        assertEquals( 1, trelloBoardDtos.size() );

        trelloBoardDtos.forEach( trelloBoardDto -> {
            assertEquals( "1", trelloBoardDto.getId() );
            assertEquals( "my_task", trelloBoardDto.getName() );

            trelloBoardDto.getLists().forEach( trelloListDto -> {
                assertEquals( "1", trelloListDto.getId() );
                assertEquals( "my_list", trelloListDto.getName() );
                assertEquals( false, trelloListDto.isClosed() );
            } );
        } );
    }

    @Test
    public void shoultCreateTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard( "Card", "desc1", "pos1", "1" );
        TrelloCardDto trelloCardDto1 = new TrelloCardDto( "Card2", "dsc2", "pos2", "2" );
        TrelloCardDto trelloCardDto2 = new TrelloCardDto( "Card3", "dsc3", "pos3", "3" );
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto( "1", "title", "http://localhost:8080/" );
        when( trelloMapper.mapToTrelloCard( trelloCardDto1 ) ).thenReturn( trelloCard );
        when( trelloMapper.mapToTrelloCardDto( trelloCard ) ).thenReturn( trelloCardDto2 );
        when( trelloService.createdTrelloCard( trelloCardDto2 ) ).thenReturn( createdTrelloCardDto );
        //When
        CreatedTrelloCardDto createdTrelloCardDtoResult = trelloFacade.createCard( trelloCardDto1 );
        //Then
        assertEquals( "1", createdTrelloCardDtoResult.getId() );
        assertEquals( "title", createdTrelloCardDtoResult.getName() );
        assertEquals( "http://localhost:8080/", createdTrelloCardDtoResult.getShortUrl() );
    }


}