package com.sw;


import com.sw.base.Launcher;
import com.sw.base.ServletContainer;

public class LauncherStartClass {

    public static void main(String[] args) throws Exception {
        ServletContainer server = Launcher.launch(new ResourceModule(), false);
        server.start(true);
    }

}
