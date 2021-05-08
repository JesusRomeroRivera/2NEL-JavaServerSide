package com.minka.tunel.domain.service;

import com.minka.tunel.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TagService {
    Page<Tag> getAllTags(Pageable pageable);
    Page<Tag> getAllTagsByUserId(Long userId, Pageable pageable);
    Tag getTagById(Long tagId);
    Tag createTag(Tag tag);
    Tag updateTag(Long tagId, Tag tagDetails);
    ResponseEntity<?> deleteTag(Long tagId);
}

