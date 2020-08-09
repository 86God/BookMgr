package com.company.test;

import com.company.dao.impl.BooksDaoImpl;
import com.company.utils.CosUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

public class CosUtilTest extends TestCase {
    public void test(){
        File file = new File("C:\\Users\\25849\\Pictures\\Saved Pictures\\QQ图片20200706095309.jpg");
        System.out.println(file);
        CosUtil.upLoad("img",file);
    }

}
