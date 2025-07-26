package com.home.task.unity;

import com.home.task.unity.utils.DatabaseUtils;
import org.testng.annotations.Test;

public class TestDB {

    @Test
    public void testDb() {
//        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        DatabaseUtils db = new DatabaseUtils();
        db.exeQuery();
    }
}
