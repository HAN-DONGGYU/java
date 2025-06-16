package bookjava.GUI.DB.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
