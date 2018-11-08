package com.crud.tasks.trello.config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertEquals;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTestSuite {
    @Autowired
    TrelloConfig trelloConfig;
    @Test
    public void testTrelloConfig() {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloAppToken = trelloConfig.getTrelloToken();
        String trelloAppUsername = trelloConfig.getUsername();
        //Then
        assertEquals("https://api.trello.com/1", trelloApiEndpoint);
        assertEquals("10ef707c44a9951700a52054af8cf5a9", trelloAppKey);
        assertEquals("d8d3019ca784b6b49184e0ab463da0b47b73737b46b7731d474d4071d755386e", trelloAppToken);
        assertEquals("agnieszkakrawczyk9", trelloAppUsername);
    }
}
