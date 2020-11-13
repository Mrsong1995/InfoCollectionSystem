package com.key.dwsurvey.dao.impl;

import com.key.common.dao.BaseDaoImpl;
import com.key.dwsurvey.dao.AnAnswerDao;
import com.key.dwsurvey.entity.AnAnswer;
import com.key.dwsurvey.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * 答卷 dao

 */


@Repository
public class AnAnswerDaoImpl extends BaseDaoImpl<AnAnswer, String> implements AnAnswerDao {

	@Override
	public void findGroupStats(Question question) {
		String sql="select count(case when answer='' then answer end) emptyCount, count(case when answer!='' then answer end) blankCount from t_an_answer where visibility=1 and qu_id=?";
		
		Object[] objs=(Object[]) this.getSession().createSQLQuery(sql).setString(0, question.getId()).uniqueResult();
		
		question.setRowContent(objs[0].toString());//未回答数
		question.setOptionContent(objs[1].toString());//回答的项数
		question.setAnCount(Integer.parseInt(objs[1].toString()));
	}

}
