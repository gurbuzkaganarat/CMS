package com.gka.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="refresh_token")
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken  extends BaseEntity{
	
	
	@Column(name="refresh_token", nullable = false, unique = true)
	private String refreshToken;
	
	@Column(name="expiry_date" , nullable = false)
	private  Date expiredDate;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;

}
