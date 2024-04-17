package ru.practicum.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.models.Event;
import ru.practicum.models.User;
import ru.practicum.models.enums.CommentState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private LocalDateTime created_on;
    private LocalDateTime updated_on;
    private LocalDateTime published_on;
    private User author;
    private Event event;
    private CommentState state;
}
