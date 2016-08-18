package com.xieziming.stap.robot;

import com.xieziming.stap.core.model.testcase.dao.TestActionDao;
import com.xieziming.stap.core.model.testcase.pojo.TestAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 8/18/16.
 */
@Component
public class TestActionCreator {
    @Autowired
    private TestActionDao testActionDao;

    public TestAction create(){
        TestAction testAction = new TestAction();
        String name = "TestAction_"+UidCreator.getUid();
        testAction.setName(name);
        testAction.setHandler("TestActionHandler_"+UidCreator.getUid());
        testAction.setRemark("This is a test action created by robot");
        testActionDao.add(testAction);
        return testActionDao.findByName(name);
    }
}
