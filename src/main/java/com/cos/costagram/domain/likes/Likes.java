package com.cos.costagram.domain.likes;

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

import com.cos.costagram.domain.image.Image;
import com.cos.costagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Like는 mysql에 안되서 s를 붙여줌
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		name="likes",
		uniqueConstraints={
			@UniqueConstraint(	
				name = "likes_uk",
				columnNames={"imageId","userId"}
			)
		}
	) // 한사람은 이미지하나를 한번만 좋아요 할 수 있음 유니크하게 해야함
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="imageId")
	private Image image;
	
	//이미지를 좋아요 눌렀는데 누가 좋아요 했는지 알아야 함
	@JsonIgnoreProperties({"images"}) // 유저는 필요한데 유저 안에 다시 likes는 필요없음
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}