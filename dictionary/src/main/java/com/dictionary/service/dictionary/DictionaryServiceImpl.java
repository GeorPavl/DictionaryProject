package com.dictionary.service.dictionary;

import com.dictionary.dto.DictionaryDTO;
import com.dictionary.dto.WordDTO;
import com.dictionary.model.Dictionary;
import com.dictionary.model.User;
import com.dictionary.model.Word;
import com.dictionary.repository.DictionaryRepository;
import com.dictionary.repository.UserRepository;
import com.dictionary.service.user.UserService;
import com.dictionary.service.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryServiceImpl implements DictionaryService{

    /**
     * INJECT REPOSITORIES
     *  AND SERVICES
     *  **/

    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private WordService wordService;

    /**
     * Convert Entity - DTO
     *  dtoToEntity
     *  writeUserDTO
     * */

    @Override
    public Dictionary dtoToEntity(DictionaryDTO dictionaryDTO) {
        Dictionary dictionary = new Dictionary();
        if (dictionaryDTO.getId() != null){
            dictionary.setId(dictionaryDTO.getId());
        }
        if (dictionaryDTO.getLanguage() != null){
            dictionary.setLanguage(dictionaryDTO.getLanguage());
        }
        if (dictionaryDTO.getCreateTime() != null){
            dictionary.setCreateTime(dictionaryDTO.getCreateTime());
        }
        if (dictionaryDTO.getUpdateTime() != null){
            dictionary.setUpdateTime(dictionaryDTO.getUpdateTime());
        }
        if (dictionaryDTO.getUserId() != null){
            Optional<User> userOptional = userRepository.findById(dictionaryDTO.getUserId());
            if (userOptional.isEmpty()){
                throw new RuntimeException("Did not find userId: " + dictionaryDTO.getUserId());
            }
            dictionary.setUser(userOptional.get());
        }
        if (dictionaryDTO.getWords() != null){
            List<Word> words = new ArrayList<>();
            for (WordDTO tempWordDTO : dictionaryDTO.getWords()){
                Word newWord = wordService.dtoToEntity(tempWordDTO);
                words.add(newWord);
            }
            dictionary.setWords(words);
        }
        return dictionary;
    }

    @Override
    public DictionaryDTO writeDictionaryDTO(Dictionary dictionary){
        Optional<User> optionalUser = userRepository.findById(dictionary.getUser().getId());
        if (optionalUser.isEmpty()){
            throw new RuntimeException("Did not find userId: " + dictionary.getUser().getId());
        }
        DictionaryDTO result = new DictionaryDTO();
        result.setId(dictionary.getId());
        result.setLanguage(dictionary.getLanguage());
        result.setCreateTime(dictionary.getCreateTime());
        result.setUpdateTime(dictionary.getUpdateTime());
        result.setUserId(dictionary.getUser().getId());
        if (dictionary.getWords()!=null){
            List newWords = new ArrayList();
            for (Word tempWord : dictionary.getWords()){
                newWords.add(wordService.writeWordDTO(tempWord));
            }
            result.setWords(newWords);
        }
        return result;
    }

    /**
     * DEFINING CRUD METHODS:
     *  findAll()
     *  findUser()
     *  createUser()
     *  updateUser()
     *  deleteUser()
     * **/

    /** GET METHODS **/

    @Override
    public List<DictionaryDTO> findAll() {
        List<DictionaryDTO> dictionaryDTOS = new ArrayList<>();
        for (Dictionary tempDictionary : dictionaryRepository.findAll()){
            dictionaryDTOS.add(writeDictionaryDTO(tempDictionary));
        }
        return dictionaryDTOS;
    }

    @Override
    public DictionaryDTO findDictionary(Long dictionaryId) {
        Optional<Dictionary> optionalDictionary = dictionaryRepository.findById(dictionaryId);
        if (optionalDictionary.isEmpty()){
            throw new RuntimeException("Did not find dictionaryId: " + dictionaryId);
        }
        return writeDictionaryDTO(optionalDictionary.get());
    }

    /** POST METHODS **/

    @Override
    public DictionaryDTO createDictionary(DictionaryDTO source) {
        User user = userService.dtoToEntity(userService.findUser(source.getUserId()));
        Dictionary current = new Dictionary(source.getLanguage(),user);
        user.addDictionary(current);
        if (source.getWords()!=null){
            createWords(source.getWords(), current);
        }
        return writeDictionaryDTO(dictionaryRepository.save(current));
    }

    public void createWords(List<WordDTO> wordDTOS, Dictionary dictionary){
        for (WordDTO tempWordDTO : wordDTOS){
            Word newWord = new Word(tempWordDTO.getOriginal(),tempWordDTO.getExamined(),dictionary);
            dictionary.addWord(newWord);
        }
    }

    /** PUT METHODS **/

    @Override
    public DictionaryDTO updateDictionary(DictionaryDTO source) {
        Dictionary current = dtoToEntity(findDictionary(source.getId()));
        if (source.getUserId() != null){
            User user = userService.dtoToEntity(userService.findUser(source.getUserId()));
            current.setUser(user);
        }
        if (source.getLanguage() != null){
            current.setLanguage(source.getLanguage());
        }
        if (source.getCreateTime() != null){
            current.setCreateTime(source.getCreateTime());
        }
        if (source.getUpdateTime() != null){
            current.setUpdateTime(source.getUpdateTime());
        }

        return writeDictionaryDTO(dictionaryRepository.save(current));
    }

    /** DELETE METHODS **/

    @Override
    public void deleteDictionary(Long dictionaryId) {
        dictionaryRepository.delete(dtoToEntity(findDictionary(dictionaryId)));
    }
}
