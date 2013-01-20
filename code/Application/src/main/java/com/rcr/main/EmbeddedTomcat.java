package com.rcr.main;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

public class EmbeddedTomcat {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(2525);
        String rootDir = getRootDir();
        tomcat.setBaseDir(rootDir);
        try {
            tomcat.addWebapp(null, "/ap", rootDir+"\\webapps\\web-1.0.war");
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    private static String getRootDir() {
        final ProtectionDomain domain = EmbeddedTomcat.class.getProtectionDomain();
        final URL location = domain.getCodeSource().getLocation();
        return new File(location.getFile()).getParent();
    }
}
