package ru.otus.spring.hw11.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.dto.BookDto;
import ru.otus.spring.hw11.model.Book;
import ru.otus.spring.hw11.repository.BookRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookRoutesTest {

    private WebTestClient client;

    @Autowired
    @Qualifier("composedBookRoutes")
    private RouterFunction<ServerResponse> route;

    @MockBean
    private BookRepository bookRepository;
    private final String id = "0000000000001";
    private final Book book = new Book(id, "name", List.of(id), List.of(id));

    @BeforeEach
    public void initClient() {
        client = WebTestClient.bindToRouterFunction(route).build();
    }

    @Test
    public void shouldReturnAllOk() {
        when(bookRepository.findAll()).thenReturn(Flux.just(book));
        client.get()
                .uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void shouldReturnNotFoundWithWrongId() {
        when(bookRepository.findById(id)).thenReturn(Mono.empty());
        client.get()
                .uri("/api/books/" + id)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void shouldReturnBookById() {
        when(bookRepository.findById(id)).thenReturn(Mono.just(book));
        BookDto bookDto = client.get()
                               .uri("/api/books/" + id)
                               .exchange()
                               .expectStatus()
                               .isOk()
                               .expectBody(BookDto.class)
                               .returnResult()
                               .getResponseBody();
        Assertions.assertNotNull(bookDto, id );
        Assertions.assertEquals(bookDto.getId(), id );
    }

}
