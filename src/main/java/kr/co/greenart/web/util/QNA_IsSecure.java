package kr.co.greenart.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "비밀글입니다.")
public class QNA_IsSecure extends RuntimeException {
	private Integer article_id;
	
	public QNA_IsSecure(Integer article_id) {
		super();
		this.article_id = article_id;
	}
}
