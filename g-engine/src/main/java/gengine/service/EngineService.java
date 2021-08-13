package gengine.service;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EngineService {


    public Object getScriptLists() {
        ApplicationHome home = new ApplicationHome(EngineService.class);
        File source = home.getSource();
        String parentDb = source.getParent();

        return null;
    }

    public Object getSelfDefineScripts() {

        return null;


    }

    public Object getPredefineScripts() {
        return null;

    }
}
