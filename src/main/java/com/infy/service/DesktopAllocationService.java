package com.infy.service;

import com.infy.dto.DesktopDTO;
import com.infy.dto.TraineeDTO;
import com.infy.exception.InfyTrainingException;

public interface DesktopAllocationService {

	public TraineeDTO getTraineeDetails(Integer traineeId) throws InfyTrainingException;

	public Integer addTrainee(TraineeDTO traineeDTO) throws InfyTrainingException;

	public void allocateDesktop(Integer traineeId, String desktopId) throws InfyTrainingException;

	public void deallocateDesktop(Integer traineeId) throws InfyTrainingException;

	public void deleteTrainee(Integer traineeId) throws InfyTrainingException;
	
	public DesktopDTO getDesktopDetails(String desktopId) throws InfyTrainingException;
}
