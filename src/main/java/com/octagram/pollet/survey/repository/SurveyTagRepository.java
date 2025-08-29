package com.octagram.pollet.survey.repository;

import com.octagram.pollet.survey.domain.model.SurveyTag;
import com.octagram.pollet.survey.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyTagRepository extends JpaRepository<SurveyTag, Long> {
    // 실제 설문에 한 번이라도 연결된 태그만 조회
    @Query("""
        select distinct st.tag
        from SurveyTag st
    """)
    List<Tag> findAllUsedTags();


}
