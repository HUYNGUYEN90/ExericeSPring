package com.infy.dto;

public class DesktopDTO {

	private String desktopId;
	private String desktopMake;
	private String desktopModel;
	private DesktopStatus desktopStatus;

	public String getDesktopId() {
		return desktopId;
	}

	public void setDesktopId(String desktopId) {
		this.desktopId = desktopId;
	}

	public String getDesktopMake() {
		return desktopMake;
	}

	public void setDesktopMake(String desktopMake) {
		this.desktopMake = desktopMake;
	}

	public String getDesktopModel() {
		return desktopModel;
	}

	public void setDesktopModel(String desktopModel) {
		this.desktopModel = desktopModel;
	}

	public DesktopStatus getDesktopStatus() {
		return desktopStatus;
	}

	public void setDesktopStatus(DesktopStatus desktopStatus) {
		this.desktopStatus = desktopStatus;
	}

	@Override
	public String toString() {
		return "DesktopDTO [desktopId=" + desktopId + ", desktopMake=" + desktopMake + ", desktopModel=" + desktopModel
				+ ", desktopStatus=" + desktopStatus + "]";
	}
	

}
