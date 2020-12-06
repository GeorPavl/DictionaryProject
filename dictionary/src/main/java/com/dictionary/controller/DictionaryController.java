package com.dictionary.controller;

import com.dictionary.dto.DictionaryDTO;
import com.dictionary.service.dictionary.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionaries")
public class DictionaryController {

    /** INJECT SERVICES **/

    @Autowired
    private DictionaryService dictionaryService;

    /** GET MAPPINGS **/

    @GetMapping("/find-all")
    public List<DictionaryDTO> findAll(){
        return dictionaryService.findAll();
    }

    @GetMapping("/find-dictionary")
    public DictionaryDTO findDictionary(@RequestParam("dictionaryId") Long dictionaryId){
        return dictionaryService.findDictionary(dictionaryId);
    }

    /** POST MAPPINGS **/

    @PostMapping("/create-dictionary")
    public DictionaryDTO createDictionary(@RequestBody DictionaryDTO dictionaryDTO){
        return dictionaryService.createDictionary(dictionaryDTO);
    }

    /** PUT MAPPINGS **/

    @PutMapping("/update-dictionary")
    public DictionaryDTO updateDictionary(@RequestBody DictionaryDTO dictionaryDTO){
        return dictionaryService.updateDictionary(dictionaryDTO);
    }

    /** DELETE MAPPINGS **/

    @DeleteMapping("/delete-dictionary")
    public void deleteDictionary(@RequestParam("dictionaryId") Long dictionaryId){
        dictionaryService.deleteDictionary(dictionaryId);
    }
}
