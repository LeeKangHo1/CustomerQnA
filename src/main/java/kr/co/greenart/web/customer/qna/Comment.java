package kr.co.greenart.web.customer.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
//	pk, article_id, comment
	private int pk;
	private int article_id;
	private String comment;
}
