package com.key.dwsurvey.dao.impl;

import com.key.common.dao.BaseDaoImpl;
import com.key.dwsurvey.dao.QuChenColumnDao;
import com.key.dwsurvey.entity.QuChenColumn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * 矩陈列 dao
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */

@Primary
@Repository
public class QuChenColumnDaoImpl extends BaseDaoImpl<QuChenColumn, String> implements QuChenColumnDao {

}
