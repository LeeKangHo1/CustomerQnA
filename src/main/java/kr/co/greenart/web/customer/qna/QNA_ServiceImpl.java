package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.greenart.web.util.QNA_IsSecure;
import kr.co.greenart.web.util.QNA_NotFoundException;

@Service
public class QNA_ServiceImpl implements QNA_Service {
//	@Autowired
//	private QNA_Mapper mapper;
	
	@Autowired
	private QNA_mysql_Mapper mapper;

	@Override
	@Transactional // 하나의 트랜잭션 에서 실행되도록 붙여줘야 한다.
	public QNA findById(Integer article_id) {
		QNA qna = mapper.FindById(article_id);
		
		if (qna == null) {
			throw new QNA_NotFoundException(article_id);
		}
		
		if (qna.getIs_secure()) {
			throw new QNA_IsSecure(article_id);
		}
		
		int rows = mapper.updateCount(article_id);
		if (rows == 1) {
			qna.setViews(qna.getViews() + 1);
		}
		
		return qna;
	}

	@Override
	public List<QNA> findAll() {
		List<QNA> qnaList = mapper.findAll(20, 0);
		return qnaList;
	}

	@Override
	public QNA writeQna(QNA qna) {
		int result = mapper.save(qna);
		return qna;
	}

}
