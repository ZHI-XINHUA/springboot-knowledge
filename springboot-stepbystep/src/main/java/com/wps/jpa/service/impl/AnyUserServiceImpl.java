package com.wps.jpa.service.impl;

import com.wps.jpa.entity.UserBO;
import com.wps.jpa.repository.AnyUserRepository;
import com.wps.jpa.service.AnyUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service
public class AnyUserServiceImpl implements AnyUserService {

    @Resource(name = "anyUserRepository")
    private AnyUserRepository anyUserRepository;

    @Override
    public UserBO findById(String id) {
        return anyUserRepository.findById(id).get();
    }

    @Override
    public List<UserBO> findAll() {
        return  anyUserRepository.findAll();
    }

    @Override
    public void saveOrDelete(UserBO user){
        delete("e98b0351-9fa9-4de7-92e9-cf5c79c663ef");
        save(user);

    }

    @Transactional
    @Override
    public UserBO save(UserBO user) {
        UserBO userBO = (UserBO) anyUserRepository.save(user);
        try {
            String error = null;
            error.split("xx");
        }catch (Exception e){
           e.printStackTrace();
        }

        return userBO;
    }

    @Override
    public void delete(String id) {
        anyUserRepository.deleteById(id);
    }

    @Override
    public Page<UserBO> finaAll(Pageable pageable) {
        return anyUserRepository.findAll(pageable);
    }
}
