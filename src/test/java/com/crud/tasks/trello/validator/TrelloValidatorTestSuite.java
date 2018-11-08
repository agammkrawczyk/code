package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {
    @Mock
    TrelloValidator trelloValidator;
    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test1", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloBoard> filtredTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(2, filtredTrelloBoards.size());
        assertEquals(  trelloBoard1.getName(),filtredTrelloBoards.get( 0 ).getName());
        assertEquals( trelloBoard1.getId(),filtredTrelloBoards.get( 0 ).getId() );
        assertEquals( trelloBoard2.getName(),filtredTrelloBoards.get( 1 ).getName() );
        assertEquals( trelloBoard2.getId(),filtredTrelloBoards.get( 1 ).getId() );
    }
    @Test
    public void testValidateCard() {
        TrelloCard trelloCard1 = new TrelloCard("test1", "desc", "post", "1");
        TrelloCard trelloCard2 = new TrelloCard("test2", "desc", "post", "1");
        trelloValidator.validateCard(trelloCard1);
        trelloValidator.validateCard(trelloCard2);
        verify(trelloValidator, times(1)).validateCard(trelloCard1);
        verify(trelloValidator, times(1)).validateCard(trelloCard2);
    }
}
