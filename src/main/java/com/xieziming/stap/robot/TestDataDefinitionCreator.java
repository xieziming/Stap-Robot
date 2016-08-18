package com.xieziming.stap.robot;

import com.xieziming.stap.core.constants.TestDataType;
import com.xieziming.stap.core.model.testcase.dao.TestDataDefinitionDao;
import com.xieziming.stap.core.model.testcase.pojo.TestDataDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Suny on 8/18/16.
 */
@Component
public class TestDataDefinitionCreator {
    @Autowired
    private TestDataDefinitionDao testDataDefinitionDao;

    public TestDataDefinition create(){
        TestDataDefinition testDataDefinition = new TestDataDefinition();
        String field = "TestDataDefinition_"+UidCreator.getUid();
        testDataDefinition.setType(new Random().nextInt() > 6 ? TestDataType.GLOBAL : TestDataType.LOCAL);
        testDataDefinition.setField(field);
        testDataDefinition.setValue("TestDataDefinitionValue_"+UidCreator.getUid());
        testDataDefinition.setRemark("This is a test data definition created by robot");
        testDataDefinitionDao.add(testDataDefinition);

        return testDataDefinitionDao.findByField(field);
    }
}
