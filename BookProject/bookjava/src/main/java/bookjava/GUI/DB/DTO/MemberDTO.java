package bookjava.GUI.DB.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDTO {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
}

