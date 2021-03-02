package lux.pe.na.market.security.controller;

import lux.pe.na.market.security.dto.JwtDto;
import lux.pe.na.market.security.dto.LoginDto;
import lux.pe.na.market.security.dto.UserDto;
import lux.pe.na.market.security.entity.Role;
import lux.pe.na.market.security.entity.User;
import lux.pe.na.market.security.enums.RoleName;
import lux.pe.na.market.security.jwt.JwtProvider;
import lux.pe.na.market.security.implementation.RoleService;
import lux.pe.na.market.security.implementation.UserService;
import lux.pe.na.market.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          UserService userService,
                          RoleService roleService,
                          JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("usuario o password incorrectos"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUserName(userDto.getUserName()))
            return new ResponseEntity<>(new Message("Ese usuario y existe"), HttpStatus.BAD_REQUEST);
        if (userService.existsByMail(userDto.getMail()))
            return new ResponseEntity<>(new Message("Ese email y existe"), HttpStatus.BAD_REQUEST);

        User user = new User(userDto.getName(), userDto.getUserName(), userDto.getMail(), passwordEncoder.encode(userDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByName(RoleName.ROLE_USER).get());
        if (userDto.getRoles().contains("admin"))
            roles.add(roleService.getByName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("usuario guardado"), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("usuario o password incorrectos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}
