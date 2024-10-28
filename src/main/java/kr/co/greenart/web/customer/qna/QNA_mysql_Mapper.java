package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper
@Qualifier("mysqlMapper")
public interface QNA_mysql_Mapper {
//	-- 1. 글 작성
	@Insert({
		"INSERT INTO customerqna (title, content, username, password, is_secure)"
		, "VALUES (#{title}, #{content}, #{username}, #{password}, #{is_secure})"
	})
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyColumn = "article_id", keyProperty = "article_id"
	, resultType = Integer.class, before = false)
	int save(QNA qna);
	
	// 정렬을 적용해서 전체 게시글 조회
	public class QnaSqlProvider {
	    public String findAllWithPagination(Pageable pageable) {
	        return new SQL() {{
	            SELECT("*");
	            FROM("customerqna");
	            WHERE("is_deleted = false");
	            if (pageable.getSort().isSorted()) {
	                for (Sort.Order order : pageable.getSort()) {
	                    ORDER_BY(order.getProperty() + " " + order.getDirection());
	                }
	            } else {
	                ORDER_BY("article_id DESC"); // 기본 정렬
	            }
	            // 페이지 크기와 오프셋 설정
	            LIMIT(pageable.getPageSize());
	            OFFSET((int) pageable.getOffset());
	        }}.toString();
	    }
	}
	
	@SelectProvider(type = QnaSqlProvider.class, method = "findAllWithPagination")
    @ResultMap("qnaMapping")
    List<QNA> findAllWithPagination(Pageable pageable);
	
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
	
	// 비밀번호 조회
	@Select("SELECT password FROM customerqna WHERE article_id = #{article_id}")
	String selectPassById(Integer article_id);
	
//	-- 7. 글 논리 삭제(pk 및 password 일치) : is_delete => true로 수정
	@Update("UPDATE customerqna SET is_deleted = true WHERE article_id = #{article_id}")
	int updateDelete(Integer article_id);
	
	// 글 수정
	@Update("UPDATE customerqna SET title = #{title}, content = #{content}, username = #{username}, password = #{password}"
			+ ", is_secure = #{is_secure}, updated_at = CURRENT_TIMESTAMP WHERE article_id = #{article_id};")
	int updateEdit(QNA qna);
}
