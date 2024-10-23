package kr.co.greenart.web.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerTimeController {
	@GetMapping("/server/time")
	public String serverTime(Model model) {
		// 스프링 부트는 문제 발생 시 Whitelabel Error Page 가 나오고 에러 메세지랑 status 코드가 뜬다.
		
		model.addAttribute("serverTime", LocalDateTime.now());
		
		// view 이름(server-time).jsp 로 forward
		// application.properties 에서 설정한 위치에 jsp 파일로 있어야 한다.
		return "server-time";
	}
}
