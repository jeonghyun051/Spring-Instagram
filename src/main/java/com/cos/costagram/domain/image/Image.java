package com.cos.costagram.domain.image;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.costagram.domain.comment.Comment;
import com.cos.costagram.domain.likes.Likes;
import com.cos.costagram.domain.tag.Tag;
import com.cos.costagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String caption; // 내용 ex) 나 오늘 너무 피곤했어!!
	
	private String postImageUrl;
	
	@JsonIgnoreProperties({"images"})
	@ManyToOne // 당연히 기본은 EAGER
	@JoinColumn(name="userId") // 데이터베이스 컬럼명에 이렇게 담김
	private User user; // 자식이 외래키의 주인, 한명의 유저는 여러개의 이미지를 만들 수 있음
	
	// 양방향 매핑 , 데베에 생기면 안됨
	
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image") // 외래키의 변수명을 적으면 된다. 기본 LAZY
	private List<Tag> tags; // * 컨틀롤러에서는 잭슨이 발동하지 않아서 갯터가 실행이안됨 무한반복이 안됨
	
	//라이크 정보도 가지고 있어야함, 그래야 버튼을 눌러서 빨간색이 뜨게 하고 유지할수있음
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image") // 외래키의 변수명을 적으면 된다. 기본 LAZY
	private List<Likes> likes; // A이미지에 홍길동, 장보고, 임꺽정 좋아요를 했고, 여기에 이름이 없으면 빨간색 하트가안뜨고
	
	// follow 정보
	// comment (댓글)
	@OrderBy("id DESC") // 정렬 javax
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Comment> comments;
	
	@CreationTimestamp // 자동으로 현재시간 담김
	private Timestamp createDate;
	
	@Transient // javax 꺼임, 칼럼이 만들어지지 않는다.
	private int likeCount;
	
	@Transient
	private boolean likeState;
	 
}
