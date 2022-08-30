package com.cms.backend.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cms.backend.SummaryModel.RequirementSummaryModel;
import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.entity.Requirement;
import com.cms.backend.repository.RequirementRepository;

@RestController
@RequestMapping("/requirement")
public class RequirementService {
    
    @Autowired
    RequirementRepository rRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping
    public ResponseEntity<ResponseSummaryModel> createRequirement(@RequestBody Requirement requirement){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            res.setAll(200, true, "New Requirement Created", toRequirementSummaryModel(rRepository.save(requirement)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> updateRequirement(@PathVariable Long id,@RequestBody Requirement requirement){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Requirement r = rRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            r.setName(requirement.getName());
            r.setQuantity(requirement.getQuantity());
            r.setDescription(requirement.getDescription());
            res.setAll(200, true, "Requirement "+id+" Updated", toRequirementSummaryModel(rRepository.save(r)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Requirement "+id+" Not Found", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> deleteRequirement(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Requirement r = rRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            rRepository.delete(r);
            res.setAll(200, true, "Requirement "+id+" Deleted", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Requirement "+id+" Not Found", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private RequirementSummaryModel toRequirementSummaryModel(Requirement requirement){
        return modelMapper.map(requirement,RequirementSummaryModel.class);
    }

}
