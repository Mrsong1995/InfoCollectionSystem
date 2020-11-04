package com.key.dwsurvey.dao.impl;

import com.key.common.dao.BaseDaoImpl;
import com.key.dwsurvey.dao.ImportErrorDao;
import com.key.dwsurvey.entity.ImportError;
import org.springframework.stereotype.Repository;

/**
 * 导入错误记录 dao
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */

@Repository
public class ImportErrorDaoImpl  extends BaseDaoImpl<ImportError, String> implements ImportErrorDao {

}
