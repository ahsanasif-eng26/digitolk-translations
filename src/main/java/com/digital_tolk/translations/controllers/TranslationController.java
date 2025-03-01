package com.digital_tolk.translations.controllers;

import com.digital_tolk.translations.Entities.Translation;
import com.digital_tolk.translations.services.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/translation")
@RequiredArgsConstructor
public class TranslationController {

  private final TranslationService translationService;

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
  public ResponseEntity<ResponseWrapper> getAllTranslations(
      @RequestParam(required = false) String locale,
      @RequestParam(required = false) String tag,
      @RequestParam(defaultValue = "0") int offset,
      @RequestParam(defaultValue = "10") int limit) {

    List<Translation> translations = translationService.getAllTranslations(locale, tag, offset, limit);
    return ResponseEntity
        .ok()
        .body(ResponseWrapper
            .builder()
            .status(Boolean.TRUE)
            .data(translations)
            .build());
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ResponseWrapper> createTranslation(@RequestBody Translation translation) {
    return ResponseEntity
        .ok()
        .body(ResponseWrapper
            .builder()
            .status(Boolean.TRUE)
            .data(translationService.createTranslation(translation))
            .build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ResponseWrapper> updateTranslation(
      @PathVariable Long id, @RequestBody Translation translation) {
    return ResponseEntity
        .ok()
        .body(ResponseWrapper
            .builder()
            .status(Boolean.TRUE)
            .data(translationService.updateTranslation(id, translation))
            .build());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ResponseWrapper> deleteTranslation(@PathVariable Long id) {
    translationService.deleteTranslation(id);
    return ResponseEntity
        .ok()
        .body(ResponseWrapper
            .builder()
            .status(Boolean.TRUE)
            .data("Translation deleted successfully")
            .build());
  }
}
