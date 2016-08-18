package com.xieziming.stap.robot;

import com.xieziming.stap.core.model.testcase.dao.*;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import com.xieziming.stap.core.model.testcase.pojo.TestCaseMeta;
import com.xieziming.stap.core.model.testcase.pojo.TestData;
import com.xieziming.stap.core.model.testcase.pojo.TestStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Suny on 8/18/16.
 */
@Component
public class TestCaseCreator {
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private TestDataDefinitionDao testDataDefinitionDao;
    @Autowired
    private TestDataDefinitionCreator testDataDefinitionCreator;
    @Autowired
    private TestActionCreator testActionCreator;
    @Autowired
    private TestStepDao testStepDao;
    @Autowired
    private TestCaseMetaDao testCaseMetaDao;
    @Autowired
    private TestDataDao testDataDao;

    public TestCase create(){
        TestCase testCase = new TestCase();
        String testCaseName = "TestCase_"+UidCreator.getUid();
        testCase.setName(testCaseName);
        testCase.setDescription("Test case description");
        testCase.setStatus("Ready");
        testCaseDao.add(testCase);
        testCase = testCaseDao.findByName(testCaseName);

        for(int i=0; i<5;i++){
            TestCaseMeta testCaseMeta = new TestCaseMeta();
            testCaseMeta.setTestCaseId(testCase.getId());
            testCaseMeta.setMetaType("General");
            testCaseMeta.setMetaKey("MetaKey_"+UidCreator.getUid());
            testCaseMeta.setMetaValue("MetaValue_"+UidCreator.getUid());
            testCaseMetaDao.add(testCaseMeta);
        }

        for(int i=1; i<10; i++){
            TestStep testStep = new TestStep();
            testStep.setTestCaseId(testCase.getId());
            testStep.setStepOrder(i);
            testStep.setParameter("TestStepParameter_"+UidCreator.getUid());
            testStep.setTestActionId(testActionCreator.create().getId());
            testStepDao.add(testStep);
        }

        for(int i=1; i<15; i++) {
            TestData testData = new TestData();
            testData.setTestCaseId(testCase.getId());
            testData.setTestDataDefinitionId(testDataDefinitionCreator.create().getId());
            testDataDao.add(testData);
        }

        return testCase;
    }

    public void deleteAll(){
        testCaseDao.deleteAll();
    }
}
