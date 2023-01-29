package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.UserInputDto;
import nl.novi.homeprojects.dtos.output.UserOutputDto;
import nl.novi.homeprojects.exceptions.BadRequestException;
import nl.novi.homeprojects.exceptions.UsernameNotFoundException;
import nl.novi.homeprojects.models.Authority;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.User;
import nl.novi.homeprojects.repositorys.UserRepository;
import nl.novi.homeprojects.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static nl.novi.homeprojects.services.AssignmentService.clientRepository;


@Service
public class UserService {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    private static UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserOutputDto> getUsers() {
        List<UserOutputDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }


    public UserOutputDto getUser(String username) {
        UserOutputDto dto = new UserOutputDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }


    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }


    public String createUser(UserInputDto userInputDto) {

        if (userExists(userInputDto.getUsername())){
            throw new BadRequestException("Username already exists");
        } else {
            String randomString = RandomStringGenerator.generateAlphaNumeric(20);
            userInputDto.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
            userInputDto.setApikey(randomString);
            User saveUser = userRepository.save(toUser(userInputDto));
            return saveUser.getUsername();
        }
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }


    public void updateUser(String username, UserOutputDto newUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }


    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserOutputDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }


    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }


    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }


    public static void assignClientToUser(String id, String clientId) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(id);
        } else if (optionalClient.isEmpty()) {
            throw new UsernameNotFoundException(clientId);
        } else {
            User user = optionalUser.get();
            Client client = optionalClient.get();
            user.setClient(client);
            userRepository.save(user);
        }
    }


    public static UserOutputDto fromUser(User user){

        var dto = new UserOutputDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.authorities = user.getAuthorities();
        if (dto.getClient() == null) {
            dto.setClient(user.getClient());
        }

        return dto;
    }


    public User toUser(UserInputDto userInputDto) {

        var user = new User();

        user.setUsername(userInputDto.getUsername());
        user.setPassword(userInputDto.getPassword());
        user.setEnabled(userInputDto.getEnabled());
        user.setApikey(userInputDto.getApikey());
        if (user.getClient() != null){
            user.setClient(userInputDto.getClient());
        }

        return user;
    }
}
