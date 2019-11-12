package com.timeline.service;

import com.timeline.TimelineApplication;
import com.timeline.dataModel.MessageTrans;
import com.timeline.dataObject.Message;
import com.timeline.error.BussinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimelineApplication.class)
class MessageServiceTest {
    @Autowired
    MessageService messageService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void saveMessage() {
        MessageTrans messageTrans = new MessageTrans(null, null, null);
        assertThrows(BussinessException.class, () -> messageService.saveMessage(messageTrans));
        messageTrans.setSenderId(33);
        messageTrans.setReceiverId(-1);
        messageTrans.setContent("");
        assertThrows(BussinessException.class, () -> messageService.saveMessage(messageTrans));
        messageTrans.setTitle("");
        assertThrows(BussinessException.class, () -> messageService.saveMessage(messageTrans));
        messageTrans.setContent("content");
        messageTrans.setTitle("title");
        try {
            Message message=messageService.saveMessage(messageTrans);
            Assertions.assertEquals(33,message.getSenderId().intValue());
        } catch (BussinessException e) {
            logger.error(e.getErrorMessage());
        }
    }

    @Test
    void getAllMessage() {
        List<Message> messages = messageService.getMessages(1);
        for (Message m :
                messages) {
            System.out.println(m.getContent());
        }
    }
}