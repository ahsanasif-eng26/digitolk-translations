package com.digital_tolk.translations.services;

import com.digital_tolk.translations.Entities.Translation;
import java.util.List;

public interface TranslationService {
  List<Translation> getAllTranslations(String locale, String tag, int offset, int limit);
  Translation createTranslation(Translation translation);
  Translation updateTranslation(Long id, Translation translation);
  void deleteTranslation(Long id);
}
