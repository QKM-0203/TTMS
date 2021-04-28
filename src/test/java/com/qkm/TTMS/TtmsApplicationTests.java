package com.qkm.TTMS;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qkm.TTMS.mapper.UserMapper;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
class TtmsApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(userMapper.getByaccounts("123"));

    }
    @Test
    public void test1() throws Exception{
        InputStream file = new FileInputStream(new File("/home/qikaimeng/1.xlsx"));
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            //遍历行，获得每个单元格对象
            for (Cell cell : row) {
                //System.out.println(cell.getNumericCellValue());
                if (cell.getCellType() == CellType.NUMERIC) {
                    cell.setCellType(CellType.STRING);
                }
                System.out.println(cell.getStringCellValue());
            }
        }

    }



}
