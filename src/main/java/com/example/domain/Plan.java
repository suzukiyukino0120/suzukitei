package com.example.domain;


public class Plan {
	
	private Integer id;
	
	private String name;

	private Integer roomId;
	
	private Room room;
	
	private Integer breakfast;
	
	private Integer dinner;
	
	private Integer basicCharge;
	
	private Integer additionalCharge;
	
	private String comment;
	
	private String image;
	
	
	
	public int getCalcTotalPrice(int numOfPeople) {
		return 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Integer breakfast) {
		this.breakfast = breakfast;
	}

	public Integer getDinner() {
		return dinner;
	}

	public void setDinner(Integer dinner) {
		this.dinner = dinner;
	}

	public Integer getBasicCharge() {
		return basicCharge;
	}

	public void setBasicCharge(Integer basicCharge) {
		this.basicCharge = basicCharge;
	}

	public Integer getAdditionalCharge() {
		return additionalCharge;
	}

	public void setAdditionalCharge(Integer additionalCharge) {
		this.additionalCharge = additionalCharge;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Plan [id=" + id + ", name=" + name + ", roomId=" + roomId + ", room=" + room + ", breakfast="
				+ breakfast + ", dinner=" + dinner + ", basicCharge=" + basicCharge + ", additionalCharge="
				+ additionalCharge + ", comment=" + comment + ", image=" + image + "]";
	}

	

	
	

	
	

}
