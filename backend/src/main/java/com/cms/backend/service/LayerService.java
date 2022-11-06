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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cms.backend.SummaryModel.LayerSummaryModel;
import com.cms.backend.SummaryModel.ResponseSummaryModel;
import com.cms.backend.entity.Layer;
import com.cms.backend.repository.LayerRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/layer")
public class LayerService {
    
    @Autowired
    LayerRepository lRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(SolutionService.class);

    @PostMapping
    @PreAuthorize("hasAnyRole('ADM','SUP')")
    public ResponseEntity<ResponseSummaryModel> createSolution(@RequestBody Layer layer){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            res.setAll(200, true, "Nova Layer criada", toLayerSummaryModel(lRepository.save(layer)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADM','SUP')")
    public ResponseEntity<ResponseSummaryModel> updateSolution(@PathVariable Long id, @RequestBody Layer layer){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Layer l = lRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            l.setX(layer.getX());
            l.setY(layer.getY());
            l.setColor(layer.getColor());
            l.setSize(layer.getSize());
            l.setType(layer.getType());
            res.setAll(200, true, "Layer "+id+" atualizada", toLayerSummaryModel(lRepository.save(l)));
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Layer "+id+" não encontrada", null);
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
    @PreAuthorize("hasAnyRole('ADM','SUP')")
    public ResponseEntity<ResponseSummaryModel> deleteSolution(@PathVariable Long id){
        ResponseSummaryModel res = new ResponseSummaryModel();
        try{
            Layer l = lRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            lRepository.delete(l);
            res.setAll(200, true, "Layer "+id+" deletada",null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch(ResponseStatusException err){
            res.setAll(404, false, "Layer "+id+" não encontrada", null);
            logger.info(res.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch(Exception err){
            res.setAll(500, false, err.getMessage(), null);
            logger.error(res.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private LayerSummaryModel toLayerSummaryModel(Layer layer){
        return modelMapper.map(layer,LayerSummaryModel.class);
    }

}
