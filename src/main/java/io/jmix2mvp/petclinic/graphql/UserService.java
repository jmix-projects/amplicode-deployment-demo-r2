package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.UserDTO;
import io.jmix2mvp.petclinic.repository.UserRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.*;

@GraphQLApi
@Service
public class UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Secured({ADMIN})
    @GraphQLQuery(name = "userList")
    @Transactional
    public List<UserDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(e -> mapper.map(e, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, OWNER, VETERINARIAN})
    @GraphQLQuery(name = "userData")
    @Transactional
    public UserDTO getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return mapper.map(userRepository.getById(authentication.getName()), UserDTO.class);
    }
}
