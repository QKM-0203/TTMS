package com.qkm.TTMS;

import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.MovieMapper;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

@SpringBootTest
class TtmsApplicationTests {


    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void test1() throws Exception{
        InputStream file = new FileInputStream(new File("/home/qkm/result1.xls"));
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        Movie movie = new Movie();
        movie.setMovieArea("中国大陆");
        movie.setMovieStatus(3);
        movie.setMovieBrief("空");
        movie.setMovieMinute(120);
        String[] string = new String[6];
        int i = 0;
        for (Row row : sheet) {
            //遍历行，获得每个单元格对象
            for (Cell cell : row) {
                //System.out.println(cell.getNumericCellValue());
                if (cell.getCellType() == CellType.NUMERIC) {
                    cell.setCellType(CellType.STRING);
                }
//                System.out.println(cell.getStringCellValue());
                  string[i++] = cell.getStringCellValue();
            }
            i = 0;
            movie.setMovieName(string[0]);
            movie.setMovieHead(string[4]);
            movie.setMovieScore(Double.parseDouble(string[2]));
            movie.setMovieType(string[3]);
            String string1 = string[1]+" 08:00:00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            movie.setMovieStart(sdf.parse(string1));
            movieMapper.insert(movie);
        }


    }

    @Test
    public void test2() throws Exception{

        InputStream file = new FileInputStream(new File("/home/qkm/excel/result2.xls"));
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        String[] string = new String[10];
        int i = 0;
        for (Row row : sheet) {
            //遍历行，获得每个单元格对象
            for (Cell cell : row) {
                //System.out.println(cell.getNumericCellValue());
                if (cell.getCellType() == CellType.NUMERIC) {
                    cell.setCellType(CellType.STRING);
                }
//                System.out.println(cell.getStringCellValue());
                string[i++] = cell.getStringCellValue();
            }
            i = 0;
            movieMapper.updateByName(string[0],string[6],string[4],string[5]);
        }


    }
//
//    private Demm
//    @Test
//    public void insert(){
//
//    }

}
