package com.math;

import org.springframework.data.repository.CrudRepository;

//@EnableJpaRepositories
public interface OperationRepository extends CrudRepository<MathOperation, Integer> {

}
