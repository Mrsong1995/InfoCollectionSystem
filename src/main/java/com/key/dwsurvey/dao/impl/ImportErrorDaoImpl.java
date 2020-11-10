package com.key.dwsurvey.dao.impl;

import com.key.common.dao.BaseDaoImpl;
import com.key.dwsurvey.dao.ImportErrorDao;
import com.key.dwsurvey.entity.ImportError;
import org.springframework.stereotype.Repository;

/**
 * 导入错误记录 dao

 */

@Repository
public class ImportErrorDaoImpl  extends BaseDaoImpl<ImportError, String> implements ImportErrorDao {

}
