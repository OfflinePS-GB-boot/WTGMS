package y88.kirill.msauth.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import y88.kirill.corelib.dtos.UserDTO;
import y88.kirill.corelib.dtos.WTGHttpStatus;
import y88.kirill.corelib.interfaces.JWTHandler;
import y88.kirill.msauth.dtos.JwtRequestDTO;
import y88.kirill.msauth.dtos.JwtResponseDTO;
import y88.kirill.msauth.models.User;
import y88.kirill.msauth.services.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTHandler jwtHandler;

    @PostMapping("/register")
    public ResponseEntity<WTGHttpStatus> register(@RequestBody UserDTO userDTO){

        if(userService.existsUserByLogin(userDTO.getLogin())){
            return ResponseEntity.of(Optional.of(new WTGHttpStatus(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким login уже существует")));
        }
        if(userService.existsUserByEmail(userDTO.getEmail())){
            return ResponseEntity.of(Optional.of(new WTGHttpStatus(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким email уже существует")));
        }

//         User user = User.builder()
//                    .login(userDTO.getLogin())
//                    .firstName(userDTO.getFirstName())
//                    .lastName(userDTO.getLastName())
//                    .password(userDTO.getPassword())
//                    .email(userDTO.getEmail())
//                    .birthdayDate(userDTO.getBirthdayDate())
//                    .build();

        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setBirthdayDate(userDTO.getBirthdayDate());



         userService.saveUser(user);

        return ResponseEntity.of(Optional.of(new WTGHttpStatus(HttpStatus.OK.value(), "Пользователь " + user.getLogin() + " зарегистрирован")));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDTO jwtRequestDTO){


//     try {
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword()));
//     }catch (BadCredentialsException e){
//         return new ResponseEntity<>(new WTGError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
//     }

        UserDTO userDTO = userService.findByUserAndPassword(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword());
        String token = jwtHandler.generateToken(userDTO);
        return ResponseEntity.ok(new JwtResponseDTO(token, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getLogin(), userDTO.getEmail()));
    }


}
