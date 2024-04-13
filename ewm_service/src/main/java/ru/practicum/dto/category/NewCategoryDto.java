package ru.practicum.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCategoryDto {
    @Size(min = 1)
    @Size(max = 50)
    @NotBlank(message = "Имя категории не может быть пустым")
    private String name;
}
