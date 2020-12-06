package com.dictionary.controller;

import com.dictionary.dto.MeaningDTO;
import com.dictionary.service.meaning.MeaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meanings")
public class MeaningController {

    /** INJECT SERVICES **/

    @Autowired
    private MeaningService meaningService;

    /** GET MAPPINGS **/

    @GetMapping("/find-all")
    public List<MeaningDTO> findAll(){
        return meaningService.findAll();
    }

    @GetMapping("/find-meaning")
    public MeaningDTO findMeaning(@RequestParam("meaningId") Long meaningId){
        return meaningService.findMeaning(meaningId);
    }

    /** POST MAPPINGS **/

    @PostMapping("/create-meaning")
    public MeaningDTO createMeaning(@RequestBody MeaningDTO meaningDTO){
        return meaningService.createMeaning(meaningDTO);
    }

    /** PUT MAPPINGS **/

    @PutMapping("/update-meaning")
    public MeaningDTO updateMeaning(@RequestBody MeaningDTO meaningDTO){
        return meaningService.updateMeaning(meaningDTO);
    }

    /** DELETE MAPPINGS **/
    @DeleteMapping("/delete-meaning")
    public void deleteMeaning(@RequestParam("meaningId") Long meaningId){
        meaningService.deleteMeaning(meaningId);
    }
}
