package y88.kirill.corelib.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
//@Builder
public class UserDTO {

    private Long id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
    private String userRoleString;

}
