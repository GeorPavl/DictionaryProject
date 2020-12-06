package com.dictionary.service.word;

import com.dictionary.dto.WordDTO;
import com.dictionary.model.Word;

import java.util.List;

public interface WordService {

    Word dtoToEntity(WordDTO wordDTO);

    List<WordDTO> findAll();

    WordDTO findWord(Long wordId);

    WordDTO createWord(WordDTO wordDTO);

    WordDTO updateWord(WordDTO wordDTO);

    void deleteWord(Long wordId);

    WordDTO writeWordDTO(Word word);
}
