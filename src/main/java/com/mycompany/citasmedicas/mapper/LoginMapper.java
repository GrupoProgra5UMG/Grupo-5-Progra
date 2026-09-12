package com.mycompany.citasmedicas.mapper;

import com.mycompany.citasmedicas.dto.LoginResponse;
import com.mycompany.citasmedicas.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginResponse toResponse(Usuario usuario, String token) {
        return new LoginResponse(
                token,
                usuario.getCorreo(),
                usuario.getNombre(),
                usuario.getRoles().stream().map(rol -> rol.getNombre()).toList()
        );
    }
}
