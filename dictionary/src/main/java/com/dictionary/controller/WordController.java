package com.dictionary.controller;

import com.dictionary.dto.WordDTO;
import com.dictionary.service.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    /** INJECT SERVICES **/

    @Autowired
    private WordService wordService;

    /** GET MAPPINGS **/

    @GetMapping("/find-all")
    public List<WordDTO> findAll(){
        return wordService.findAll();
    }
    
    @GetMapping("/find-word")
    public WordDTO findWord(@RequestParam("wordId") Long wordId){
        return wordService.findWord(wordId);
    }

    /** POST MAPPINGS **/

    @PostMapping("/create-word")
    public WordDTO createWord(@RequestBody WordDTO wordDTO){
        return wordService.createWord(wordDTO);
    }

    /** PUT MAPPINGS **/

    @PutMapping("/update-word")
    public WordDTO updateWord(@RequestBody WordDTO wordDTO){
        return wordService.updateWord(wordDTO);
    }

    /** DELETE MAPPINGS **/

    @DeleteMapping("/delete-word")
    public void deleteWord(@RequestParam("wordId") Long wordId){
        wordService.deleteWord(wordId);
    }
    
    
}
