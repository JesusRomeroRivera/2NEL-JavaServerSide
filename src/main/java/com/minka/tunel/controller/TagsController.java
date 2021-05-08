package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.service.TagService;
import com.minka.tunel.resource.SaveTagResource;
import com.minka.tunel.resource.TagResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TagsController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public Page<TagResource> getAllTags(Pageable pageable) {
        List<TagResource> tags = tagService.getAllTags(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int tagsCount = tags.size();
        return new PageImpl<>(tags, pageable, tagsCount);
    }

    @GetMapping("/posts/{postId}/tags")
    public Page<TagResource> getAllTagsByUserId(
            @PathVariable Long postId,
            Pageable pageable) {
        List<TagResource> tags = tagService.getAllTagsByUserId(postId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int tagsCount = tags.size();
        return new PageImpl<>(tags, pageable, tagsCount);
    }

    @GetMapping("/tags/{tagId}")
    public TagResource getTagById(
            @PathVariable Long tagId) {
        return convertToResource(tagService.getTagById(tagId));
    }

    @PostMapping("/tags")
    public TagResource createTag(
            @Valid @RequestBody SaveTagResource resource){
        return convertToResource(tagService.createTag(convertToEntity(resource)));
    }

    @PutMapping("/tags/{tagId}")
    public TagResource updateTag(
            @PathVariable Long tagId,
            @Valid @RequestBody SaveTagResource resource) {
        return convertToResource(tagService.updateTag(tagId, convertToEntity(resource)));
    }

    private Tag convertToEntity(SaveTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResource(Tag entity) {
        return mapper.map(entity, TagResource.class);
    }
}
