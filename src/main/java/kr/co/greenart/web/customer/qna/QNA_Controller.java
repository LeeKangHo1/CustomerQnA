package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	@Autowired
	private QNA_Service service;
	
	@GetMapping({"/qna", "/qna/"})
	public String qna(@PageableDefault(size = 10) Pageable page, Model model) {
		List<QNA> qnaList = service.findAll();
		
		model.addAttribute("qnaList", qnaList);
		
		log.info("size=" + page.getPageSize());
		log.info("page=" + page.getPageNumber());
		
		log.info("offset: " + page.getOffset());
		
		log.info("sort: " + page.getSort());
		
		// Pageable: 정렬을 위해서 getmapping을 할 때 파라미터를 자동으로 설정? 해주는 객체 
//		http://localhost:8080/qna?page=3&size=10&sort=views -> ASC
		// sort=views,desc -> views: DESC로 만들어 준다.
//		http://localhost:8080/qna?page=3&size=10&sort=views,id
		// views: ASC, id: ASC
		
		return "qna";
	}
	
	@GetMapping("/qna/{article_id}")
	public String readArticle(@PathVariable Integer article_id, Model model) {
		QNA qna = service.findById(article_id);
		
		if (qna.getIs_secure()) {
			return "checkPassword";
		} else {
			model.addAttribute("qna", qna);
			return "article";
		}
	}
	
	@PostMapping("/qna/{article_id}")
    public String verifyPassword(@PathVariable Integer article_id, 
                                 @RequestParam String password, 
                                 Model model) {
		QNA qna = service.findById(article_id);
       	String originalPassword = qna.getPassword();   
       	
        if (originalPassword.equals(password)) {
        	model.addAttribute("qna", qna);
            return "article"; 
        } else {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "checkPassword"; 
        }
    }

	
	@GetMapping("/qna/form")
	public String toForm() {
		return "qnaForm";
	}

	@PostMapping("/qna/form")
	public String submitForm(@RequestParam String title, @RequestParam String content,
			@RequestParam String username, @RequestParam String password
			, @RequestParam(required = false, defaultValue = "false") Boolean is_secure) {
		
		QNA qna = QNA.builder().title(title)
				.content(content)
				.username(username)
				.password(password)
				.is_secure(is_secure).build();
		QNA qnaPost = service.writeQna(qna);
		
		return "redirect:/qna";
	}
}
