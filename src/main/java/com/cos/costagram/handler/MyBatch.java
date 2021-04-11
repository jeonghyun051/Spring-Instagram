package com.cos.costagram.handler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyBatch {

	private final ExceptionList exceptionList;
	
	@Scheduled(fixedDelay = 1000*60*10) // Cron 정기적 실행
	public void excute() {
		List<String> exList = exceptionList.getExList();
		// DB에 insert 하기ㅏ
		exList.clear();
	}
}