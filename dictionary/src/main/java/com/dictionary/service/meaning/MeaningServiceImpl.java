package com.dictionary.service.meaning;

import com.dictionary.dto.MeaningDTO;
import com.dictionary.model.Meaning;
import com.dictionary.model.Word;
import com.dictionary.repository.MeaningRepository;
import com.dictionary.repository.WordRepository;
import com.dictionary.service.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeaningServiceImpl implements MeaningService{

    /**
     * INJECT REPOSITORIES
     *  AND SERVICES
     *  **/
    @Autowired
    private MeaningRepository meaningRepository;
    @Autowired
    private WordService wordService;
    @Autowired
    private WordRepository wordRepository;

    /**
     * Convert Entity - DTO
     *  dtoToEntity
     *  writeMeaningDTO
     * */

    @Override
    public Meaning dtoToEntity(MeaningDTO source) {
        Meaning result = new Meaning();
        if (source.getId() != null){
            result.setId(source.getId());
        }
        if (source.getDefinition() != null){
            result.setDefinition(source.getDefinition());
        }
        if (source.getExample() != null){
            result.setExample(source.getExample());
        }
        if (source.getType() != null){
            result.setType(Meaning.EnumWordType.decode(source.getType()));
        }
        if (source.getWordId() != null){
            Optional<Word> optionalWord = wordRepository.findById(source.getWordId());
            if (optionalWord.isEmpty()){
                throw new RuntimeException("Did not find wordId: " + source.getWordId());
            }
            result.setWord(optionalWord.get());
        }
        return result;
    }

    @Override
    public MeaningDTO writeMeaningDTO(Meaning source) {
        MeaningDTO result = new MeaningDTO();
        result.setId(source.getId());
        result.setDefinition(source.getDefinition());
        result.setExample(source.getExample());
        result.setType(source.getType().getCode());
        result.setWordId(source.getWord().getId());
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
    public List<MeaningDTO> findAll() {
        List<MeaningDTO> meaningDTOS = new ArrayList<>();
        for (Meaning tempMeaning : meaningRepository.findAll()){
            meaningDTOS.add(writeMeaningDTO(tempMeaning));
        }
        return meaningDTOS;
    }

    @Override
    public MeaningDTO findMeaning(Long meaningId) {
        Optional<Meaning> optionalMeaning = meaningRepository.findById(meaningId);
        if (optionalMeaning.isEmpty()){
            throw new RuntimeException("Did not find meaningId: " + meaningId);
        }
        return writeMeaningDTO(optionalMeaning.get());
    }

    @Override
    public MeaningDTO createMeaning(MeaningDTO source) {
        Word word = wordService.dtoToEntity(wordService.findWord(source.getWordId()));
        source.getType();
        Meaning.EnumWordType type = Meaning.EnumWordType.values()[source.getType()];
        Meaning.EnumWordType type1 = Meaning.EnumWordType.decode(source.getType());
        Meaning current = new Meaning(source.getDefinition(), source.getExample(),word, Meaning.EnumWordType.decode(source.getType()));
        word.addMeaning(current);
        return writeMeaningDTO(meaningRepository.save(current));
    }

    @Override
    public MeaningDTO updateMeaning(MeaningDTO source) {

        Meaning current = current = dtoToEntity(findMeaning(source.getId()));

        if (source.getWordId() != null){
            Word word = wordService.dtoToEntity(wordService.findWord(source.getWordId()));
            current.setWord(word);
        }
        if (source.getDefinition() != null){
            current.setDefinition(source.getDefinition());
        }
        if (source.getExample() != null){
            current.setExample(source.getExample());
        }
        if (source.getType() != null){
            current.setType(Meaning.EnumWordType.decode(source.getType()));
        }
        return writeMeaningDTO(current);
    }

    @Override
    public void deleteMeaning(Long meaningId) {
        meaningRepository.deleteById(meaningId);
    }
}
