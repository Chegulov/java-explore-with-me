package ru.practicum.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class NewCompilationDto {
    private List<Long> events;
    private Boolean pinned;
    @Size(min = 1, max = 50)
    @NotBlank(message = "заголовок не может быть пустым")
    private String title;
}
