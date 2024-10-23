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

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_Mapper;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {
	@Autowired
	private QNA_Mapper mapper; // mapperscan 설정이 기본으로 되어 있다.

	@Test
	@Order(1) // 낮은 값이 priority(우선순위) 높다.
	void testInsert() {
		QNA qna = QNA.builder().title("제목").content("내용").username("유저네임").password("비밀번호").build();
		int rows = mapper.save(qna);
		
		// h2 db랑 mysql db랑 명령어가 달라서 error
		// 근데 우리는 mysql용 mapper가 필요한데? -> mysql용, h2용 구현체 2개를 만든다.
		assertEquals(1, rows);
		assertNotNull(qna.getArticle_id());
	}

	@Test
	@Order(2)
	void atestSelect() {
		// atestSelect() 로 하면 에러난다. junit은 메소드 순서가 정해져 있지 않다.
		// boot에서 junit.jupiter(더 최신 버전)은 알파벳 순으로 테스트를 실행
		// @TestMethodOrder(MethodOrderer.OrderAnnotation.class) 와 @Order(value = 1) 추가해서 순서 지정 가능
		// order 번호가 같으면 알파벳순으로 
		List<QNA> all = mapper.findAll(10, 0);

		assertNotEquals(0, all.size());
	}
	
}
