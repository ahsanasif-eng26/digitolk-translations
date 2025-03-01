package com.digital_tolk.translations.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "translation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Translation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "locale", nullable = false, length = 10)
  private String locale;

  @Column(length = 50)
  private String tag;

  @Column(name = "translation_key", nullable = false, length = 255)
  private String key;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @CreationTimestamp
  private LocalDateTime createdDate;

  @UpdateTimestamp
  private LocalDateTime updatedDate;
}
