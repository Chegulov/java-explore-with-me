package ru.practicum.controllers.admin_api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.user.NewUserRequest;
import ru.practicum.dto.user.UserDto;
import ru.practicum.services.admin_api.AdminUsersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class AdminUsersController {
    private final AdminUsersService adminUsersService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
                                  @RequestParam(defaultValue = "0") Integer from,
                                  @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);

        return adminUsersService.getUsers(ids, pageable);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody NewUserRequest newUserRequest) {
        return adminUsersService.create(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        adminUsersService.delete(userId);
    }
}
