package com.cos.costagram.utils;

import java.util.ArrayList;
import java.util.List;

import com.cos.costagram.domain.image.Image;
import com.cos.costagram.domain.tag.Tag;

public class TagUtils {

	//엔티티 아님 영속화 아님
	public static List<Tag> parsingToTagObject(String tags, Image imageEntity) {
		String temp[] = tags.split("#"); // #으로 짤라서 넣는다. 짜른 크기만큼 배열이 됨
		List<Tag> list = new ArrayList<>();

  		// 정현 : 파싱할 때 0번지에 공백이 데이터베이스에 들어가서 시작번지를 1번으로 한다.
		for (int i=1; i<temp.length; i++) {
			Tag tag = Tag.builder()
					.name(temp[i].trim())
					.image(imageEntity)
					.build();

			list.add(tag);
		}

		return list;
	}
}
