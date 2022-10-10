package com.cms.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.SummaryModel.RoleSummaryModel;
import com.cms.backend.entity.Role;
import com.cms.backend.repository.RoleRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/role")
public class RoleService {
    
    @Autowired
    private RoleRepository rRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(RoleService.class);

    @GetMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<ResponseSummaryModel> listRoles(){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            List<RoleSummaryModel> all = rRepository.findAll().stream().map(this::toRoleSummaryModel).collect(Collectors.toList());
            res.setAll(200, true, "Todos os níveis listados", all);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private RoleSummaryModel toRoleSummaryModel(Role role){
        return modelMapper.map(role,RoleSummaryModel.class);
    }

}
