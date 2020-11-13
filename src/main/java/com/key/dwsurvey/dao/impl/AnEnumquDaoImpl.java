package com.key.dwsurvey.dao.impl;


import com.key.common.dao.BaseDaoImpl;
import com.key.dwsurvey.dao.AnEnumquDao;
import com.key.dwsurvey.entity.AnEnumqu;
import com.key.dwsurvey.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 枚举 dao

 */

@Repository
public class AnEnumquDaoImpl extends BaseDaoImpl<AnEnumqu, String> implements AnEnumquDao {

	@Override
	public void findGroupStats(Question question) {
		String sql="select answer,count(answer) from t_an_enumqu where  visibility=1 and  qu_id=? GROUP BY answer";
		List<Object[]> list=this.getSession().createSQLQuery(sql).setString(0, question.getId()).list();
		
		//一共有多少对枚举数
		if(list!=null && list.size()>0){
			question.setAnCount(list.size());
		}
	}
	
}
