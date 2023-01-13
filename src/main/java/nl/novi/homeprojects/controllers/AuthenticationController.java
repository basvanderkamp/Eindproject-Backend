package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.input.AuthenticationRequest;
import nl.novi.homeprojects.dtos.output.AuthenticationResponse;
import nl.novi.homeprojects.utils.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {

    /*inject authentionManager, userDetailService en jwtUtil*/
    private AuthenticationManager authenticateManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticateManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticateManager = authenticateManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticateManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}