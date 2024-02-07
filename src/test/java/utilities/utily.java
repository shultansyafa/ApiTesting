package utilities;

import test.ApiTesting;

import java.io.File;

public class utily {

    public static File getApiTestingFile(String ApiTesting){
        return new File("C:\\Users\\shult\\apiTestingAutomation\\src\\test\\java\\test\\ApiTesting.java" + ApiTesting);
    }
}
