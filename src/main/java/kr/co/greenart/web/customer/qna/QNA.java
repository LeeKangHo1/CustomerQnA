package kr.co.greenart.web.customer.qna;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QNA {
	// 필드가 많아서 생성자 보다 builder 사용 예정
	private Integer article_id;
	private String title; // NOT NULL
	private String content; // NOT NULL
	private String username; // NOT NULL
	private String password; // NOT NULL
	private Integer views; // view count, 조회수
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Boolean is_secure;
	private Boolean is_deleted;
}
