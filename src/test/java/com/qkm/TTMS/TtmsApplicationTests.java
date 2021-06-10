package com.qkm.TTMS;

import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class TtmsApplicationTests {


    @Autowired
    private MovieActorMapper mapper;


    @Autowired
    private MovieProducerMapper mapper1;

    @Autowired
    private MovieVideoMapper mapper2;
    @Autowired
    private MovieWriterMapper mapper3;

    @Test
    public void test1() throws Exception{
        InputStream file = new FileInputStream(new File("/home/qkm/excel/actor.xls"));
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        MovieActor movieActor = new MovieActor();
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
            movieActor.setMovieId(Integer.parseInt(string[0]));
            movieActor.setActorName(string[1]);
            movieActor.setActorPicture(string[2]);
            movieActor.setRoleName(string[3]);
            mapper.insert(movieActor);

        }


    }
    @Test
    public void test2() throws Exception{
        InputStream file = new FileInputStream(new File("/home/qkm/excel/writer.xls"));
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        MovieWriter movieWriter = new MovieWriter();
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
            movieWriter.setMovieId(Integer.parseInt(string[0]));
            movieWriter.setScreenwriterName(string[1]);
            movieWriter.setScreenwriterPicture(string[2]);
            mapper3.insert(movieWriter);

        }


    }

//    @Test
//    public void test2() throws Exception{
//
//        InputStream file = new FileInputStream(new File("/home/qkm/excel/result2.xls"));
//        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
//        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
//        String[] string = new String[10];
//        int i = 0;
//        for (Row row : sheet) {
//            //遍历行，获得每个单元格对象
//            for (Cell cell : row) {
//                //System.out.println(cell.getNumericCellValue());
//                if (cell.getCellType() == CellType.NUMERIC) {
//                    cell.setCellType(CellType.STRING);
//                }
////                System.out.println(cell.getStringCellValue());
//                string[i++] = cell.getStringCellValue();
//            }
//            i = 0;
//            movieMapper.updateByName(string[0],string[6],string[4],string[5]);
//        }
//
//
//    }
//
//    private Demm
//    @Test
//    public void insert(){
//
//    }


    @Autowired
    private  MovieUserMapper mapper4;

    @Autowired
    private  MovieUserRolesMapper mapper5;


    @Autowired
    private  MovieHallMapper mapper6;

    @Autowired
    private  CinemaMoviesMapper mapper7;

    @Test
    public void test3() throws Exception{
        InputStream file = new FileInputStream(new File("/home/qkm/excel/Book3.xlsx"));
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        CinemaMovies cinemaMovies = new CinemaMovies();
        String[] string = new String[20];
        int i = 0;

        for (Row row : sheet) {
            //遍历行，获得每个单元格对象
            for (Cell cell : row) {
                //System.out.println(cell.getNumericCellValue());
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    cell.setCellType(CellType.STRING);
                }
                if (cell != null) {
                    string[i++] = cell.getStringCellValue();
                }
            }

            i = 0;
            cinemaMovies.setCinemaId(Integer.parseInt(string[1]));
            cinemaMovies.setMovieId(Integer.parseInt(string[0]));
            mapper7.insert(cinemaMovies);
        }

    }

}
