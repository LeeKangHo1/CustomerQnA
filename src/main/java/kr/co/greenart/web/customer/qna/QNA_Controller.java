package kr.co.greenart.web.customer.qna;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@Slf4j
public class QNA_Controller {
	@Autowired
	private QNA_Service service;
	
	@GetMapping("/qna")
	public String searchQnaList(
			@PageableDefault(size = 20, page = 0, sort = "article_id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "article_id") String sortBy,
			@RequestParam(defaultValue = "searchTitle") String searchType,
			@RequestParam(defaultValue = "") String keyword, Model model) {

		// 선택한 정렬 방식 적용
		Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(Sort.Order.desc(sortBy)));

		List<QNA> searchResults = service.searchByTitleOrContent(searchType, keyword, sortedPageable);

		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);

		model.addAttribute("qnaList", searchResults);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("currentSort", sortBy);
		return "qna2";
	}
	
//	@PostMapping("/qna")
//	public String searchQna(
//			@PageableDefault(size = 20, page = 0, sort = "article_id", direction = Sort.Direction.DESC) Pageable pageable,
//			@RequestParam(required = false, defaultValue = "article_id") String sortBy,
//			@RequestParam(defaultValue = "searchTitle") String searchType,
//			@RequestParam(defaultValue = "") String keyword, Model model) {
//
//		// 선택한 정렬 방식 적용
//		Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//				Sort.by(Sort.Order.desc(sortBy)));
//
//		List<QNA> searchResults = service.searchByTitleOrContent(searchType, keyword, sortedPageable);
//
//		model.addAttribute("searchType", searchType);
//		model.addAttribute("keyword", keyword);
//
//		model.addAttribute("qnaList", searchResults);
//		model.addAttribute("currentPage", pageable.getPageNumber());
//		model.addAttribute("currentSort", sortBy);
//		return "qna2";
//	}
	
	@GetMapping("/qna/{article_id}")
	public String readArticle(@PathVariable Integer article_id, Model model) {
		QNA qna = service.findById(article_id);
		
		if (qna.getIs_secure()) {
			return "checkPassword";
		} else {
			service.updateViews(qna);
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
        	service.updateViews(qna);
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
	
	// 삭제 매핑
	@GetMapping("/qna/{article_id}/delete")
	public String deleteArticle(@PathVariable Integer article_id, Model model) {
	    model.addAttribute("actionType", "delete");
	    model.addAttribute("article_id", article_id);
	    return "checkPass";
	}
	
	@PostMapping("/qna/{article_id}/delete")
	public String deleteQna(@PathVariable Integer article_id, Model model, @RequestParam String password) {
		QNA qna = service.findById(article_id);
       	String originalPassword = qna.getPassword();   
       	
        if (originalPassword.equals(password)) {
        	int result = service.deleteQna(article_id);
            return "redirect:/qna"; 
        } else {
        	model.addAttribute("actionType", "delete");
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "checkPass"; 
        }
	}

	// 수정하기 매핑
	@GetMapping("/qna/{article_id}/edit")
	public String editArticle(@PathVariable Integer article_id, Model model) {
	    model.addAttribute("actionType", "edit");
	    model.addAttribute("article_id", article_id);
	    return "checkPass";
	}

	@PostMapping("/qna/{article_id}/edit")
	public String editQna(@PathVariable Integer article_id, Model model, @RequestParam String password) {
		QNA qna = service.findById(article_id);
       	String originalPassword = qna.getPassword();   
       	
        if (originalPassword.equals(password)) {
        	model.addAttribute("qna", qna);
            return "qnaFormForEdit"; 
        } else {
        	model.addAttribute("actionType", "edit");
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "checkPass"; 
        }
	}
	
	// 수정 form 제출하기
	@PostMapping("/qna/{article_id}/editForm")
	public String editArticle(@PathVariable Integer article_id, @RequestParam String title, @RequestParam String content,
			@RequestParam String username, @RequestParam String password
			, @RequestParam Boolean is_secure, Model model) {
		
		QNA qna = QNA.builder()
				.article_id(article_id)
				.title(title)
				.content(content)
				.username(username)
				.password(password)
				.is_secure(is_secure).build();
		
		int result = service.updateArticle(qna);
		QNA afterQna = service.findById(article_id);
		model.addAttribute("qna", afterQna);
		
		return "article";
	}
	
}
