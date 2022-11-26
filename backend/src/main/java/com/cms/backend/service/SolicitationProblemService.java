package com.cms.backend.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.SummaryModel.SolicitationProblemSummaryModel;
import com.cms.backend.entity.SolicitationProblem;
import com.cms.backend.repository.SolicitationProblemRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/SolicitationProblem")
public class SolicitationProblemService {

    @Autowired
    SolicitationProblemRepository spRepository;
 
    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SolicitationService.class);

    @PostMapping
    @PreAuthorize("hasRole('SUP')")
    public ResponseEntity<ResponseSummaryModel> addProblem(@RequestBody SolicitationProblem sp){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            res.setAll(200, true, "SolicitationProblem "+sp.getProblem().getId()+" adicionado", toSolicitationProblemSummaryModel(spRepository.save(sp)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUP')")
    public ResponseEntity<ResponseSummaryModel> deleteAddedProblem(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            SolicitationProblem sp = spRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            sp.setProblem(null);
            sp.setSolicitation(null);
            spRepository.delete(sp);
            res.setAll(200, true, "SolicitationProblem "+id+" deletado", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "SolicitationProblem "+id+" não encontrado", null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private SolicitationProblemSummaryModel toSolicitationProblemSummaryModel(SolicitationProblem sp){
        return modelMapper.map(sp,SolicitationProblemSummaryModel.class);
    }

}
