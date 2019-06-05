package com.brzn.mtgboard.user;

import com.brzn.mtgboard.offer.Offer;
import com.brzn.mtgboard.offer.OfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(SecuredUserController.class)
public class SecuredUserControllerTest {

    @MockBean
    private SecuredUserService securedUserService;

    @MockBean
    private OfferService offerService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldAddCardOffer() throws Exception {

        Offer offer = new Offer();
        offer.setId(1L);

        String id = RandomString.make(2);
        String token = RandomString.make(60);
//        ServletRequest request = mock(ServletRequest.class);

        Mockito.when(securedUserService.checkUserToken(any(ServletRequest.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/cards")
                .content(mapper.writeValueAsString(offer))
                .contentType(MediaType.APPLICATION_JSON)
                .header("authId", id)
                .header("authToken", token))
                .andExpect(status().isCreated());
    }
}