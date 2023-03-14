package y88.kirill.corelib.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import y88.kirill.corelib.dtos.UserDTO;
import y88.kirill.corelib.interfaces.JWTHandler;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JWTService implements JWTHandler {

    private final String JWT_SECRET = "3b2648762a13d3f6be076edb7f70fa391e83049e1eaef30448eecc4effd31e74f7eaa092196868d677986ab5f12afd579a894d0daa0716da1d72c443a539976e";

    @Override
    public String generateToken(UserDTO userDTO) {

        //todo уменьшить после добавления рефреша токена
        LocalDateTime expiredDate = LocalDateTime.now().plus(15,ChronoUnit.DAYS);

        //todo проверить адекватность работы .setExpiration
        Instant expirationTime = Instant.now().plus(15, ChronoUnit.DAYS);
        Date expirationDate = Date.from(expirationTime);

        String token = Jwts.builder()
                .claim("id", userDTO.getId())
                .claim("login", userDTO.getLogin())
                .claim("role", userDTO.getUserRoleString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();

        return "Bearer " + token;
    }

    @Override
    public UserDTO parseToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(jwsClaims.getBody().get("id", Long.class));
        userDTO.setLogin(jwsClaims.getBody().get("login", String.class));
        userDTO.setUserRoleString(jwsClaims.getBody().get("role", String.class));

        return userDTO;
//        return UserDTO.builder()
//                .id(jwsClaims.getBody().get("id", Long.class))
//                .login(jwsClaims.getBody().get("login", String.class))
//                .userRoleString(jwsClaims.getBody().get("role", String.class))
//                .build();
    }


}
