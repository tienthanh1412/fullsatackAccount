package com.vti.entites;

public enum PositionName {
	DEV1, DEV2, PM, LEADER, SCRUM_MASTER, TEST;
	
//	nếu sử dụng hàm chuyển đổi thì thêm vào và cấu hình loại enum class Position
//	DEV1("DEV1"), DEV2("DEV2"), PM("PM"), LEADER("LEADER"), SCRUM_MASTER("SCRUM_MASTER"), TEST("TEST");
//
//	private String positionName;
//
//	private PositionName(String positionName) {
//		this.positionName = positionName;
//	}
//
//	public String getPositionName() {
//		return positionName;
//	}
//
//	public static PositionName toEnum(String splPositionName) {
//		for (PositionName item : PositionName.values()) {
//			if (item.getPositionName().equals(splPositionName)) {
//				return item;
//			}
//
//		}
//		return null;
//	}
}
