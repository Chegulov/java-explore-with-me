package ru.practicum.controllers.admin_api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.comment.CommentDto;
import ru.practicum.dto.comment.CommentUpdateRequest;
import ru.practicum.services.admin_api.AdminCommentsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class AdminCommentsController {
    private final AdminCommentsService adminCommentsService;

    @PatchMapping("/{commentId}")
    public CommentDto updateState(@PathVariable Long commentId,
                                  @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return adminCommentsService.updateState(commentId, commentUpdateRequest);
    }
}
