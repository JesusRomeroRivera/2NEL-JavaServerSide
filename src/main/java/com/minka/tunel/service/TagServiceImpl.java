package com.minka.tunel.service;

import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.repository.ProfileRepository;
import com.minka.tunel.domain.repository.TagRepository;
import com.minka.tunel.domain.service.TagService;
import com.minka.tunel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Page<Tag> getAllTagsByUserId(Long userId, Pageable pageable) {
        return profileRepository.findById(userId)
                .map(user -> {
                    List<Tag> tags = user.getProfileTags();
                    int tagsCount = tags.size();
                    return new PageImpl<>(tags, pageable, tagsCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", userId));
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagDetails) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    tag.setName(tagDetails.getName());
                    return tagRepository.save(tag);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    tagRepository.delete(tag);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }
}
