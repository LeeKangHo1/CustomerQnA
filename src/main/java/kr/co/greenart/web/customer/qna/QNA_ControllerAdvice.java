package kr.co.greenart.web.customer.qna;

import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.greenart.web.util.QNA_IsSecure;
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
	
//	@ExceptionHandler(QNA_IsSecure.class)
//    public String handleSecureArticle(QNA_IsSecure e, Model model) {
//        model.addAttribute("article_id", e.getArticle_id());
//        return "checkPassword"; // 비밀번호 입력 폼 페이지로 이동
//    }

}
