package com.cms.backend.service;

import java.util.ArrayList;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.cms.backend.entity.Material;
import com.cms.backend.entity.Solicitation;
import com.cms.backend.entity.Usuario;
import com.cms.backend.repository.SolicitationRepository;
import com.cms.backend.repository.UserRepository;
import com.cms.backend.util.Status;
import com.cms.backend.repository.LocationRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/solicitation")
public class SolicitationService {
    
    @Autowired
    private SolicitationRepository sRepository;

    @Autowired
    private UserRepository uRepository;

    @Autowired
    private LocationRepository lRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SolicitationService.class);

    @PostMapping
    @PreAuthorize("hasRole('CLT')")
    public ResponseEntity<ResponseSummaryModel> createSolicitation(@RequestBody Solicitation solicitation){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            String session = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            Usuario user = uRepository.findByEmail(session);
            solicitation.setUser(user);
            Solicitation n = sRepository.save(solicitation);
            addLocation(n,n.getLocation());
            res.setAll(200, true, "Nova Solicitação criada", toSolicitationSummaryModel(n));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseSummaryModel> editSolicitation(@PathVariable Long id, @RequestBody Solicitation solicitation){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation s = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            s.setName(solicitation.getName());
            s.setDescription(solicitation.getDescription());
            s.setStatus(solicitation.getStatus());
            s.setLocation(solicitation.getLocation());
            s.setBudget(solicitation.getBudget());
            res.setAll(200, true, "Solicitação "+s.getId()+" atualizada", toSolicitationSummaryModel(sRepository.save(s)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitação "+id+" não encontrada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}/addService")
    @PreAuthorize("hasAnyRole('ADM','SUP')")
    public ResponseEntity<ResponseSummaryModel> addService(@RequestBody Solicitation service,@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            Usuario usuario = uRepository.findById(service.getResponsible().getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            solicitation.setService(service.getService());
            solicitation.setResponsible(usuario);
            solicitation.setStatus(Status.IN_PROGRESS);
            res.setAll(200, true, "Solicitação "+solicitation.getId()+" atualizada", toSolicitationSummaryModel(sRepository.save(solicitation)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitação ou usuário "+id+" não encontrados", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}/addMaterial")
    @PreAuthorize("hasRole('SUP')")
    public ResponseEntity<ResponseSummaryModel> addMaterial(@RequestBody Material material,@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            Set<Material> nMaterials = new HashSet<>();
            if(solicitation.getMaterials() != null || solicitation.getMaterials().size()>0){
                solicitation.getMaterials().forEach(m->{
                    nMaterials.add(m);
                });
            }
            nMaterials.add(material);
            solicitation.setMaterials(nMaterials);
            solicitation.setStatus(Status.IN_PROGRESS);
            res.setAll(200, true, "Material "+material.getNum()+" adicionado", toSolicitationSummaryModel(sRepository.save(solicitation)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitação "+id+" não encontrada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseSummaryModel> findSolicitation(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            res.setAll(200, true, "Solicitação "+id+" encontrada", toSolicitationSummaryModel(solicitation));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitação "+id+" não encontrada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseSummaryModel> listSolicitation(){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Object sessionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
            List<SolicitationSummaryModel> all = new ArrayList<>();
            if(sessionRole.equals("[ROLE_CLT]")){
                String sessionEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
                all = sRepository.findAll().stream().filter(chd->chd.getUser().getEmail().equals(sessionEmail)).map(this::toSolicitationSummaryModel).collect(Collectors.toList());
            }else{
                all = sRepository.findAll().stream().map(this::toSolicitationSummaryModel).collect(Collectors.toList());
            }
            res.setAll(200, true, "Todas as Solicitações listadas", all);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CLT')")
    public ResponseEntity<ResponseSummaryModel> deleteSolicitation(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Solicitation solicitation = sRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            sRepository.delete(solicitation);
            res.setAll(200, true, "Solicitação "+solicitation.getId()+" deletada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Solicitação "+id+" não encontrada", null);
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
