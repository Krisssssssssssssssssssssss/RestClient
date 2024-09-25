package org.example.api.rickmorty.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    void getAllCharacters() throws Exception {
        //GIVEN
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "info": {
                                        "count": 32,
                                        "pages": 42
                                    },
                                    "results": [
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "status": "Alive",
                                            "species": "Human"
                                        }
                                    ]
                                }
                                """,
                        MediaType.APPLICATION_JSON));

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
              [
                {
                  "id":1,
                  "name":"Rick Sanchez",
                   "species":"Human"}
                ]
                        """));
    }

        @Test
        void getCharacterById () {
        }

        @Test
        void getCharactersWithStatus () {
        }

        @Test
        void getSpeciesStatistic () {
        }

        @Test
        void noSuchElementExceptionHandler () {
        }
}