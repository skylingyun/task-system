package com.ybz.utils;

import com.ybz.exception.CustomRuntimeException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Guoru on 201/9/15.
 */
public class ExportExcel {
    private static LoggerHelper logger = new LoggerHelper(ExportExcel.class);
    private XSSFWorkbook wb = null;

    private XSSFSheet sheet = null;

    /**
     * @param wb
     * @param sheet
     */
    public ExportExcel(XSSFWorkbook wb, XSSFSheet sheet) {
        super();
        this.wb = wb;
        this.sheet = sheet;
    }

    /**
     * @return the sheet
     */
    public XSSFSheet getSheet() {
        return sheet;
    }

    /**
     * @param sheet the sheet to set
     */
    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * @return the wb
     */
    public XSSFWorkbook getWb() {
        return wb;
    }

    /**
     * @param wb the wb to set
     */
    public void setWb(XSSFWorkbook wb) {
        this.wb = wb;
    }

    /**
     * 创建通用EXCEL头部
     *
     * @param headString 头部显示的字符
     * @param colSum     该报表的列数
     */
    public void createNormalHead(String headString, int colSum) {

        XSSFRow row = sheet.createRow(0);

        // 设置第一行
        XSSFCell cell = row.createCell(0);
        row.setHeight((short) 400);

        // 定义单元格为字符串类型
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new XSSFRichTextString(headString));

        // 指定合并区域
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) colSum));

        XSSFCellStyle cellStyle = wb.createCellStyle();

        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 设置单元格字体
        XSSFFont font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 300);
        cellStyle.setFont(font);

        cell.setCellStyle(cellStyle);
    }

    /**
     * 创建通用报表第二行
     *
     * @param params 统计条件数组
     * @param colSum 需要合并到的列索引
     */
    public void createNormalTwoRow(String[] params, int colSum) {
        XSSFRow row1 = sheet.createRow(1);
        row1.setHeight((short) 300);

        XSSFCell cell2 = row1.createCell(0);

        cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell2.setCellValue(new XSSFRichTextString("统计时间：" + params[0] + "至"
                + params[1]));

        // 指定合并区域
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 0, 1, (short) colSum));

        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 设置单元格字体
        XSSFFont font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        cell2.setCellStyle(cellStyle);

    }

    /**
     * 设置报表标题
     *
     * @param columHeader 标题字符串数组
     */
    public void createColumHeader(List<String> columHeader) {

        // 设置列头
        XSSFRow row2 = sheet.createRow(2);

        // 指定行高
        row2.setHeight((short) 600);

        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        XSSFFont font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        /*
         * cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体
         * cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
         * cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         * cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
         * cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
         */

        // 设置单元格背景色
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        XSSFCell cell3 = null;

        for (int i = 0; i < columHeader.size(); i++) {
            cell3 = row2.createCell(i);
            cell3.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new XSSFRichTextString(columHeader.get(i)));
            sheet.autoSizeColumn(i);
        }

    }

    /**
     * 创建内容单元格
     *
     * @param wb    HSSFWorkbook
     * @param row   HSSFRow
     * @param col   short型的列索引
     * @param align 对齐方式
     * @param val   列值
     */
    public void cteateCell(XSSFWorkbook wb, XSSFRow row, int col, short align,
                           String val) {
        XSSFCell cell = row.createCell(col);
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new XSSFRichTextString(val));
        XSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(align);
        cell.setCellStyle(cellstyle);
        sheet.autoSizeColumn(col);
    }

    /**
     * 创建合计行
     *
     * @param colSum    需要合并到的列索引
     * @param cellValue
     */
    public void createLastSumRow(int colSum, String[] cellValue) {

        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        XSSFFont font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        XSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
        XSSFCell sumCell = lastRow.createCell(0);

        sumCell.setCellValue(new XSSFRichTextString("合计"));
        sumCell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), (short) 0,
                sheet.getLastRowNum(), (short) colSum));// 指定合并区域

        for (int i = 2; i < (cellValue.length + 2); i++) {
            sumCell = lastRow.createCell(i);
            sumCell.setCellStyle(cellStyle);
            sumCell.setCellValue(new XSSFRichTextString(cellValue[i - 2]));

        }

    }

    /**
     * 输入EXCEL文件
     *
     * @param fileName 文件名
     */
    public void outputExcel(String fileName) {
        FileOutputStream fos = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            logger.error("找不到文件", e.getMessage());
            throw new CustomRuntimeException("找不到文件");
        } catch (IOException e) {
            logger.error("文件操作异常", e.getMessage());
            throw new CustomRuntimeException("文件操作异常");
        }
    }

    /**
     * 样式加工站
     * @param list
     * @param excelHeader
     * @param sheetName
     * @return
     */
    public static HSSFWorkbook getHssfWorkbook(List<?> list, String[] excelHeader,String sheetName) {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个工作表
        HSSFSheet sheet = wb.createSheet(sheetName);
        //在工作表中合并首行并居中
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelHeader.length - 1));
        // 创建[标题]样式
        HSSFCellStyle titleStyle = wb.createCellStyle();
        // 设置[标题]样式
        titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        //创建[标题]字体
        HSSFFont font = wb.createFont();
        //设置[标题]字体
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把[标题字体]应用到[标题样式]
        cellStyle.setFont(font);

        // 创建[列首]样式
        HSSFCellStyle headersStyle = wb.createCellStyle();
        // 设置[列首]样式
        headersStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        headersStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headersStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headersStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headersStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headersStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headersStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //创建[列首]字体
        HSSFFont headersFont = wb.createFont();
        //设置[列首]字体
        headersFont.setColor(HSSFColor.VIOLET.index);
        headersFont.setFontHeightInPoints((short) 12);
        headersFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把[列首字体]应用到[列首样式]
        headersStyle.setFont(headersFont);

        // 创建[表中数据]样式
        HSSFCellStyle dataSetStyle = wb.createCellStyle();
        // 设置[表中数据]样式
        dataSetStyle.setFillForegroundColor(HSSFColor.GOLD.index);
        dataSetStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        dataSetStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        dataSetStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        dataSetStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        dataSetStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        dataSetStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dataSetStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建[表中数据]字体
        HSSFFont dataSetFont = wb.createFont();
        // 设置[表中数据]字体
        dataSetFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        dataSetFont.setColor(HSSFColor.BLUE.index);
        // 把[表中数据字体]应用到[表中数据样式]
        dataSetStyle.setFont(dataSetFont);

        //创建标题行-增加样式-赋值
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue(sheetName);

        // 创建列首-增加样式-赋值
        HSSFRow row = sheet.createRow(1);
        for (short i = 0; i < excelHeader.length; i++) {

            @SuppressWarnings("deprecation")
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headersStyle);
            HSSFRichTextString text = new HSSFRichTextString(excelHeader[i]);
            cell.setCellValue(text);
        }

        // 创建表中数据行-增加样式-赋值
        Iterator<?> it = list.iterator();
        int index = 1;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
//            sheet.autoSizeColumn(index);
            Object t = it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                @SuppressWarnings("deprecation")
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(dataSetStyle);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    @SuppressWarnings("rawtypes")
                    Class tCls = t.getClass();
                    @SuppressWarnings("unchecked")
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});

                    // 如果是时间类型,按照格式转换
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        textValue = sdf.format(date);
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        if (value != null) {
                            textValue = value.toString();
                        }
                    }

                    // 利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            // 不是数字做普通处理
                            cell.setCellValue(textValue);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
//                    //清理资源
//                    try {
//                        wb.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
        return wb;
    }
}
