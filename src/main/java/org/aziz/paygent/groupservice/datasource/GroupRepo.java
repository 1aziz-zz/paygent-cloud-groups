package org.aziz.paygent.groupservice.datasource;

import org.aziz.paygent.groupservice.models.entities.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepo extends CrudRepository<Group, String> {
    Group findByTitle(String name);
}

