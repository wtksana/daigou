package com.daigou.client.util

import com.daigou.common.util.DateUtil
import com.daigou.core.domain.Entity
import com.daigou.core.domain.ExcelConstant
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFRichTextString
import java.beans.Introspector
import java.io.File
import java.util.*


/**
 * Created by wt on 2017/3/13.
 */
private val sheetMaxCount = 1000000//单个sheet最多写入行数

/**
 * 创建Workbook
 * @return
 */
private fun createWorkbook(): Workbook {
    val wb = SXSSFWorkbook(10000)
    val hcs = wb.createCellStyle()
    hcs.setBorderBottom(BorderStyle.THIN)
    hcs.setBorderLeft(BorderStyle.THIN)
    hcs.setBorderRight(BorderStyle.THIN)
    hcs.setBorderTop(BorderStyle.THIN)
    hcs.setAlignment(HorizontalAlignment.CENTER)
    val hfont = wb.createFont()
    hfont.fontName = "宋体"
    hfont.fontHeightInPoints = 16.toShort()// 设置字体大小
    hfont.bold = true// 加粗
    hcs.setFont(hfont)

    val tcs = wb.createCellStyle()
    tcs.setBorderBottom(BorderStyle.THIN)
    tcs.setBorderLeft(BorderStyle.THIN)
    tcs.setBorderRight(BorderStyle.THIN)
    tcs.setBorderTop(BorderStyle.THIN)
    val tfont = wb.createFont()
    tfont.fontName = "宋体"
    tfont.fontHeightInPoints = 12.toShort()// 设置字体大小
    tfont.bold = true// 加粗
    tcs.setFont(tfont)

    val cs = wb.createCellStyle()
    cs.setBorderBottom(BorderStyle.THIN)
    cs.setBorderLeft(BorderStyle.THIN)
    cs.setBorderRight(BorderStyle.THIN)
    cs.setBorderTop(BorderStyle.THIN)
    val font = wb.createFont()
    font.fontName = "宋体"
    font.fontHeightInPoints = 12.toShort()// 设置字体大小

    return wb
}

/**
 * 创建表头
 * @param sheet
 * *
 * @param headers
 */
private fun createHeader(sheet: Sheet, title: String, header: Array<String>) {

    //设置标题
    val tRow = sheet.createRow(0)
    val hc = tRow.createCell(0)
    hc.setCellValue(XSSFRichTextString(title))
    sheet.addMergedRegion(CellRangeAddress(0, 0, 0, header.size - 1))// 合并标题行：起始行号，终止行号， 起始列号，终止列号
    hc.cellStyle = sheet.workbook.getCellStyleAt(1)

    //设置表头
    val nRow = sheet.createRow(1)
    for (i in header.indices) {
        val cell = nRow.createCell(i)
        cell.setCellValue(header[i])
        cell.cellStyle = sheet.workbook.getCellStyleAt(2)
    }
}

/**
 * JavaBean转Map

 * @param obj
 * *
 * @return
 */
fun beanToMap(obj: Any): Map<String, Any> {
    val map = hashMapOf<String, Any>()
//    try {
    val beanInfo = Introspector.getBeanInfo(obj.javaClass)
    val propertyDescriptors = beanInfo.propertyDescriptors
    for (property in propertyDescriptors) {
        val key = property.name
        // 过滤class属性
        if (key != "class") {
            // 得到property对应的getter方法
            val getter = property.readMethod
            val value = getter.invoke(obj)
            map.put(key, value)
        }

    }
//    } catch (e: Exception) {
//        println("beanToMap Error " + e)
//    }
    return map
}

fun exportExcel(entity: Entity, data: List<Any>, file: File): String? {

    try {
        val title = ExcelConstant.title[entity]
        val header = ExcelConstant.header[entity]
        val fields = ExcelConstant.fields[entity]

        val wb = createWorkbook()
        val sheet = wb.createSheet(title)
        createHeader(sheet, title!!, header!!)
        var body = 2//正文从第三行开始，Header占了两行
        for (obj in data) {
            val map = beanToMap(obj)
            val nRow = sheet.createRow(body++)
            for (i in fields!!.indices) {
                val cell = nRow.createCell(i)
                setCellValue(cell, map[fields[i]])
            }
        }
        wb.write(file.outputStream())
    } catch(e: Exception) {
        e.printStackTrace()
        return e.localizedMessage
    }

    return null
}

fun setCellValue(cell: Cell, value: Any?) {
    if (value is Date) {
        cell.setCellValue(DateUtil.dateStr4(value))
    } else {
        cell.setCellValue(value.toString())
    }
}