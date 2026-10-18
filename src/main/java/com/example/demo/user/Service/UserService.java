package com.example.demo.user.Service;


import com.example.demo.exeptions.user.UserConflictException;
import com.example.demo.user.dto.*;
import com.example.demo.user.entity.Role;
import com.example.demo.helpers.GlobalHelperService;
import com.example.demo.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.profile.entity.Profile;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GlobalHelperService globalHelperService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    // ========== GET ==========

    // retorna os dados do usuario logado
    public UserResponse getMe() {
        return userMapper.toUserResponse(globalHelperService.getLoggedUser()) ;

    }


    // verifica se o userName ja esta em uso e da sugestões de novos nomes
    public RegisterSuggestions validateUsername(String userName) {

        // caso o nome esteja disponivel
        if (!globalHelperService.validadeUserNameOnCreate(userName)) {
            return new RegisterSuggestions(true, List.of());
        }

        // caso não esteja disponivel
        return new RegisterSuggestions(false, generateUsernameSuggestions(userName));
    }

    // verifica se o email ja esta em uso
    public RegisterSuggestions validadeEmail(String email){
        return new RegisterSuggestions(
                !globalHelperService.validadeEmailOnCreate(email), List.of()
        );
    }


    // ========== POST ==========

    // criar usuario
    @Transactional
    public UserResponse createUser(UserRequest request){

        // verifica se o userName ou email ja esta sendo usado
        if (globalHelperService.validadeUserNameOnCreate(request.userName()
        ) || globalHelperService.validadeEmailOnCreate(request.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Nome de usuário ou email já está em uso"
            );
        }

        // instanciar o user
        User user = new User();

        // set dos valores com base no request recebido
        user.setName(request.name());
        user.setUserName(request.userName());
        user.setEmail(request.email());

        // os usarios novos nascem como USER
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.password()));

        // cria profile automaticamente
        globalHelperService.createProfile(user);

        return userMapper.toUserResponse(userRepository.save(user));

    }

    // TODO - verifica se o email e userName ja exitste

    // ========== PUT ==========

    // eidtar usuario
    public UserResponse updateUser(UpdateUserRequest request){
        User loggedUser = globalHelperService.getLoggedUser();

        if (request.userName() != null){
            if (globalHelperService.validadeUserNameOnCreate(request.userName())){
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Nome de usuário ou email já está em uso"
                );
            }
            loggedUser.setUserName(request.userName());
        }

        if (request.name() != null){
            loggedUser.setName(request.name());
        }

        if (request.password() != null){
            loggedUser.setPassword(passwordEncoder.encode(request.password()));
        }

        return userMapper.toUserResponse(userRepository.save(loggedUser));

    }


    // transformar user logado em artista
    public UserRoleResponse toArtist(){
        User loggedUser = globalHelperService.getLoggedUser();

        if (loggedUser.getRole() == Role.ARTIST){
            throw new UserConflictException("Você já é um artista");
        }

        if (loggedUser.getRole() == Role.ADMIN){
            throw new UserConflictException("Administradores não podem ser artistas");
        }

        loggedUser.setRole(Role.ARTIST);

        return userMapper.toUserRoleResponse(userRepository.save(loggedUser));

    }


    // ========== DELETE ==========

    // próprio usuario se deletar
    public void deleteUser(){
        userRepository.delete(globalHelperService.getLoggedUser());
    }


    // retornar ate 3 sugestões validas
    private List<String> generateUsernameSuggestions(String userName) {
        List<String> suggestions = new ArrayList<>();

        List<String> candidates = List.of(
                userName + "7",
                userName + "01",
                userName + "_dev",
                userName + ".br",
                userName + "123"
        );

        for (String candidate : candidates) {
            if (!globalHelperService.validadeUserNameOnCreate(candidate)) {
                suggestions.add(candidate);
            }

            if (suggestions.size() == 3) {
                break;
            }
        }

        return suggestions;
    }
}
