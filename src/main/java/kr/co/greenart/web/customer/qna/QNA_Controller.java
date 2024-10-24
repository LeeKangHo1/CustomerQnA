package kr.co.greenart.web.customer.qna;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QNA_Controller {
	@Autowired
	private QNA_Service service;
	
	@GetMapping("/qna")
	public String qna(Model model) {
		List<QNA> qnaList = service.findAll();
		
		model.addAttribute("qnaList", qnaList);
		
		return "qna";
	}
	
	@GetMapping("/qna/{article_id}")
	public String readArticle(@PathVariable Integer article_id, Model model) {
		QNA qna = service.findById(article_id);
		
		model.addAttribute("qna", qna);
		
		return "article";
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
