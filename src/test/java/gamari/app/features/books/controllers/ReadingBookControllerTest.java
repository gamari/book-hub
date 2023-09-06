package gamari.app.features.books.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import gamari.app.features.base.BaseController;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.book.BookQueryService;
import gamari.app.features.books.services.book.BookRegistrationService;
import gamari.app.features.books.services.memo.MemoService;
import gamari.app.features.books.services.reading_book.ReadingBookQueryService;
import gamari.app.features.books.services.reading_book.ReadingBookRegistrationService;
import gamari.app.features.users.services.UserService;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ActiveProfiles("local")
@WebMvcTest(ReadingBookController.class)
public class ReadingBookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReadingBookQueryService readingBookQueryService;

    @MockBean
    private ReadingBookRegistrationService readingBookRegistrationService;

    @MockBean
    private UserService userService;

    @MockBean
    private BookQueryService bookQueryService;

    @MockBean
    private BookRegistrationService bookRegistrationService;

    @MockBean
    private MemoService memoService;

    // TODO 認証を回避する方法を考える
    @WithMockUser(username = "user", password = "password", roles = { "USER" })
    @Test
    void testUpdateStatus() throws Exception {
        // Init
        ReadingBook mockReadingBook = new ReadingBook();
        mockReadingBook.setId("1");
        mockReadingBook.setStatus("READING");

        when(readingBookQueryService.findReadingBookById("1")).thenReturn(Optional.of(mockReadingBook));

        // Act and Assert
        mockMvc.perform(
                post("/reading-books/updateStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("bookId", "1", "status", "READING"))))
                .andExpect(status().isOk());
        // Verify that the ReadingBookRegistrationService#updateStatus method was called
        verify(readingBookRegistrationService).updateStatus(mockReadingBook);
    }
}
