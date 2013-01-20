package com.rcr.main;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

public class DevEmbeddedTomcat {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        String rootDir = getProjectRootDir();

        tomcat.setBaseDir(rootDir+"Application\\target");
        try {
            tomcat.addWebapp(null, "/rcr", rootDir+"\\Web\\target\\web-1.0");
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    private static String getProjectRootDir() {
        final ProtectionDomain domain = DevEmbeddedTomcat.class.getProtectionDomain();
        final URL location = domain.getCodeSource().getLocation();
        return new File(location.getFile()).getParentFile().getParentFile().getParent();
    }
}

