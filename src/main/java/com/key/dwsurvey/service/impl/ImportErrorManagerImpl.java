package com.key.dwsurvey.service.impl;

import com.key.dwsurvey.dao.ImportErrorDao;
import com.key.dwsurvey.entity.ImportError;
import com.key.dwsurvey.mapper.ImportErrorMapper;
import com.key.dwsurvey.service.ImportErrorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 导入错误记录题
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Service
public class ImportErrorManagerImpl implements ImportErrorManager {
    @Autowired
    private ImportErrorDao importErrorDao;
    @Autowired
    private ImportErrorMapper importErrorMapper;
    @Transactional
    public void save(ImportError entity) {
        if (entity.getId() == null){
            importErrorMapper.insertSelective(entity);
        }else{
            importErrorMapper.updateByPrimaryKeySelective(entity);
        }
        //begin delete  by jesse at 2020-07-15  for 优化
        //importErrorDao.save(entity);
        //end delete by jesse at 2020-07-15

    }
    
}
