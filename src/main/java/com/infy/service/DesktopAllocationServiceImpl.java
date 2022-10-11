package com.infy.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.DesktopDTO;
import com.infy.dto.DesktopStatus;
import com.infy.dto.TraineeDTO;
import com.infy.entity.Desktop;
import com.infy.entity.Trainee;
import com.infy.exception.InfyTrainingException;
import com.infy.repository.DesktopRepository;
import com.infy.repository.TraineeRespository;
@Service(value="desktopAllocationService")
@Transactional
public class DesktopAllocationServiceImpl implements DesktopAllocationService {
	
	@Autowired
	private  TraineeRespository traineeRespository;
	
	@Autowired
	private DesktopRepository desktopRepository;

	/**
	 This method calls findById method of traineeRespository sending  traineeId received in parameter
	 @param Integer traineeId
	 @return TraineeDTO object object populated from entity object returned by findById method of traineeRespository
	 @throws Service.TRAINEE_NOT_FOUND exception if object returned by findById method of traineeRespository is null
	 */
	@Override
	public TraineeDTO getTraineeDetails(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRespository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(()->new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		TraineeDTO traineedto = new TraineeDTO();
		traineedto.setTraineeId(trainee.getTraineeId());
		traineedto.setTraineeName(trainee.getTraineeName());
		DesktopDTO desktopdto = new DesktopDTO();
		desktopdto.setDesktopId(trainee.getDesktop().getDesktopId());
		desktopdto.setDesktopMake(trainee.getDesktop().getDesktopMake());
		desktopdto.setDesktopModel(trainee.getDesktop().getDesktopModel());
		desktopdto.setDesktopStatus(trainee.getDesktop().getDesktopStatus());
		traineedto.setDesktop(desktopdto);
		return traineedto;
	}
	
	/**
	 This method calls findById method of desktopRepository sending  desktopId received in parameter
	 @param String despktopId
	 @return DesktopDTO object populated from entity object returned by findById method of desktopRepository
	 @throws Service.DESKTOP_NOT_FOUND exception if object returned by findById method of desktopRepository is null
	 */
	@Override
	public DesktopDTO getDesktopDetails(String desktopId) throws InfyTrainingException {
		Optional<Desktop> optional = desktopRepository.findById(desktopId);
		Desktop desktop = optional.orElseThrow(()->new InfyTrainingException("Service.DESKTOP_NOT_FOUND")); 
		DesktopDTO desktopdto = new DesktopDTO();
		desktopdto.setDesktopId(desktop.getDesktopId());
		desktopdto.setDesktopMake(desktop.getDesktopMake());
		desktopdto.setDesktopModel(desktop.getDesktopModel());
		desktopdto.setDesktopStatus(desktop.getDesktopStatus());
		return desktopdto;
	}

	/**
	 This method calls save method of traineeRespository sending Trainee entity object populated by trainee object received in parameter
	 @param TraineeDTO object
	 @return Integer traineeId after calling save method of traineeRespository
	 
	 */
	@Override
	public Integer addTrainee(TraineeDTO traineeDTO) throws InfyTrainingException {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(traineeDTO.getTraineeId());
		trainee.setTraineeName(traineeDTO.getTraineeName());
		traineeRespository.save(trainee);
		
		return trainee.getTraineeId();

	}

	/**
	 This method calls findById method of traineeRespository sending  traineeId received in parameter, then 
	 checks whether the trainee is allocated to with a desktop, then calls
	 findById method of traineeRespository sending  desktopId received in parameter, then
	 checks whether the desktop is allocated to a trainee and then it allocates the desktop to the trainee.
	 @param Integer traineeId, String desktopId
	 @throws 
	 -Service.TRAINEE_NOT_FOUND exception if object returned by findById method of traineeRespository is null
	 -Service.TRAINEE_DESKTOP_FOUND exception if desktop is already allocated to this trainee
	 -Service.DESKTOP_NOT_FOUND exception if object returned by findById method of desktopRepository is null
	 -Service.DESKTOP_ALREADY_ALLOCATED exception if desktop is already allocated to some other trainee
	 */
	@Override
	public void allocateDesktop(Integer traineeId, String desktopId) throws InfyTrainingException {

		Optional<Trainee> optional_trainee = traineeRespository.findById(traineeId);
	
		Optional<Desktop> optional_desktop = desktopRepository.findById(desktopId);
		Trainee trainee = optional_trainee.orElseThrow(()->new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		Desktop desktop = optional_desktop.orElseThrow(()->new InfyTrainingException("Service.DESKTOP_NOT_FOUND")); 
	
		if(trainee.getDesktop()!=null)
		{
			throw new InfyTrainingException("TRAINEE_DESKTOP_FOUND");
		}
		if(desktop.getDesktopStatus().equals("AVAILABLE"))
		{
			throw new InfyTrainingException("DESKTOP_ALREADY_ALLOCATED");
		}
		
	DesktopDTO desktopdto = new DesktopDTO();
		TraineeDTO trainne_new = new TraineeDTO();
		desktop.setDesktopStatus(DesktopStatus.AVAILABLE);
		desktopdto.setDesktopStatus(desktop.getDesktopStatus());
		desktopdto.setDesktopId(desktopId);
	trainne_new.setDesktop(desktopdto);
		trainee.setDesktop(trainne_new.getDesktop());
		traineeRespository.save(trainee);
		
	
	}
	/**
	 This method calls findById method of traineeRespository sending  traineeId received in parameter, then
	 it deallocates the desktop allocated to the trainee.
	 @param Integer traineeId
	 @throws
	 - Service.TRAINEE_NOT_FOUND exception if object returned by findById method of traineeRespository is null
	 - Service.DESKTOP_NOT_ALLOCATED exception if no desktop is allocated to the trainee
	 */
	@Override
	public void deallocateDesktop(Integer traineeId) throws InfyTrainingException {
		
		
		Optional<Trainee> optional_trainee = traineeRespository.findById(traineeId);
		Trainee trainee = optional_trainee.orElseThrow(()->new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		if(trainee.getDesktop()==null)
		{
			throw new InfyTrainingException("Service.DESKTOP_NOT_ALLOCATED");
		}
		traineeRespository.deleteById(traineeId);
	}
	/**
	 This method first calls findById method of traineeRespository sending  traineeId received in parameter,
	 then calls delete method of traineeRespository sending traineeId received in parameter
	 @param Integer traineeId
	 @throws Service.TRAINEE_NOT_FOUND exception if Trainee object returned by findById method of traineeRespository is null 
	 */
	@Override
	public void deleteTrainee(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional_trainee = traineeRespository.findById(traineeId);
		Trainee trainee = optional_trainee.orElseThrow(()->new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
	trainee.setDesktop(null);
	traineeRespository.deleteById(traineeId);
	}
	
}
