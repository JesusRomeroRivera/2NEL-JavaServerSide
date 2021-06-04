package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.service.TagService;
import com.minka.tunel.resource.SaveTagResource;
import com.minka.tunel.resource.TagResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get Tags", description = "Get All Tags", tags = {"tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Tags returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/tags")
    public Page<TagResource> getAllTags(Pageable pageable) {
        List<TagResource> tags = tagService.getAllTags(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int tagsCount = tags.size();
        return new PageImpl<>(tags, pageable, tagsCount);
    }

    @Operation(summary = "Get Tag by UserID", description = "Get a specific Tag by UserID", tags = {"tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/profiles/{userId}/tags")
    public Page<TagResource> getAllTagsByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        List<TagResource> tags = tagService.getAllTagsByUserId(userId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int tagsCount = tags.size();
        return new PageImpl<>(tags, pageable, tagsCount);
    }

    @Operation(summary = "Get Tag by ID", description = "Get a specific Tag by its ID", tags = {"tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/tags/{tagId}")
    public TagResource getTagById(
            @PathVariable Long tagId) {
        return convertToResource(tagService.getTagById(tagId));
    }

    @Operation(summary = "Create a Tag", description = "Create a Tag", tags = {"tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/tags")
    public TagResource createTag(
            @Valid @RequestBody SaveTagResource resource){
        return convertToResource(tagService.createTag(convertToEntity(resource)));
    }

    @Operation(summary = "Update a Tag", description = "Update a Tag", tags = {"tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/tags/{tagId}")
    public TagResource updateTag(
            @PathVariable Long tagId,
            @Valid @RequestBody SaveTagResource resource) {
        return convertToResource(tagService.updateTag(tagId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete a Tag", description = "Delete a Tag", tags = {"tags"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Long tagId) {
        return tagService.deleteTag(tagId);
    }

    private Tag convertToEntity(SaveTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResource(Tag entity) {
        return mapper.map(entity, TagResource.class);
    }
}
