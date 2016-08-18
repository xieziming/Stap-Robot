package com.xieziming.stap;

import com.xieziming.stap.robot.CommentCreator;
import com.xieziming.stap.robot.RobotContext;
import com.xieziming.stap.robot.TestCaseCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class Robot {
    public static void main( String[] args ) {
        ApplicationContext ac= new AnnotationConfigApplicationContext(RobotContext.class);
        TestCaseCreator testCaseCreator = ac.getBean(TestCaseCreator.class);
        CommentCreator commentCreator = ac.getBean(CommentCreator.class);

        //testCaseCreator.deleteAll();

        for(int i=0; i<10; i++){
            testCaseCreator.create();
        }

        for(int i=0; i<200; i++){
            commentCreator.create();
        }
    }
}
