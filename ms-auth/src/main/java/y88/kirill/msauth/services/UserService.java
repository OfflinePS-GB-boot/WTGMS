package y88.kirill.msauth.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import y88.kirill.corelib.dtos.UserDTO;
import y88.kirill.msauth.models.User;
import y88.kirill.msauth.models.UserRole;
import y88.kirill.msauth.repositories.UserRepository;
import y88.kirill.msauth.repositories.UserRoleRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user){
        UserRole userRole = userRoleRepository.findByTitle("ROLE_USER");
        user.setUserRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login).orElseThrow();
    }

    public UserDTO findByUserAndPassword(String login, String password){
        User userEntity = findByLogin(login);

        if(userEntity != null) {
            if(passwordEncoder.matches(password, userEntity.getPassword())){
                return convertToDTO( userEntity);
            }
        }
    return null;
    }


    public boolean existsUserByLogin(String login){
        return userRepository.existsUserByLogin(login);
    }

    public boolean existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }


    public UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthdayDate(user.getBirthdayDate());
        userDTO.setUserRoleString(user.getUserRole().getTitle());
        return userDTO;


//        return UserDTO.builder()
//                .id(user.getId())
//                .login(user.getLogin())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .birthdayDate(user.getBirthdayDate())
//                .userRoleString(user.getUserRole().getTitle())
//                .build();
    }


}
