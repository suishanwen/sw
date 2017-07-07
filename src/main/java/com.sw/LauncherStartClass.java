package com.sw;


import com.sw.base.Launcher;
import com.sw.base.ServletContainer;

public class LauncherStartClass {
    /**
     * Launcher版本有更新原来的执行方式不再有效，在IDE里面启动Launcher，请执行本类的main方法。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ResourceModule module = new ResourceModule();
        ServletContainer server = Launcher.launch(module, false);
        server.start(true);
    }

}
