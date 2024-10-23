package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class QNA_Controller {
	// Controller: 사용자가 view를 조작했을 때 컨트롤러에게 명령이 내려진다.
	// 명령 받은 컨트롤러는 model을 조작하는 명령어를 수행.
	// 수행 후 view의 형태로 사용자에게 제공

	@Autowired
	private QNA_mysql_Mapper mapper;

	@GetMapping("/qna")
	public String qna(Model model) {
		List<QNA> qnaList = mapper.findAll(10, 0);

		log.info(qnaList.toString());
		model.addAttribute("qnaList", qnaList);

		return "qna";
	}
	
	@GetMapping("/qna/{article_id}")
	public String showDetail(@PathVariable("article_id") Integer article_id, Model model) {
	    if (article_id == null) {
	        throw new IllegalArgumentException("article_id is required and cannot be null");
	    }
	    QNA qna = mapper.findByPk(article_id);
	    
	    model.addAttribute("qna", qna);
	    if (qna.getIs_secure() == false) {
	    	return "qnaDetail";
	    } else {
	    	return "checkPass";
	    }
	}
	
	// 비밀번호 확인 미구현
//	@PostMapping("/qna/{article_id}")
//	public String checkPass(@PathVariable("article_id") Integer article_id, @RequestParam String password) {
//		QNA qna = mapper.findByPk(article_id);
//		log.info("qna 정보 확인: " + qna.toString());
//		if (qna.getPassword().equals(password)) {
//			return "qnaDetail";
//		} else {
//			return "checkPass";
//		}
//	}
	

	@GetMapping("/qna/form")
	public String toForm() {
		return "qnaForm";
	}

	@PostMapping("/qna/form")
	public String submitForm(@RequestParam String title, @RequestParam String content,
			@RequestParam String username, @RequestParam String password
			, @RequestParam Integer is_secure) {
		
		log.info("비밀글 여부: " + is_secure);
		if (is_secure == 1) {
			QNA qna = QNA.builder().title(title)
					.content(content)
					.username(username)
					.password(password)
					.is_secure(true).build();
			int result = mapper.save(qna);
		} else {
			QNA qna = QNA.builder().title(title)
					.content(content)
					.username(username)
					.password(password)
					.is_secure(false).build();
			int result = mapper.save(qna);
		}
		return "redirect:/qna";
	}

}
