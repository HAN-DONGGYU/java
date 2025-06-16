package bookjava.GUI.DB.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanDTO {
    private int id;
    private int idBook;
    private int idMember;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDateTime returnDate;
    private String status;
}

