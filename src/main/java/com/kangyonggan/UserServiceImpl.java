package com.kangyonggan;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author kangyonggan
 * @since 9/28/17
 */
public class UserServiceImpl {

    public void updateUserAge(String username, int age) {
        System.out.println("==========>> update user set age = ? where username = ?");
        System.out.println("==========>> " + age + ", " + username);
    }

    @LogTime
    public void updateUserPassword(String username, String password) {
        System.out.println("==========>> update user set password = ? where username = ?");
        System.out.println("==========>> " + password + ", " + username);
    }

    /**
     * 测试入口, 在方法updateUserPassword前加一行代码
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.kangyonggan.UserServiceImpl");
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            LogTime logTime = (LogTime) ctMethod.getAnnotation(LogTime.class);
            if (logTime != null) {
                System.out.println("在方法" + ctMethod.getName() + "上发现注解@LogTime, 需要对其前置增强");

//                ctMethod.setBody("{System.out.println(\"xxx\");}");
                ctMethod.insertBefore("{System.out.println(\"这是前置增强的代码，哈哈哈哈\");}");

                System.out.println("已经增强！！！");

                //把生成的class文件写入文件
                byte[] byteArr = ctClass.toBytecode();
                FileOutputStream fos = new FileOutputStream(new File("/Users/kyg/code/kyg/javassist/target/classes/com/kangyonggan/UserServiceImpl.class"));
                fos.write(byteArr);
                fos.flush();
                fos.close();
            }
        }

        new UserServiceImpl().updateUserAge("admin", 22);
        new UserServiceImpl().updateUserPassword("admin", "123456");
    }

}
