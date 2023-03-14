package y88.kirill.msauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
}
