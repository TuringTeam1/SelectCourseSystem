 package com;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.apache.poi.hssf.usermodel.HSSFCell; 
import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.junit.Test;

import cn.edu.gdin.entity.Student;

public class TestExcel { 
      //��¼��������Ϣ 
      static Log log = LogFactory.getLog(TestExcel.class);  
      //��ȡExcel�ĵ���·�� 
      public static void ImportMethod(String filePath) { 
            try { 
                  // ������Excel�������ļ������� 
                  HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath));  
                  // ��Excel�ĵ��У���һ�Ź������ȱʡ������0
                  // �����Ϊ��HSSFSheet sheet = workbook.getSheetAt(0); 
                  HSSFSheet sheet = wookbook.getSheet("Sheet1"); 
                  //��ȡ��Excel�ļ��е��������� 
                  int rows = sheet.getPhysicalNumberOfRows();
                  //������ 
                  for (int i = 0; i < rows; i++) { 
                        // ��ȡ���϶˵�Ԫ�� 
                        HSSFRow row = sheet.getRow(i); 
                        // �в�Ϊ�� 
                        if (row != null) { 
                              //��ȡ��Excel�ļ��е����е��� 
                              int cells = row.getPhysicalNumberOfCells(); 
                              String value = "";      
                              //������ 
                              for (int j = 0; j < cells; j++) { 
                                    //��ȡ���е�ֵ 
                                    HSSFCell cell = row.getCell((short) j); 
                                    if (cell != null) { 
                                          switch (cell.getCellType()) { 
                                                case HSSFCell.CELL_TYPE_FORMULA: 
                                                break; 
                                                case HSSFCell.CELL_TYPE_NUMERIC: 
                                                      value += cell.getNumericCellValue() + ",";         
                                                break;   
                                                case HSSFCell.CELL_TYPE_STRING: 
                                                      value += cell.getStringCellValue() + ","; 
                                                break; 
                                                default: 
                                                      value += "0"; 
                                                break; 
                                    } 
                              }      
                        } 
                        // �����ݲ��뵽mysql���ݿ��� 
                        String[] val = value.split(","); 
                        Student entity = new Student(); 
                        entity.setStunum(val[0]);
                        entity.setStuname(val[1]);
                        entity.setStusex(val[2]);
                        entity.setStubirth(val[3]);
                        entity.setStuadim(val[4]);
                        entity.setStumajor(val[5]);
                        entity.setStuclass(val[6]);
                        entity.setStucollege(val[7]);
                       System.out.println(entity.toString());
                       // TestMethod method = new TestMethod(); 
                        //method.Add(entity); 
                  } 
             } 
      } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
      } catch (IOException e) { 
            e.printStackTrace(); 
      } 
   } 
      @Test
      public void test(){
    	  TestExcel.ImportMethod("D://student.xls");
      }
} 