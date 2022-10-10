package com.cms.backend.service;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.security.Login;
import com.cms.backend.util.JwtUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/session")
public class SessionService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    Logger logger = LoggerFactory.getLogger(SessionService.class);

    @PostMapping
    public ResponseEntity<ResponseSummaryModel> createSession(@RequestBody Login login, HttpServletResponse response){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Authentication auth = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
            auth = authenticationManager.authenticate(auth);
            login.setPassword(null);
            login.setToken(JwtUtils.generateToken(auth));
            login.setRole(auth.getAuthorities().iterator().next().getAuthority());
            res.setAll(200, true, "Usuário autenticado", login);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch(BadCredentialsException err){
            res.setAll(401, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

}
