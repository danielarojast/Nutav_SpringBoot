package com.riwi.nutav.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.nutav.api.dto.request.GuideRequest;
import com.riwi.nutav.api.dto.response.GuideResp;
import com.riwi.nutav.infraestructure.abstract_service.IGuideService;
import com.riwi.nutav.utils.enums.SortType;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/guides")
@AllArgsConstructor
@Tag(name= "Guides")
public class GuideController {

    @Autowired
    private final IGuideService guideService;

    @GetMapping
    public ResponseEntity<Page<GuideResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.guideService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GuideResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.guideService.get(id));
    }

    @PostMapping
    public ResponseEntity<GuideResp> insert(
            @Validated @RequestBody GuideRequest request) {
        return ResponseEntity.ok(this.guideService.create(request));
    }

     @PutMapping(path = "/{id}")
    public ResponseEntity<GuideResp> update(
            @Validated @RequestBody GuideRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.guideService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.guideService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
