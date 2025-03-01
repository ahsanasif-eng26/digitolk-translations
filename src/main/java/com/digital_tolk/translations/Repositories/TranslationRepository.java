package com.digital_tolk.translations.Repositories;

import com.digital_tolk.translations.Entities.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long>, JpaSpecificationExecutor<Translation> {
  Optional<Translation> findByLocaleAndKey(String locale, String key);
}
