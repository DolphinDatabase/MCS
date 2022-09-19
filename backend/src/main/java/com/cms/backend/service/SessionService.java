package com.cms.backend.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.SummaryModel.UserSummaryModel;
import com.cms.backend.entity.User;
import com.cms.backend.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/session")
public class SessionService {
    
    @Autowired
    private UserRepository uRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SessionService.class);

    @PostMapping
    public ResponseEntity<ResponseSummaryModel> createUser(@RequestBody User user){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            User u = uRepository.findByEmail(user.getEmail());
            if(u != null){
                if(u.getPassword().equals(user.getPassword())){
                    res.setAll(200, true, "Session Created", toUserSummaryModel(u));
                    logger.info(res.getMessage());
                    return ResponseEntity.status(HttpStatus.OK).body(res);
                }else{
                    res.setAll(401, false, "E-mail/Password is incorrect", null);
                    logger.info(res.getMessage());
                    return ResponseEntity.status(HttpStatus.OK).body(res);
                }
            }else{
                res.setAll(404, false, "User not Found", null);
                logger.info(res.getMessage());
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private UserSummaryModel toUserSummaryModel(User user){
        return modelMapper.map(user,UserSummaryModel.class);
    }

}
