package com.xieziming.stap.robot;

import com.xieziming.stap.core.constants.ExecutionStatusType;
import com.xieziming.stap.core.model.execution.dao.ExecutionContextDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.execution.pojo.ExecutionContext;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlan;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by Suny on 8/18/16.
 */
@Component
public class ExecutionCreator {
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private ExecutionContextDao executionContextDao;
    @Autowired
    private ExecutionDao executionDao;

    public void create(){
        List<TestCase> testCaseList = testCaseDao.findAll();
        List<ExecutionPlan> executionPlanList = executionPlanDao.findAll();
        List<ExecutionContext> executionContextList = executionContextDao.findAll();
        Execution execution = new Execution();
        execution.setExecutionPlanId(executionPlanList.get(new Random().nextInt(executionContextList.size())).getId());
        execution.setTestCaseId(testCaseList.get(new Random().nextInt(testCaseList.size())).getId());
        execution.setStatus(ExecutionStatusType.READY);
        execution.setExecutionContextId(executionContextList.get(new Random().nextInt(executionContextList.size())).getId());

        executionDao.add(execution);
    }
}
