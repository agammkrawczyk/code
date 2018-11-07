package com.crud.tasks.trello.mapper;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.mappper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add( new TrelloListDto( "1", "list", false ) );

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList( trelloListDto );
        //Then
        assertEquals( 1, trelloLists.size() );
        assertEquals( "list", trelloLists.get( 0 ).getName() );
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add( new TrelloList( "1", "list", false ) );
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto( trelloList );
        //Then
        assertEquals( 1, trelloListDto.size() );
        assertEquals( "list", trelloListDto.get( 0 ).getName() );
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "tes2", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("5", "test Board", trelloListsDto);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("5", trelloBoardList.get(0).getId());
        assertEquals("test Board", trelloBoardList.get(0).getName());

    }


    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto( "card1", "desc ", "pos", "1" );
        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard( trelloCardDto );
        //Then
        assertEquals( trelloCardDto.getName(), trelloCard.getName() );
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard( "card1", "desc", "pos", "1" );
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto( trelloCard );
        //Then
        assertEquals( trelloCard.getName(), trelloCardDto.getName() );
    }


    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "dsc", "post", "5");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);
        //Then
        assertEquals("test card", trelloCardDto.getName());
        assertEquals("dsc", trelloCardDto.getDescription());
        assertEquals("post", trelloCardDto.getPos());
        assertEquals("5", trelloCardDto.getListId());
    }
}