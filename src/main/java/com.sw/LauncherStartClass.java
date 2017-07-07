package com.sw;


import com.sw.base.Launcher;
import com.sw.base.ServletContainer;

public class LauncherStartClass {

    public static void main(String[] args) throws Exception {
        ResourceModule module = new ResourceModule();
        ServletContainer server = Launcher.launch(module, false);
        server.start(true);
    }

}
