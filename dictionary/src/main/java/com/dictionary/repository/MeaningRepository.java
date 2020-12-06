package com.dictionary.repository;

import com.dictionary.model.Meaning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeaningRepository extends JpaRepository<Meaning, Long> {

    public List<Meaning> findByType(Meaning.EnumWordType type);
}
