package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.greenart.web.util.QNA_NotFoundException;

@Service
public class QNA_ServiceImpl implements QNA_Service {
	@Autowired
	private QNA_mysql_Mapper mapper;

	@Override
	@Transactional
	public QNA findById(Integer article_id) {
		QNA qna = mapper.FindById(article_id);
		
		if (qna == null) {
			throw new QNA_NotFoundException(article_id);
		}
		return qna;
	}

	@Override
	public List<QNA> findAll(Pageable pageable) {
		List<QNA> qnaList = mapper.findAllWithPagination(pageable);
		return qnaList;
	}

	@Override
	public QNA writeQna(QNA qna) {
		int result = mapper.save(qna);
		return qna;
	}

	@Override
	public String findPassById(Integer article_id) {
		return mapper.selectPassById(article_id);
	}

	@Override
	public QNA updateViews(QNA qna) {
		int rows = mapper.updateCount(qna.getArticle_id());
		if (rows == 1) {
			qna.setViews(qna.getViews() + 1);
		}
		return qna;
	}

	@Override
	public int deleteQna(Integer article_id) {
		int rows = mapper.updateDelete(article_id);
		return rows;
	}

	@Override
	public int updateArticle(QNA qna) {
		int result = mapper.updateEdit(qna);
		return result;
	}
	
	// 검색 메서드
    public List<QNA> searchByTitleOrContent(String searchType, String keyword, Pageable sortedPageable) {
        return mapper.searchByTitleOrContent(searchType, keyword, sortedPageable);
    }

}
