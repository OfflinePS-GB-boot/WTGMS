package y88.kirill.corelib.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import y88.kirill.corelib.dtos.UserDTO;
import y88.kirill.corelib.interfaces.JWTHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTHandler jwtHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken authenticationToken = createToken(authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            //todo выбросить свою ошибку
        }

        filterChain.doFilter(request,response);
    }


    private UsernamePasswordAuthenticationToken createToken (String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        UserDTO userDTO = jwtHandler.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(userDTO.getUserRoleString()!=null && !userDTO.getUserRoleString().isEmpty()){
            authorities.add(new SimpleGrantedAuthority(userDTO.getUserRoleString()));
        }

        return new UsernamePasswordAuthenticationToken(userDTO, null, authorities);
    }





}
