package com.stormpractice.web;

import java.util.List;

import com.stormpractice.entity.Record;
import com.stormpractice.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stormpractice.config.ApplicationConfiguration;
import com.stormpractice.kafka.KafkaProducerUtil;


/**
 * 
* Title: UserRestController
* Description: 
* 用户数据操作接口
 */
@RestController
@RequestMapping(value = "/api")
public class RecordController {
	@Autowired
    private RecordService recordService;
	@Autowired
	ApplicationConfiguration app;

    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public String sendRecord(@RequestParam("orderid") String orderid,
                            @RequestParam("username") String username,
                            @RequestParam("bookname") String bookname,
                            @RequestParam("num") int num){
        Record record = new Record();
        record.setOrderid(orderid);
        record.setBookname(bookname);
        record.setUsername(username);
        record.setNum(num);
        boolean result = KafkaProducerUtil.sendMessage(record.toString(), app.getServers(), app.getTopicName());
        return result ? "success":"failed";
    }

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public List<Record> findAllRecords(){
        return recordService.findAllRecords();
    }

    //    @GetMapping("/user")
//    public List<User> findByUser(User user) {
//    	System.out.println("开始查询...");
//        return userService.findByUser(user);
//    }

//    @PostMapping("/user")
//    public boolean sendKafka(@RequestBody User user) {
//        return KafkaProducerUtil.sendMessage(user.toString(), app.getServers(), app.getTopicName());
//    }
}
