package com.dictionary.service.word;

import com.dictionary.dto.WordDTO;
import com.dictionary.model.Dictionary;
import com.dictionary.model.Word;
import com.dictionary.repository.DictionaryRepository;
import com.dictionary.repository.WordRepository;
import com.dictionary.service.dictionary.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService{

    /**
     * INJECT REPOSITORIES
     *  AND SERVICES
     *  **/

    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * Convert Entity - DTO
     *  dtoToEntity
     *  writeWordDTO
     * */

    @Override
    public Word dtoToEntity(WordDTO wordDTO) {
        Word word = new Word();
        if (wordDTO.getId() != null){
            word.setId(wordDTO.getId());
        }
        if (wordDTO.getOriginal() != null){
            word.setOriginal(wordDTO.getOriginal());
        }
        if (wordDTO.getCreateTime() != null){
            word.setCreateTime(wordDTO.getCreateTime());
        }
        if (wordDTO.getUpdateTime() != null){
            word.setUpdateTime(wordDTO.getUpdateTime());
        }
        if (wordDTO.getExamined() != null){
            word.setExamined(wordDTO.getExamined());
        }
        if (wordDTO.getDictionaryId() != null){
            Optional<Dictionary> result = dictionaryRepository.findById(wordDTO.getDictionaryId());
            if (result.isEmpty()){
                throw new RuntimeException("Did not find dictionaryId: " + wordDTO.getDictionaryId());
            }
            word.setDictionary(result.get());
        }
        return word;
    }

    @Override
    public WordDTO writeWordDTO(Word word) {
        Optional<Dictionary> dictionaryOptional = dictionaryRepository.findById(word.getDictionary().getId());
        if (dictionaryOptional.isEmpty()){
            throw new RuntimeException("Did not find dictionaryId: " + word.getDictionary().getId());
        }
        WordDTO result = new WordDTO();
        result.setId(word.getId());
        result.setOriginal(word.getOriginal());
        result.setCreateTime(word.getCreateTime());
        result.setUpdateTime(word.getUpdateTime());
        result.setExamined(word.getExamined());
        result.setDictionaryId(word.getDictionary().getId());
        return result;
    }

    /**
     * DEFINING CRUD METHODS:
     *  findAll()
     *  findWord()
     *  createWord()
     *  updateWord()
     *  deleteWord()
     * **/

    @Override
    public List<WordDTO> findAll() {
        List<WordDTO> wordDTOS = new ArrayList<>();
        for (Word tempWord : wordRepository.findAll()){
            wordDTOS.add(writeWordDTO(tempWord));
        }
        return wordDTOS;
    }

    @Override
    public WordDTO findWord(Long wordId) {
        Optional<Word> optionalWord = wordRepository.findById(wordId);
        if (optionalWord.isEmpty()){
            throw new RuntimeException("Did not find wordId: " + wordId);
        }
        return writeWordDTO(optionalWord.get());
    }

    @Override
    public WordDTO createWord(WordDTO source) {
        Dictionary dictionary = dictionaryService.dtoToEntity(dictionaryService.findDictionary(source.getDictionaryId()));
        Word current = dtoToEntity(source);
        dictionary.addWord(current);
        return writeWordDTO(wordRepository.save(current));
    }

    @Override
    public WordDTO updateWord(WordDTO source) {
        Word current = dtoToEntity(findWord(source.getId()));
        if (source.getDictionaryId() != null){
            Dictionary dictionary = dictionaryService.dtoToEntity(dictionaryService.findDictionary(source.getDictionaryId()));
            current.setDictionary(dictionary);
        }
        if (source.getOriginal() != null){
            current.setOriginal(source.getOriginal());
        }
        if (source.getCreateTime() != null){
            current.setCreateTime(source.getCreateTime());
        }
        if (source.getUpdateTime() != null){
            current.setUpdateTime(source.getUpdateTime());
        }
        if (source.getExamined() != null){
            current.setExamined(source.getExamined());
        }
        return writeWordDTO(wordRepository.save(current));
    }

    @Override
    public void deleteWord(Long wordId) {
    wordRepository.delete(dtoToEntity(findWord(wordId)));
    }
}
