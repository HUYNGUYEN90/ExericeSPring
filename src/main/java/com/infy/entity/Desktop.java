package com.infy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.infy.dto.DesktopStatus;
@Entity
public class Desktop {
	@Id
//	@Column(name="desktop_id")
		private String desktopId;
//	@Column(name="desktop_make")
		private String desktopMake;
//	@Column(name="desktop_model")
		private String desktopModel;
//	@Column(name="desktop_status")
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
		public int hashCode() {
			return Objects.hash(desktopId, desktopMake, desktopModel, desktopStatus);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Desktop other = (Desktop) obj;
			return Objects.equals(desktopId, other.desktopId) && Objects.equals(desktopMake, other.desktopMake)
					&& Objects.equals(desktopModel, other.desktopModel) && desktopStatus == other.desktopStatus;
		}
		
}
