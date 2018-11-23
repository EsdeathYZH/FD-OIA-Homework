package com.stormpractice.dao;

import com.stormpractice.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends JpaRepository<Record,String> {

}
