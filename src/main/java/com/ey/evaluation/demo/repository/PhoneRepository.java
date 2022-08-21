package com.ey.evaluation.demo.repository;

import com.ey.evaluation.demo.model.PhoneJPA;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneJPA, Long> {
}
