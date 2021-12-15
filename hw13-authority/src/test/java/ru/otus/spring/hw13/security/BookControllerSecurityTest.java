package ru.otus.spring.hw13.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.hw13.rest.BookController;
import ru.otus.spring.hw13.security.config.WebSecurityConfig;
import ru.otus.spring.hw13.security.jwt.AuthEntryPointJwt;
import ru.otus.spring.hw13.security.jwt.AuthTokenFilter;
import ru.otus.spring.hw13.security.jwt.JwtConfig;
import ru.otus.spring.hw13.security.jwt.JwtUtils;
import ru.otus.spring.hw13.service.BookService;
import ru.otus.spring.hw13.service.DtoConverter;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import({AuthEntryPointJwt.class, JwtConfig.class, AuthTokenFilter.class, BookController.class})
@ContextConfiguration(classes = {WebSecurityConfig.class})
public class BookControllerSecurityTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DtoConverter dtoConverter;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtUtils jwtUtils;

    @Test
    void shouldRequireAuthWhenGetList() throws Exception {
        when(bookService.readAll()).thenReturn(Collections.emptyList());
        mvc.perform(get("/api/books"))
           .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    void shouldAllOkWhenGetListWithToken() throws Exception {
        String token = "someToken";
        String user = "admin";
        when(bookService.readAll()).thenReturn(Collections.emptyList());
        when(jwtUtils.validateJwtToken(token)).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(token)).thenReturn(user);
        when(userDetailsService.loadUserByUsername(user)).thenReturn(new User(user, user, Collections.emptyList()));
        mvc.perform(get("/api/books").header("Authorization", "Bearer " + token))
           .andExpect(status().isOk());
    }

}
