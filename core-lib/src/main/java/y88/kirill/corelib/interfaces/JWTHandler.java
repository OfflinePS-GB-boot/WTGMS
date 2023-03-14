package y88.kirill.corelib.interfaces;

import y88.kirill.corelib.dtos.UserDTO;

public interface JWTHandler {
    String generateToken(UserDTO token);
    UserDTO parseToken(String token);

}
