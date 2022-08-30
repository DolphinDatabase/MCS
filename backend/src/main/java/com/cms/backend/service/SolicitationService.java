package com.cms.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cms.backend.SummaryModel.SolicitationSummaryModel;
import com.cms.backend.entity.Solicitation;
import com.cms.backend.repository.SolicitationRepository;

@RestController
@RequestMapping("/solicitation")
public class SolicitationService {
    
    @Autowired
    SolicitationRepository sRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @GetMapping
    public ResponseEntity<ResponseSummaryModel> listSolicitation(){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            List<SolicitationSummaryModel> all = sRepository.findAll().stream().map(this::toSolicitationSummaryModel).collect(Collectors.toList()); 
            res.setAll(200, true, "List all Solicitations", all);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseSummaryModel> createSolicitation(@RequestBody Solicitation solicitation){
        ResponseSummaryModel res = new ResponseSummaryModel();        
        try{
            res.setAll(200, true, "New Solicitation Created", sRepository.save(solicitation));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> updateSolicitation(@PathVariable Long id,@RequestBody Solicitation solicitation){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation s = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            s.setName(solicitation.getName());
            s.setStatus(solicitation.getStatus());
            res.setAll(200, true, "Solicitation "+id+" Updated", toSolicitationSummaryModel(sRepository.save(s)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitation "+id+" Not Found", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> deleteSolicitation(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation s = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            sRepository.delete(s);
            res.setAll(200, true, "Solicitation "+id+" Deleted", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitation "+id+" Not Found", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private SolicitationSummaryModel toSolicitationSummaryModel(Solicitation solicitation){
        return modelMapper.map(solicitation,SolicitationSummaryModel.class);
    }

}
