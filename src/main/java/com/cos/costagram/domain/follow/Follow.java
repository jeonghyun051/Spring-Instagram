package com.cos.costagram.domain.follow;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.costagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		name="follow",
		uniqueConstraints={
			@UniqueConstraint(
				name = "follow_uk",
				columnNames={"fromUserId","toUserId"}
			)
		}
	) //중복으로 될수있는걸 막기
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnoreProperties({"images"})
	@ManyToOne
	@JoinColumn(name = "fromUserId")
	private User fromUser; // ~~로 부터, 팔로우를 하는 애
	
	@JsonIgnoreProperties({"images"})
	@ManyToOne
	@JoinColumn(name = "toUserId")
	private User toUser; // ~~를, 팔로우를 당하는 애
	
	@CreationTimestamp // 자동으로 현재시간 담김
	private Timestamp createDate;
}
