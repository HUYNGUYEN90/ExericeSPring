package com.infy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.DesktopDTO;
import com.infy.dto.TraineeDTO;
import com.infy.service.DesktopAllocationService;

@SpringBootApplication
public class InfyTrainingToTraineeApplication implements CommandLineRunner {
	
	public static final Log LOGGER = LogFactory.getLog(InfyTrainingToTraineeApplication.class);

	@Autowired
	DesktopAllocationService allocationService;
	
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(InfyTrainingToTraineeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//addTrainee();
	getTraineeDetails();
//		getDesktopDetails();
//		 allocateDesktop();
//		 deallocateDesktop();
//		 deleteTrainee();

	}

	void getTraineeDetails() {
		try {
			Integer traineeId = 800001;

			TraineeDTO traineeDTO = allocationService.getTraineeDetails(traineeId);

			LOGGER.info(traineeDTO);
		} catch (Exception e) {
			LOGGER.info(
					environment.getProperty(e.getMessage(), "Some exception occureed. Please check log file."));

		}
	}

	void getDesktopDetails() {
		try {
			String desktopId = "MYSGEC111111D";

			DesktopDTO desktopDTO = allocationService.getDesktopDetails(desktopId);

			LOGGER.info(desktopDTO);

		} catch (Exception e) {
			LOGGER.info(
					environment.getProperty(e.getMessage(), "Some exception occureed. Please check log file."));

		}
	}

	void addTrainee() {
		try {
			TraineeDTO traineeDTO = new TraineeDTO();
			traineeDTO.setTraineeName("John");

			Integer traineeId = allocationService.addTrainee(traineeDTO);

			String message = environment.getProperty("UserInterface.TRAINEE_SUCCESS");
			LOGGER.info(message + traineeId);
		} catch (Exception e) {
			LOGGER.info(
					environment.getProperty(e.getMessage(), "Some exception occureed. Please check log file."));

		}
	}

	void allocateDesktop() {
		try {
			String desktopId = "MYSGEC222222D";
			Integer traineeId = 800002;

			allocationService.allocateDesktop(traineeId, desktopId);
			LOGGER.info(environment.getProperty("UserInterface.DESKTOP_ALLOCATED_SUCCESS"));

		} catch (Exception e) {
			LOGGER.info(
					environment.getProperty(e.getMessage(), "Some exception occureed. Please check log file."));

		}
	}

	void deallocateDesktop() {
		try {

			Integer traineeId = 800001;

			allocationService.deallocateDesktop(traineeId);
			LOGGER.info(environment.getProperty("UserInterface.DESKTOP_DEALLOCATED_SUCCESS"));

		} catch (Exception e) {
			LOGGER.info(
					environment.getProperty(e.getMessage(), "Some exception occureed. Please check log file."));

		}
	}

	void deleteTrainee() {
		try {

			Integer traineeId = 800001;

			allocationService.deleteTrainee(traineeId);
			LOGGER.info(environment.getProperty("UserInterface.TRAINEE_DELETED_SUCCESS"));

		} catch (Exception e) {
			LOGGER.info(
					environment.getProperty(e.getMessage(), "Some exception occureed. Please check log file."));

		}
	}
}
