package com.devsenior.andresquintero.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsenior.andresquintero.user.exception.UserNotFoundException;
import com.devsenior.andresquintero.user.mapper.Usermapper;
import com.devsenior.andresquintero.user.model.dto.UserRequest;
import com.devsenior.andresquintero.user.model.dto.UserResponse;
import com.devsenior.andresquintero.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Usermapper userMapper;

    public UserServiceImpl (UserRepository userRepository,
         Usermapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse save(UserRequest user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }
        //validar que el request sea valido

        //convertir el request en un documento

        var document = userMapper.toDocument(user);

        // almacenar el documento
        document = userRepository.save(document);

        //convertir el documento guardado en response
        var response = userMapper.toResponse(document);
        

        //devolver el response
        return response;
    }

    
      
       @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
         }

    @Override
    public UserResponse getById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(()-> new UserNotFoundException())  ;
               }

          @Override
          public void delete(String id) {
              userRepository.deleteById(id);

          }
        
          @Override
            public UserResponse getByUsername(String username) {
                return userRepository.findByUsername(username)
                        .map(userMapper::toResponse)
                        .orElseThrow(()-> new UserNotFoundException(
                            "no existe un usuario con el nombre dado"
                        ));
            }

          @Override
          public List<UserResponse> findAll() { throw new UnsupportedOperationException("Unimplemented method 'findAll'");
          }

          @Override
          public UserResponse findById(String id) {
              return userRepository.findById(id)
                      .map(userMapper::toResponse)
                      .orElseThrow(() -> new UserNotFoundException(
                              "no existe un usuario con el id dado"
                      ));
          }
        }

