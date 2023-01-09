package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;


public class UpdatePlanForm {
	private Integer id;
	
	@NotBlank(message="プラン名を入力してください")
	private String name;

	@NotNull(message="部屋タイプを選択してください")
	private Integer roomId;
	
	@NotNull(message="朝食の有無を選択してください")
	private Integer breakfast;
	
	@NotNull(message="夕食の有無を選択してください")
	private Integer dinner;
	
	@NotNull(message="基本料金を入力してください")
	private Integer basicCharge;
	
	@NotNull(message="追加料金を入力してください")
	private Integer additionalCharge;
	
	@NotBlank(message="プラン説明を入力してください")
	private String comment;
	
	private MultipartFile image;
	
	@NotBlank(message="どちらかを選択してください")
	private String nowImage;
	

	public String getNowImage() {
		return nowImage;
	}

	public void setNowImage(String nowImage) {
		this.nowImage = nowImage;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UpdatePlanForm [id=" + id + ", name=" + name + ", roomId=" + roomId + ", breakfast=" + breakfast
				+ ", dinner=" + dinner + ", basicCharge=" + basicCharge + ", additionalCharge=" + additionalCharge
				+ ", comment=" + comment + ", image=" + image + ", nowImage=" + nowImage + "]";
	}

	

}
