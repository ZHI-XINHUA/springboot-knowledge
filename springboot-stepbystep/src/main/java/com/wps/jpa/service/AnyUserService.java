package com.wps.jpa.service;


import com.wps.jpa.entity.UserBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnyUserService {

    UserBO findById(String id);

    List<UserBO> findAll();

    void saveOrDelete(UserBO user);

    UserBO save(UserBO user);

    void delete(String id);

    Page<UserBO> finaAll(Pageable pageable);
}
