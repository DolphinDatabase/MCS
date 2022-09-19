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

import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.SummaryModel.SolicitationSummaryModel;
import com.cms.backend.entity.Location;
import com.cms.backend.entity.Problem;
import com.cms.backend.entity.Solicitation;
import com.cms.backend.repository.SolicitationRepository;

import com.cms.backend.repository.LocationRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/solicitation")
public class SolicitationService {
    
    @Autowired
    private SolicitationRepository sRepository;

    @Autowired
    private LocationRepository lRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SolicitationService.class);

    @PostMapping
    public ResponseEntity<ResponseSummaryModel> createSolicitation(@RequestBody Solicitation solicitation){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation n = sRepository.save(solicitation);
            addLocation(n,n.getLocation());
            res.setAll(200, true, "New Solicitation created", toSolicitationSummaryModel(n));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> editSolicitation(@PathVariable Long id, @RequestBody Solicitation solicitation){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation s = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            s.setName(solicitation.getName());
            s.setDescription(solicitation.getDescription());
            s.setStatus(solicitation.getStatus());
            s.setLocation(solicitation.getLocation());
            res.setAll(200, true, "Solicitation "+s.getId()+" Updated", toSolicitationSummaryModel(sRepository.save(s)));
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

    @PutMapping("/{id}/addProblem")
    public ResponseEntity<ResponseSummaryModel> addProblem(@RequestBody Problem problem,@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            Set<Problem> nProblems = new HashSet<>();
            if(solicitation.getProblems() != null || solicitation.getProblems().size()>0){
                solicitation.getProblems().forEach(p->{
                    nProblems.add(p);
                });
            }
            nProblems.add(problem);
            solicitation.setProblems(nProblems);
            res.setAll(200, true, "Problem "+problem.getId()+" Added to Solicitation "+solicitation.getId(), toSolicitationSummaryModel(sRepository.save(solicitation)));
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> findSolicitation(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            res.setAll(200, true, "Solicitation "+id+" Found", toSolicitationSummaryModel(solicitation));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSummaryModel> deleteSolicitation(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            sRepository.delete(solicitation);
            res.setAll(200, true, "Solicitation "+solicitation.getId()+" Deleted", null);
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

    private void addLocation(Solicitation solicitation,Location location){
        location.setSolicitation(solicitation);
        lRepository.save(location);
    }

    private SolicitationSummaryModel toSolicitationSummaryModel(Solicitation solicitation){
        return modelMapper.map(solicitation,SolicitationSummaryModel.class);
    }

}
