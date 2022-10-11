package com.infy.repository;


import org.springframework.data.repository.CrudRepository;
import com.infy.entity.Trainee;

public interface TraineeRespository extends CrudRepository<Trainee, Integer> {
	
}
