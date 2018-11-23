package com.stormpractice.service;

import com.stormpractice.dao.RecordDao;
import com.stormpractice.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YZH
 * @date 2018/11/23 11:14
 */
@Service
public class RecordService {
    @Autowired
    private RecordDao recordDao;

    /**
     * 批量新增记录
     * @param records
     * @return
     */
    public void insertBatch(List<Record> records){
        recordDao.save(records);
    }

    public List<Record> findAllRecords(){
        return recordDao.findAll();
    }
}
