package com.infy.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.infy.dto.DesktopDTO;
@Entity
public class Trainee {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer traineeId;

	private String traineeName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="desktop_id",unique = true)
	private DesktopDTO desktop;
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public String getTraineeName() {
		return traineeName;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
	public DesktopDTO getDesktop() {
		return desktop;
	}
	public void setDesktop(DesktopDTO desktop) {
		this.desktop = desktop;
	}
	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", traineeName=" + traineeName + ", desktop=" + desktop + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(desktop, traineeId, traineeName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainee other = (Trainee) obj;
		return Objects.equals(desktop, other.desktop) && Objects.equals(traineeId, other.traineeId)
				&& Objects.equals(traineeName, other.traineeName);
	}
	
}
