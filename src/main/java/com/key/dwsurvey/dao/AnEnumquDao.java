package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnEnumqu;
import com.key.dwsurvey.entity.Question;

/**
 * 枚举题 interface
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */
public interface AnEnumquDao extends BaseDao<AnEnumqu, String> {

	void findGroupStats(Question question);

}
