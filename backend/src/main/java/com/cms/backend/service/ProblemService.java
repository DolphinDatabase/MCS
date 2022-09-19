package com.cms.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cms.backend.SummaryModel.ProblemSummaryModel;
import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.entity.Problem;
import com.cms.backend.entity.Solution;
import com.cms.backend.repository.ProblemRepository;
import com.cms.backend.repository.SolutionRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/problem")
public class ProblemService {
    
    @Autowired
    private ProblemRepository pRepository;

    @Autowired
    private SolutionRepository sRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SolutionService.class);

    @PostMapping
    public ResponseEntity<ResponseSummaryModel> createProblem(@RequestBody Problem problem){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Problem n = pRepository.save(problem);
            addSolutions(n, problem.getSolutions());
            res.setAll(200, true, "New Problem Created", toProblemSummaryModel(n));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseSummaryModel> listProblem(){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            List<ProblemSummaryModel> all = pRepository.findAll().stream().map(this::toProblemSummaryModel).collect(Collectors.toList());
            res.setAll(200, true, "List All Problems", all);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> findProblem(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Problem problem = pRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            res.setAll(200, true, "Problem "+id+" Found", toProblemSummaryModel(problem));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solution "+id+" Not Found", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> updateProblem(@PathVariable Long id, @RequestBody Problem problem){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Problem p = pRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            p.setName(problem.getName());
            p.setNivel(problem.getNivel());
            addSolutions(p, problem.getSolutions());
            res.setAll(200, true, "Problem "+id+" Updated", toProblemSummaryModel(pRepository.save(p)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Problem "+id+" Not Found", null);
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
    public ResponseEntity<ResponseSummaryModel> deleteProblem(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Problem p = pRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            p.getSolicitations().forEach(s->{
                s.getProblems().remove(p);
            });
            pRepository.delete(p);
            res.setAll(200, true, "Problem "+id+" Deleted",null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Problem "+id+" Not Found", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private void addSolutions(Problem p, Set<Solution> newSet){
        Set<Solution> n = new HashSet<>();
        newSet.forEach(solution->{
            solution.setProblem(p);
            n.add(sRepository.save(solution));
        });
        p.setSolutions(n);
        pRepository.save(p);
    }

    private ProblemSummaryModel toProblemSummaryModel(Problem problem){
        return modelMapper.map(problem,ProblemSummaryModel.class);
    }

}
