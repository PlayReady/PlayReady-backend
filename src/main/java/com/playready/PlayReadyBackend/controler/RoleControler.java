package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.RoleDto;
import com.playready.PlayReadyBackend.model.Role;
import com.playready.PlayReadyBackend.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleControler {

    private final RoleRepository repos;

    public RoleControler(RoleRepository repos){
        this.repos =repos;
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role r: repos.findAll()) {
            RoleDto roleDto = new RoleDto();
            roleDto.rolename = r.getRolename();
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }
}
