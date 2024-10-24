package kr.co.greenart.web.customer.qna;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.greenart.web.util.QNA_NotFoundException;

@ControllerAdvice(assignableTypes = QNA_Controller.class)
public class QNA_ControllerAdvice {
	@ExceptionHandler(QNA_NotFoundException.class)
	public ModelAndView notFound(QNA_NotFoundException e) {
		// 조회 정보가 없을 경우 200으로 나오기 때문에 수정 필요
		ModelAndView mv = new ModelAndView("notFound");
		mv.setStatus(HttpStatusCode.valueOf(404));
		
		return mv;
	}
}
