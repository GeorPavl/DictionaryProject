package com.dictionary.service.meaning;

import com.dictionary.dto.MeaningDTO;
import com.dictionary.model.Meaning;

import java.util.List;

public interface MeaningService {

    Meaning dtoToEntity(MeaningDTO source);

    MeaningDTO writeMeaningDTO(Meaning source);

    List<MeaningDTO> findAll();

    MeaningDTO findMeaning(Long meaningId);

    MeaningDTO createMeaning(MeaningDTO source);

    MeaningDTO updateMeaning(MeaningDTO source);

    void deleteMeaning(Long meaningId);
}
