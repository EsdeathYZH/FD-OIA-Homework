/**
 * 
 */
package com.stormpractice.storm.bolt;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.stormpractice.entity.Record;
import com.stormpractice.service.RecordService;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.stormpractice.constant.Constants;
import com.stormpractice.util.GetSpringBean;

/**
 * @Title: InsertBolt
 * @Description: 
 * 写入数据的bolt
 * @Version:1.0.0  
 * @author stormpractice
 * @date 2018年4月19日
 */
public class InsertBolt extends BaseRichBolt{

	/**
	 *
	 */
	private static final long serialVersionUID = 6542256546124282695L;


	private static final Logger logger = LoggerFactory.getLogger(InsertBolt.class);


	private RecordService recordService;
		
		
	@SuppressWarnings("rawtypes")
	public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
		recordService=GetSpringBean.getBean(RecordService.class);
	}


	public void execute(Tuple tuple) {
		String msg=tuple.getStringByField(Constants.FIELD);
		try{
			List<Record> listRecord =JSON.parseArray(msg,Record.class);
			//移除age小于10的数据
			if(listRecord!=null&&listRecord.size()>0){
				Iterator<Record> iterator = listRecord.iterator();
				 while (iterator.hasNext()) {
					 Record record = iterator.next();
					 if (record.getNum()<3) {
						 logger.warn("Bolt移除的数据:{}",record);
						 iterator.remove();
					 }
				 }
				if(listRecord.size()>0){
					recordService.insertBatch(listRecord);
				}
			}
		}catch(Exception e){
			logger.error("Bolt的数据处理失败!数据:{}",msg,e);
		}
	}


	/**
	 * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
	 * Storm在终止一个bolt之前会调用这个方法。
	 */
	@Override
	public void cleanup() {
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {

	}
}
