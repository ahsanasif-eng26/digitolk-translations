package com.digital_tolk.translations.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TranslationSeeder implements CommandLineRunner {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String SQL_INSERT =
      "INSERT INTO translation (locale, tag, translation_key, content) VALUES (?, ?, ?, ?)";

  private static final String[] LOCALES = {"en", "fr", "es", "de", "it"};
  private static final String[] TAGS = {"greeting", "error", "info", "warning"};

  @Override
  public void run(String... args) {
    List<Object[]> batchArgs = new ArrayList<>();
    Random random = new Random();

    for (int i = 1; i <= 100000; i++) {
      String locale = LOCALES[random.nextInt(LOCALES.length)];
      String tag = TAGS[random.nextInt(TAGS.length)];
      String translationKey = "key_" + i;
      String content = "Translation content " + i;

      batchArgs.add(new Object[]{locale, tag, translationKey, content});
    }

    jdbcTemplate.batchUpdate(SQL_INSERT, batchArgs);
    System.out.println("Inserted 100K records into 'translation' table!");
  }
}
