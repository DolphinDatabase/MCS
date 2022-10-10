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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.SummaryModel.SolutionSummaryModel;
import com.cms.backend.entity.Solution;
import com.cms.backend.repository.SolutionRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/solution")
public class SolutionService {
    
    @Autowired
    private SolutionRepository sRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SolutionService.class);

    @PostMapping
    @PreAuthorize("hasRole('SUP')")
    public ResponseEntity<ResponseSummaryModel> createSolution(@RequestBody Solution solution){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            res.setAll(200, true, "Nova Solução criada", toSolutionSummaryModel(sRepository.save(solution)));
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
    public ResponseEntity<ResponseSummaryModel> findSolution(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solution solution = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            res.setAll(200, true, "Solução "+id+" encontrada", toSolutionSummaryModel(solution));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solução "+id+" não encontrada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUP')")
    public ResponseEntity<ResponseSummaryModel> updateSolution(@PathVariable Long id, @RequestBody Solution solution){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solution s = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            s.setDescription(solution.getDescription());
            res.setAll(200, true, "Solução "+id+" atualizada", toSolutionSummaryModel(sRepository.save(s)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solução "+id+" não encontrada", null);
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
    @PreAuthorize("hasRole('SUP')")
    public ResponseEntity<ResponseSummaryModel> deleteSolution(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solution s = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            sRepository.delete(s);
            res.setAll(200, true, "Solução "+id+" deletada",null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solução "+id+" não encontrada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private SolutionSummaryModel toSolutionSummaryModel(Solution solution){
        return modelMapper.map(solution,SolutionSummaryModel.class);
    }

}
