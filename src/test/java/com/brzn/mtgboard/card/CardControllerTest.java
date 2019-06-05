package com.brzn.mtgboard.card;

import com.brzn.mtgboard.card.counter.SearchCounterService;
import com.brzn.mtgboard.card.dto.CardForCardPage;
import com.brzn.mtgboard.card.dto.CardForSearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(CardController.class)
public class CardControllerTest {

    @MockBean
    private CardService cardService;

    @MockBean
    private SearchCounterService searchCounterService;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetCardByPartialNameFromApi() throws Exception {

        String partialName = RandomString.make(5);
        List<CardForSearchResult> emptyList = new ArrayList<>();
        List<CardForSearchResult> cards = new ArrayList<>();
        cards.add(new CardForSearchResult(RandomString.make(3), RandomString.make(4)));
        cards.add(new CardForSearchResult(RandomString.make(3), RandomString.make(4)));
        cards.add(new CardForSearchResult(RandomString.make(3), RandomString.make(4)));

        when(cardService.findAllByPartialName(partialName)).thenReturn(emptyList);
        when(cardService.findAllByPartialNameFromApi(partialName)).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cards/name/like/{name}", partialName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("*").isArray());
    }

    @Test
    public void shouldGetCardByPartialNameFromDatabase() throws Exception {

        String partialName = RandomString.make(5);
        List<CardForSearchResult> cards = new ArrayList<>();
        cards.add(new CardForSearchResult(RandomString.make(3), RandomString.make(4)));
        cards.add(new CardForSearchResult(RandomString.make(3), RandomString.make(4)));
        cards.add(new CardForSearchResult(RandomString.make(3), RandomString.make(4)));

        when(cardService.findAllByPartialName(partialName)).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cards/name/like/{name}", partialName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("*").isArray());
    }

    @Test
    public void shouldGetCardByNameAndSetName() throws Exception {
        String cardName = RandomString.make(3);
        String setName = RandomString.make(5);

        CardForCardPage card = new CardForCardPage();
        card.setName(cardName);
        card.setCardSet(setName);

        when(cardService.getDtoCardByNameAndSetName(anyString(), anyString())).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cards/name/set/{cardName}/{setName}", cardName, setName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.name")).value(cardName))
                .andExpect((jsonPath("$.cardSet")).value(setName));
    }

    @Test
    public void shouldGetCardNotFoundByNameAndSetName() throws Exception {
        String cardName = RandomString.make(3);
        String setName = RandomString.make(5);

        CardForCardPage card = null;

        when(cardService.getDtoCardByNameAndSetName(anyString(), anyString())).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cards/name/set/{cardName}/{setName}", cardName, setName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("*").doesNotExist());
    }

    @Test
    public void shouldPostCardByNameAndSetName() throws Exception {
        String cardName = RandomString.make(3);
        String setName = RandomString.make(5);

        CardForCardPage card = new CardForCardPage();
        card.setName(cardName);
        card.setCardSet(setName);

        when(cardService.postCardByNameAndSetName(anyString(), anyString())).thenReturn(card);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cards/name/set/{cardName}/{setName}", cardName, setName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.name")).value(cardName))
                .andExpect((jsonPath("$.cardSet")).value(setName));
    }

    @Test
    public void shouldNotFoundCardToPostByNameAndSetName() throws Exception {
        String cardName = RandomString.make(3);
        String setName = RandomString.make(5);

        when(cardService.postCardByNameAndSetName(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cards/name/set/{cardName}/{setName}", cardName, setName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("*").doesNotExist());
    }

    @Test
    public void shouldPostCardsByName() throws Exception{

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        cards.add(new Card());
        cards.add(new Card());

        String cardName = RandomString.make(3);

        when(cardService.postCardsByName(anyString())).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cards/name/{cardName}", cardName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("*")).isArray());
    }

    @Test
    public void shouldGetBadRequestWhenPostCardsByNameBecauseNull() throws Exception{

        List<Card> cards = null;

        String cardName = RandomString.make(3);

        when(cardService.postCardsByName(anyString())).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cards/name/{cardName}", cardName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect((jsonPath("*")).doesNotExist());
    }

    @Test
    public void shouldGetBadRequestWhenPostCardsByNameBecauseArrayEmpty() throws Exception{

        List<Card> cards = new ArrayList<>();

        String cardName = RandomString.make(3);

        when(cardService.postCardsByName(anyString())).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cards/name/{cardName}", cardName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect((jsonPath("*")).doesNotExist());
    }

}