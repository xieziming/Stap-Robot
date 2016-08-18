package com.xieziming.stap.robot;

import com.xieziming.stap.core.model.comment.dao.CommentDao;
import com.xieziming.stap.core.model.comment.pojo.Comment;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlan;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import com.xieziming.stap.core.model.user.dao.UserDao;
import com.xieziming.stap.core.model.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by Suny on 8/18/16.
 */
@Component
public class CommentCreator {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private CommentDao commentDao;

    public void create(){
        Comment comment = new Comment();
        int rand = new Random().nextInt(10);
        if(rand > 4 && rand < 7){
            List<Execution> executionList = executionDao.findAll();
            if(executionList.size() > 0) comment.setExecutionId(executionList.get(new Random().nextInt(executionList.size())).getId());
        }else if(rand >= 7){
            List<ExecutionPlan> executionPlanList = executionPlanDao.findAll();
            if(executionPlanList.size() > 0) comment.setExecutionPlanId(executionPlanList.get(new Random().nextInt(executionPlanList.size())).getId());
        }else {
            List<TestCase> testCaseList = testCaseDao.findAll();
            if(testCaseList.size() > 0) comment.setTestCaseId(testCaseList.get(new Random().nextInt(testCaseList.size())).getId());
        }

        comment.setContent("This comment is created by a robot, #NO."+UidCreator.getUid());
        List<User> userList = userDao.findAll();
        if(userList.size() > 0) {
            comment.setUserId(userList.get(new Random().nextInt(userList.size())).getId());
            if(comment.getExecutionId() != null || comment.getExecutionPlanId() != null || comment.getTestCaseId() != null) {
                commentDao.add(comment);
            }
        }
    }
}
