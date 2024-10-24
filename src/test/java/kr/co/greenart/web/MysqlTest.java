package kr.co.greenart.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_mysql_Mapper;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MysqlTest {
	@Autowired
	private QNA_mysql_Mapper mapper; 

//	@Test
//	@Order(1)
//	@Transactional
//	@Rollback
//	void testInsert() {
//		QNA qna = QNA.builder().title("제목").content("내용").username("유저네임").password("비밀번호").build();
//		int rows = mapper.save(qna);
//		
//		assertEquals(1, rows);
//		assertNotNull(qna.getArticle_id());
//	}

//	@Test
//	@Order(2)
//	@Transactional
//	@Rollback
//	void testSelect() {
//		List<QNA> all = mapper.findAll(10, 0);
//
//		assertNotEquals(1, all.size());
//	}
	
}