package kr.co.greenart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_Mapper;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private QNA_Mapper mapper;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	// 어플 실행하면 바로 실행
	@Override
	public void run(String... args) throws Exception {
//		for (int i = 2; i < 7; i++) {
//			mapper.save(QNA.builder()
//							.title("title" + i)
//							.content("content" + i)
//							.username("userane" + i)
//							.password("password" + i)
//							.build());
//		}
	}

}
