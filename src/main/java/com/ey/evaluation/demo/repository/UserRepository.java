package com.ey.evaluation.demo.repository;

import com.ey.evaluation.demo.model.UserJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserJPA, Long> {

    List<UserJPA> findByEmail(String email);
}
