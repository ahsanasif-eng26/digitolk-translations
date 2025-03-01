package com.digital_tolk.translations.services.imp;

import com.digital_tolk.translations.Entities.Translation;
import com.digital_tolk.translations.services.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {
  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<Translation> rowMapper = (rs, rowNum) -> {
    Translation translation = new Translation();
    translation.setId(rs.getLong("id"));
    translation.setLocale(rs.getString("locale"));
    translation.setTag(rs.getString("tag"));
    translation.setKey(rs.getString("translation_key"));
    translation.setContent(rs.getString("content"));
    return translation;
  };

  @Override
  public List<Translation> getAllTranslations(String locale, String tag, int offset, int limit) {
    StringBuilder sql = new StringBuilder("SELECT * FROM translation WHERE 1=1");
    List<Object> params = new ArrayList<>();

    if (locale != null) {
      sql.append(" AND locale = ?");
      params.add(locale);
    }
    if (tag != null) {
      sql.append(" AND tag = ?");
      params.add(tag);
    }
    sql.append(" LIMIT ? OFFSET ?");

    params.add(limit);
    params.add(offset);

    return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
  }

  @Override
  public Translation createTranslation(Translation translation) {
    String sql = "INSERT INTO translation (locale, tag, translation_key, content) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, translation.getLocale(), translation.getTag(), translation.getKey(), translation.getContent());

    // Retrieve the last inserted translation (optional, based on your DB)
    return translation;
  }

  @Override
  public Translation updateTranslation(Long id, Translation translation) {
    String sql = "UPDATE translation SET locale = ?, tag = ?, translation_key = ?, content = ? WHERE id = ?";
    int rowsUpdated = jdbcTemplate.update(sql, translation.getLocale(), translation.getTag(), translation.getKey(), translation.getContent(), id);

    if (rowsUpdated == 0) {
      throw new RuntimeException("Translation not found");
    }
    return translation;
  }

  @Override
  public void deleteTranslation(Long id) {
    String sql = "DELETE FROM translation WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }
}
