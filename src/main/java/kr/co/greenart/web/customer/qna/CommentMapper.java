package kr.co.greenart.web.customer.qna;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper {

	@Select("SELECT * FROM customerqnacomment WHERE article_id = #{article_id};")
	Comment findByArticleId(Integer article_id);

	@Insert("INSERT INTO customerqnacomment (article_id, comment) VALUES (#{article_id}, #{comment});")
	int insertComment(Comment comment);
	
	@Update("UPDATE customerqnacomment SET comment = #{comment} WHERE pk = #{article_id};")
	int updateComment(Comment comment);
}
