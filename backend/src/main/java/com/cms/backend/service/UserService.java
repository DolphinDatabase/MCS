package com.cms.backend.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.SummaryModel.UserSummaryModel;
import com.cms.backend.entity.Role;
import com.cms.backend.entity.Usuario;
import com.cms.backend.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserService {
    
    @Autowired
    private UserRepository uRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping
    @PreAuthorize("hasAnyRole('ADM','SUP')")
    public ResponseEntity<ResponseSummaryModel> listUsers(){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            List<UserSummaryModel> all = uRepository.findAll().stream().map(this::toUserSummaryModel).collect(Collectors.toList());
            res.setAll(200, true, "Todos os usuários listados", all);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseSummaryModel> findUser(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Usuario user = uRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            res.setAll(200, true, "Usuário "+id+" Encontrado", toUserSummaryModel(user));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Usuário "+id+" não encontrado", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ResponseSummaryModel> createUser(@RequestBody Usuario user){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            if(filterRoles(user.getRoles())!=null && user.getRoles().size()>1){
                res.setAll(401, false, "Níveis não autorizados", null);
                logger.warn(res.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }
            user.setPassword(encoder.encode(user.getDocument()));
            res.setAll(200, true, "Novo Usuário criado", toUserSummaryModel(uRepository.save(user)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ResponseSummaryModel> updateUser(@PathVariable Long id, @RequestBody Usuario user){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Usuario u = uRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            res.setAll(200, true, "Usuário "+id+" atualizado", toUserSummaryModel(uRepository.save(u)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Usuário "+id+" não atualizado", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ResponseSummaryModel> deleteUser(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Usuario u = uRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            uRepository.delete(u);
            res.setAll(200, true, "Usuário "+id+" deletado",null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Usuário "+id+" não encontrado", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private UserSummaryModel toUserSummaryModel(Usuario user){
        return modelMapper.map(user,UserSummaryModel.class);
    }

    private Role filterRoles(Set<Role> roles){
        return roles.stream().filter(role->role.getId().equals(Long.valueOf(3))).findAny().orElse(null);
    }

}
