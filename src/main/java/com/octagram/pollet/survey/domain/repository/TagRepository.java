package com.octagram.pollet.survey.domain.repository;

import com.octagram.pollet.survey.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
