package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("mysqlMapper")
public interface QNA_mysql_Mapper {
//	CREATE TABLE IF NOT EXISTS customerqna(
//			article_id INT PRIMARY KEY AUTO_INCREMENT,
//			title VARCHAR(200) NOT NULL,
//			content TEXT NOT NULL,
//			username VARCHAR(20) NOT NULL,
//			password VARCHAR(64) NOT NULL,
//			views INT DEFAULT 0,
//			created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//			updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//			is_secure bit DEFAULT 0,
//			is_deleted bit DEFAULT 0
//		);
	
//	-- 익명 고객센터 문의게시판 테이블을 생성하는 쿼리문을 작성해주세요 
//	(테이블 이름 customerqna, 아래는 column 이름)
//	article_id, title, content, username, password
//	, views, created_at, updated_at, is_secure, is_deleted
	
//	-- 1. 글 작성
	@Insert({
		"INSERT INTO customerqna (title, content, username, password, is_secure)"
		, "VALUES (#{title}, #{content}, #{username}, #{password}, #{is_secure})"
	})
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyColumn = "article_id", keyProperty = "article_id"
	, resultType = Integer.class, before = false)
	int save(QNA qna);
	
	
//	-- 2. 전체 게시글 목록 조회
	@Select("SELECT article_id, title, content, username, views, is_secure FROM customerqna"
			+ " ORDER BY article_id DESC"
			+ " LIMIT #{pageSize} OFFSET #{limit}")
	@Results(id = "qnaList"
			, value = {
					@Result(column = "article_id", property = "article_id")
					, @Result(column = "title", property = "title")
					, @Result(column = "content", property = "content")
					, @Result(column = "username", property = "username")
					, @Result(column = "views", property = "views")
					, @Result(column = "is_secure", property = "is_secure")
			})
	List<QNA> findAll(int pageSize, int limit);
	
//	-- 3. 게시글 조회 시, is_secure 값이 false인 행만 조회
	@Select("SELECT article_id, title, content, username, views, is_secure FROM customerqna"
			+ " WHERE is_secure = 0"
			+ " ORDER BY article_id DESC"
			+ " LIMIT #{pageSize} OFFSET #{limit}")
	@ResultMap("qnaList")
	List<QNA> findBySecureIsFalse(int pageSize, int limit);
	
	// TODO 4 5 6 7 SQL 명령문 구현하시오
//	-- 4. 게시글 조회(id로 검색, title, content, username)
	@Select("SELECT title, content, username, views, is_secure FROM customerqna"
			+ " WHERE article_id = #{article_id}")
	QNA findByPk(Integer article_id);
	
//	-- 5. 게시글의 비밀 여부 조회 (is_secure)
	@Select("SELECT is_secure FROM customerqna"
			+ " WHERE article_id = #{article_id}")
	int findSecureByPk(int article_id);
	
//	-- 7. 글 논리 삭제(pk 및 password 일치) : is_delete => 1로 수정
	@Update("UPDATE customerqna SET is_deleted = '1' WHERE article_id = #{article_id}")
	int updateDelete();
	
	
//	pk로 모든 컬럼 조회
	@Select("SELECT * FROM customerqna WHERE article_id = #{article_id}")
	@Results(
			id = "qnaMapping"
			, value = {
					@Result(column = "article_id", property = "article_id", id = true)
					, @Result(column = "title", property = "title")
					, @Result(column = "content", property = "content")
					, @Result(column = "username", property = "username")
					, @Result(column = "password", property = "password")
					, @Result(column = "views", property = "views")
					, @Result(column = "created_at", property = "created_at")
					, @Result(column = "updated_at", property = "updated_at")
					, @Result(column = "is_secure", property = "is_secure")
					, @Result(column = "is_deleted", property = "is_deleted")
			}
	)
	QNA FindById(Integer article_id);
	
	// 조회수 증가
	@Update("UPDATE customerqna SET views = views + 1 WHERE article_id = #{article_id}")
	int updateCount(Integer article_id);
}
