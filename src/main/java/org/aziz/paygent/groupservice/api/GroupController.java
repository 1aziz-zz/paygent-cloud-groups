package org.aziz.paygent.groupservice.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aziz.paygent.groupservice.exceptions.GroupNotFoundException;
import org.aziz.paygent.groupservice.models.entities.Group;
import org.aziz.paygent.groupservice.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping
    public Iterable<Group> getAll() {
        return groupService.getAll();
    }

      @GetMapping(path = "{id}")
    public Group get(@PathVariable(value = "id") String id) {
        return groupService.get(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }

    @PostMapping
    public Group create(@RequestBody @Valid Group group) throws JsonProcessingException {
         return groupService.save(group);
    }

    @PutMapping("{id}")
    public Group update(@PathVariable(value = "id") String id, @RequestBody Group groupDetails) {
        Group group = groupService.validatedGroup(id);
        group.setTitle(groupDetails.getTitle());
        group.setDescription(groupDetails.getDescription());
        return groupService.update(group);
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") String id) {
        groupService.delete(id);
    }

}