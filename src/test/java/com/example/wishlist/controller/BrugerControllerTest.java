package com.example.wishlist.controller;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.service.BaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(BrugerController.class)
public class BrugerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BaseService baseService;

    private Bruger bruger;

    @BeforeEach
    void setUp(){
        bruger = new Bruger("Hans","hans1234");
        when(baseService.logInd("testuser", "testpassword")).thenReturn(true);
        when(baseService.logInd("wronguser", "wrongpassword")).thenReturn(false);
    }

    @Test
    void testGemBruger() throws Exception{
        doNothing().when(baseService).gemBruger(any(Bruger.class));

        mockMvc.perform(post("/bruger/gem")
                        .flashAttr("bruger", bruger))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testBrugerLogInd_Succes() throws Exception{

        mockMvc.perform(post("/bruger/logind")
                        .param("userName", "testuser")
                        .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bruger/profil"));
    }

    @Test
    void testBrugerLogInd_Failed() throws Exception {
        mockMvc.perform(post("/bruger/logind")
                        .param("userName", "wronguser")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("forkertInput"))
                .andExpect(view().name("log-ind"));
    }

}
