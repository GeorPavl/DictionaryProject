package com.dictionary.service.dictionary;

import com.dictionary.dto.DictionaryDTO;
import com.dictionary.model.Dictionary;

import java.util.List;

public interface DictionaryService {

    Dictionary dtoToEntity(DictionaryDTO dictionaryDTO);

    List<DictionaryDTO> findAll();

    DictionaryDTO findDictionary(Long dictionaryId);

    DictionaryDTO createDictionary(DictionaryDTO dictionaryDTO);

    DictionaryDTO updateDictionary(DictionaryDTO dictionaryDTO);

    void deleteDictionary(Long dictionaryId);

    DictionaryDTO writeDictionaryDTO(Dictionary dictionary);
}
