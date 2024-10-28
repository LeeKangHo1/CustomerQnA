package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.data.domain.Pageable;

// 요구사항 도출 / 검증
public interface QNA_Service {
	QNA findById(Integer article_id);
	
	// 1. 게시글 작성
	QNA writeQna(QNA qna);
	
	// 2. 전체 게시글 조회
	List<QNA> findAll(Pageable pageable);
	
	// 2. 조회 수 증가
	QNA updateViews(QNA qna);
	
	// 3. 비밀번호 조회
	String findPassById(Integer article_id);
	
	// 3 게시글 삭제
	int deleteQna(Integer article_id);
	
	// 3. 게시글 수정
	int updateArticle(QNA qna);

	// 4. 게시글 검색
	List<QNA> searchByTitleOrContent(String keyword, String searchType, Pageable sortedPageable);
	
	// 5. 관리자 기능(비밀 게시글 조회, 게시글 강제 삭제)
	
	// 7. 관리자 댓글 기능(댓글 작성, 댓글 수정/삭제, 답변이 완료되지 않은 게시글 목록 조회)
	
	
/*
1. 게시글 작성

필수 입력 항목: 제목, 내용, 유저이름, 비밀번호
비밀번호는 게시글 수정/삭제 시 필요

2. 게시글 조회

모든 사용자가 게시글 열람 가능 (비밀글은 비밀번호 일치시)
조회수 자동 증가
최신순/조회수순 정렬 가능
페이지당 20개 게시글 표시

3. 게시글 수정/삭제

작성 시 입력한 비밀번호로 인증
수정 이력 저장 -> ?
삭제 시 실제 삭제가 아닌 논리 삭제 처리

4. 검색 기능

검색 대상: 제목, 내용

5. 관리자 기능

비밀 게시글 조회
게시글 강제 삭제

6. 공유

게시글 공유 링크 생성
 */
/*

7. 관리자 댓글 기능
7.1 댓글 작성
7.2 댓글 수정/삭제
7.3 답변이 완료되지 않은 게시글 목록 조회

etc. 기타 사용자 편의 기능

첨부파일 업로드 가능 (이미지 파일만 허용, 파일당 최대 5MB)
자주 묻는 질문 / 답변
태그 추가, 검색
게시글 추천
메일 전송

 */
}