package com.key.dwsurvey.service;

import com.key.dwsurvey.entity.ImportError;

/**
 * 导入错误记录

 */
public interface ImportErrorManager {

    void save(ImportError importError);

}
