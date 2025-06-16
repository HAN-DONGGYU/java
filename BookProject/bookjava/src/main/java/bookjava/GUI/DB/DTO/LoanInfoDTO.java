package bookjava.GUI.DB.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanInfoDTO {
    private int loanId;
    private int bookId;
    private String title;
    private LocalDate dueDate;
    private String memberId;

}
