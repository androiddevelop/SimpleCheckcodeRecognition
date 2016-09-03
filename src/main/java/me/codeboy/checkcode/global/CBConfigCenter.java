package me.codeboy.checkcode.global;

/**
 * config center
 * Created by yuedong.li on 9/3/16.
 */
public class CBConfigCenter {
    public final static String PROJECT_DIR = System.getProperty("user.dir") + "/src/main/resources";
    public final static String TRAIN_DIR = PROJECT_DIR + "/train";
    public final static String TRAIN_DATA_SET = PROJECT_DIR + "/convert/train_dataset";
    public final static String TEST_DIR = PROJECT_DIR + "/test";
    public final static String MODEL_DIR = PROJECT_DIR + "/model";
    public final static String MODEL_PATH = MODEL_DIR + "/checkcode_model";
}
