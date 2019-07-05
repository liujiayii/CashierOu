package com.cashier.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @ClassName: ExcelUtil

 * @description 文件导入导出的工具类
 *
 * @author  pangchong
 * @createDate 2018年12月13日
 */

public class ExcelUtil {

	  public static String NO_DEFINE = "no_define";//未定义的字段
	    public static String DEFAULT_DATE_PATTERN="yyyy-MM-dd";//默认日期格式
	    public static int DEFAULT_COLOUMN_WIDTH = 17;
	    
	    private final static String excel2003L =".xls";    //2003- 版本的excel  
	    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
	    /** 
	     * Excel导入
	     */  
	    public static  List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception{  
	        List<List<Object>> list = null;  
	        //创建Excel工作薄  
	        Workbook work = getWorkbook(in,fileName);  
	        if(null == work){  
	            throw new Exception("创建Excel工作薄为空！");  
	        }  
	        Sheet sheet = null;  
	        Row row = null;  
	        Cell cell = null;  
	        list = new ArrayList<List<Object>>();  
	        //遍历Excel中所有的sheet  
	        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
	            sheet = work.getSheetAt(i);  
	            if(sheet==null){continue;}  
	            //遍历当前sheet中的所有行  
	            //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部  
	            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {  
	                //读取一行  
	                row = sheet.getRow(j);  
	                //去掉空行和表头  
	                if(row==null||row.getFirstCellNum()==j){continue;}  
	                //遍历所有的列  
	                List<Object> li = new ArrayList<Object>();  
	                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
	                    cell = row.getCell(y);  
	                    li.add(getCellValue(cell));  
	                }  
	                list.add(li);  
	            }  
	        }  
	        return list;  
	    }  
	    /** 
	     * 描述：根据文件后缀，自适应上传文件的版本 
	     */  
	    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
	        Workbook wb = null;  
	        String fileType = fileName.substring(fileName.lastIndexOf("."));  
	        if(excel2003L.equals(fileType)){  
	            wb = new HSSFWorkbook(inStr);  //2003-  
	        }else if(excel2007U.equals(fileType)){  
	            wb = new XSSFWorkbook(inStr);  //2007+  
	        }else{  
	            throw new Exception("解析的文件格式有误！");  
	        }  
	        return wb;  
	    }  
	    /** 
	     * 描述：对表格中数值进行格式化 
	     */  
	    public static  Object getCellValue(Cell cell){  
	        Object value = null;  
	        DecimalFormat df = new DecimalFormat("0");  //格式化字符类型的数字  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
	        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
	        switch (cell.getCellType()) {  
	            case Cell.CELL_TYPE_STRING:  
	                value = cell.getRichStringCellValue().getString();  
	                break;  
	            case Cell.CELL_TYPE_NUMERIC:  
	                if("General".equals(cell.getCellStyle().getDataFormatString())){  
	                    value = df.format(cell.getNumericCellValue());  
	                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
	                    value = sdf.format(cell.getDateCellValue());  
	                }else{  
	                    value = df2.format(cell.getNumericCellValue());  
	                }  
	                break;  
	            case Cell.CELL_TYPE_BOOLEAN:  
	                value = cell.getBooleanCellValue();  
	                break;  
	            case Cell.CELL_TYPE_BLANK:  
	                value = "";  
	                break;  
	            default:  
	                break;  
	        }  
	        return value;  
	    }  
	    
	    /**
	     * 导出Excel 97(.xls)格式 ，少量数据
	     * @param title 标题行 
	     * @param headMap 属性-列名
	     * @param jsonArray 数据集
	     * @param datePattern 日期格式，null则用默认日期格式
	     * @param colWidth 列宽 默认 至少17个字节
	     * @param out 输出流
	     */
		public static void exportExcel(Map<String, String> headMap,JSONArray jsonArray,String datePattern,int colWidth, OutputStream out) {
	        if(datePattern==null) datePattern = DEFAULT_DATE_PATTERN;
	        // 声明一个工作薄
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        workbook.createInformationProperties();
	        workbook.getDocumentSummaryInformation().setCompany("*****公司");
	        
	         //表头样式
	        HSSFCellStyle titleStyle = workbook.createCellStyle();
	        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        HSSFFont titleFont = workbook.createFont();
	        titleFont.setFontHeightInPoints((short) 20);
	        titleFont.setBoldweight((short) 700);
	        titleStyle.setFont(titleFont);
	        
	        // 列头样式
	        HSSFCellStyle headerStyle = workbook.createCellStyle();
	        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        HSSFFont headerFont = workbook.createFont();
	        headerFont.setFontHeightInPoints((short) 12);
	        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        headerStyle.setFont(headerFont);
	        
	        // 单元格样式
	        HSSFCellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont cellFont = workbook.createFont();
	        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	        cellStyle.setFont(cellFont);
	        
	        // 生成一个(带标题)表格
	        HSSFSheet sheet = workbook.createSheet();
	        // 声明一个画图的顶级管理器
	        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	        // 定义注释的大小和位置,详见文档
	        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
	                0, 0, 0, (short) 4, 2, (short) 6, 5));
	        // 设置注释内容
	        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
	        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
	        comment.setAuthor("JACK");
	        //设置列宽
	        int minBytes = colWidth<DEFAULT_COLOUMN_WIDTH?DEFAULT_COLOUMN_WIDTH:colWidth;//至少字节数
	        int[] arrColWidth = new int[headMap.size()];
	        // 产生表格标题行,以及设置列宽
	        String[] properties = new String[headMap.size()];
	        String[] headers = new String[headMap.size()];
	        int ii = 0;
	        for (Iterator<String> iter = headMap.keySet().iterator(); iter
	                .hasNext();) {
	            String fieldName = iter.next();
	 
	            properties[ii] = fieldName;
	            headers[ii] = fieldName;
	 
	            int bytes = fieldName.getBytes().length;
	            arrColWidth[ii] =  bytes < minBytes ? minBytes : bytes;
	            sheet.setColumnWidth(ii,arrColWidth[ii]*256);
	            ii++;
	        }
	        // 遍历集合数据，产生数据行
	        int rowIndex = 0;
	        for (Object obj : jsonArray) {
	            if(rowIndex == 65535 || rowIndex == 0){
	                if ( rowIndex != 0 ) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示
	 
	                HSSFRow headerRow = sheet.createRow(0); //列头 rowIndex =0
	                for(int i=0;i<headers.length;i++)
	                {
	                    headerRow.createCell(i).setCellValue(headers[i]);
	                    headerRow.getCell(i).setCellStyle(headerStyle);
	 
	                }
	                rowIndex = 1;//数据内容从 rowIndex=1开始
	            }
	            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
	            HSSFRow dataRow = sheet.createRow(rowIndex);
	            for (int i = 0; i < properties.length; i++)
	            {
	                HSSFCell newCell = dataRow.createCell(i);
	 
	                Object o =  jo.get(properties[i]);
	                String cellValue = ""; 
	                if(o==null) cellValue = "";
	                else if(o instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(o);
	                else cellValue = o.toString();
	 
	                newCell.setCellValue(cellValue);
	                newCell.setCellStyle(cellStyle);
	            }
	            rowIndex++;
	        }
	        // 自动调整宽度
	        /*for (int i = 0; i < headers.length; i++) {
	            sheet.autoSizeColumn(i);
	        }*/
	        try {
	            workbook.write(out);
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
	    /**
	     * 导出Excel 2007 OOXML (.xlsx)格式
	     * @param title 标题行
	     * @param headMap 属性-列头
	     * @param jsonArray 数据集
	     * @param datePattern 日期格式，传null值则默认 年月日
	     * @param colWidth 列宽 默认 至少17个字节
	     * @param out 输出流
	     */
	    public static void exportExcelX(Map<String, String> headMap,JSONArray jsonArray,String datePattern,int colWidth, OutputStream out) {
	        if(datePattern==null) datePattern = DEFAULT_DATE_PATTERN;
	        // 声明一个工作薄
	        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);//缓存
	        workbook.setCompressTempFiles(true);
	        
	         //表头样式
	        CellStyle titleStyle = workbook.createCellStyle();
	        titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
	        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
	        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        Font titleFont = workbook.createFont();
	        titleFont.setFontHeightInPoints((short) 30);
	        titleFont.setBoldweight((short) 1000);
	        titleStyle.setFont(titleFont);
	     
	        // 列头样式
	        CellStyle headerStyle = workbook.createCellStyle();
	        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        Font headerFont = workbook.createFont();
	        headerFont.setFontHeightInPoints((short) 12);
	        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        headerStyle.setFont(headerFont);
	        
	        // 单元格样式
	        CellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        Font cellFont = workbook.createFont();
	        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	        cellStyle.setFont(cellFont);
	        
	        // 生成一个(带标题)表格
	        SXSSFSheet sheet = workbook.createSheet();
	        // 自动调整列宽
	        sheet.trackAllColumnsForAutoSizing();
	  
	        //设置列宽
	        int minBytes = colWidth<DEFAULT_COLOUMN_WIDTH?DEFAULT_COLOUMN_WIDTH:colWidth;//至少字节数
	        int[] arrColWidth = new int[headMap.size()];
	        // 产生表格标题行,以及设置列宽
	        String[] properties = new String[headMap.size()];
	        String[] headers = new String[headMap.size()];
	        int ii = 0;
	        for (Iterator<String> iter = headMap.keySet().iterator(); iter
	                .hasNext();) {
	            String fieldName = iter.next();
	 
	            properties[ii] = fieldName;
	            headers[ii] = headMap.get(fieldName);
	 
	            int bytes = fieldName.getBytes().length;
	            arrColWidth[ii] =  bytes < minBytes ? minBytes : bytes;
	            sheet.setColumnWidth(ii,arrColWidth[ii]*400);
	            ii++;
	        }
	        // 遍历集合数据，产生数据行
	        int rowIndex = 0;
	        for (Object obj : jsonArray) {
	            if(rowIndex == 65535 || rowIndex == 0){
	                if ( rowIndex != 0 ) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示
	 
	                SXSSFRow headerRow = sheet.createRow(0); //列头 rowIndex =0
	                for(int i=0;i<headers.length;i++)
	                {
	                    headerRow.createCell(i).setCellValue(headers[i]);
	                    headerRow.getCell(i).setCellStyle(headerStyle);
	 
	                }
	                rowIndex = 1;//数据内容从 rowIndex=1开始
	            }
	            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
	            SXSSFRow dataRow = sheet.createRow(rowIndex);
	            for (int i = 0; i < properties.length; i++)
	            {
	                SXSSFCell newCell = dataRow.createCell(i);
	 
	                Object o =  jo.get(properties[i]);
	                String cellValue = ""; 
	                if(o==null) cellValue = "";
	                else if(o instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(o);
	                else if(o instanceof Float || o instanceof Double) 
	                    cellValue= new BigDecimal(o.toString()).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	                else cellValue = o.toString();
	 
	                newCell.setCellValue(cellValue);
	                newCell.setCellStyle(cellStyle);
	            }
	            rowIndex++;
	        }
	        // 自动调整宽度
	        for (int i = 0; i < headers.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	        try {
	            workbook.write(out);
	            workbook.close();
	            workbook.dispose();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
	    //Web 导出excel
	    public static void downloadExcelFile(String title,Map<String,String> headMap,JSONArray ja,HttpServletResponse response){
	        try {
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            ExcelUtil.exportExcelX(headMap,ja,null,0,os);
	            byte[] content = os.toByteArray();
	            InputStream is = new ByteArrayInputStream(content);
	            // 设置response参数，可以打开下载页面
	            response.reset();
	 
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
	            response.setHeader("Content-Disposition", "attachment;filename="+ new String((title + ".xlsx").getBytes(), "iso-8859-1"));
	            response.setContentLength(content.length);
	            ServletOutputStream outputStream = response.getOutputStream();
	            BufferedInputStream bis = new BufferedInputStream(is);
	            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
	            byte[] buff = new byte[8192];
	            int bytesRead;
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	 
	            }
	            bis.close();
	            bos.close();
	            outputStream.flush();
	            outputStream.close();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
