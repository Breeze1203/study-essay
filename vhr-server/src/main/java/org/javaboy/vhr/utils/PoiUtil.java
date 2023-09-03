package org.javaboy.vhr.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javaboy.vhr.bean.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component("PiiUtil")
public class PoiUtil {


    private EmpUtil employeeByPage = null;

    // 生成excel文件
    public static ResponseEntity<byte[]> downExcel(List<Employee> employeeList) throws UnsupportedEncodingException {
        Workbook workbook = new XSSFWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Sheet sheet = workbook.createSheet("员工信息表");
        String[] str = new String[]{"姓名", "工号", "性别", "出生日期", "身份证号码", "婚姻状况", "民族", "籍贯", "政治面貌",
                "电子邮件", "电话号码", "联系地址", "所属部门", "职位", "职称", "聘用形式", "入职日期", "转正日期", "合同起始时间",
                "合同终止日期", "合同期限", "最高学历", "学校", "专业"};
        for (int i = 0; i < str.length; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        Row row = sheet.createRow(0);
        for (int i = 0; i < str.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(str[i]);
            cell.setCellStyle(cellStyle);
        }
//        employeeByPage = employeeService.getEmployeeByPage(null, null, null);
//        List<Employee> employeeList = employeeByPage.getEmployeeList();
        for (int i = 0; i < employeeList.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(employeeList.get(i).getName());
            cell.setCellStyle(cellStyle);
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(employeeList.get(i).getWorkId());
            cell1.setCellStyle(cellStyle);
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(employeeList.get(i).getGender());
            cell2.setCellStyle(cellStyle);
            Cell cell3 = row1.createCell(3);
            cell3.setCellValue(DateUtil.dateFormat(employeeList.get(i).getBirthday()));
            cell3.setCellStyle(cellStyle);
            Cell cell4 = row1.createCell(4);
            cell4.setCellValue(employeeList.get(i).getIdCard());
            cell4.setCellStyle(cellStyle);
            Cell cell5 = row1.createCell(5);
            cell5.setCellValue(employeeList.get(i).getWedlock());
            cell5.setCellStyle(cellStyle);
            Cell cell6 = row1.createCell(6);
            cell6.setCellValue(employeeList.get(i).getNation().getName());
            cell6.setCellStyle(cellStyle);
            Cell cell7 = row1.createCell(7);
            cell7.setCellValue(employeeList.get(i).getNativePlace());
            cell7.setCellStyle(cellStyle);
            Cell cell8 = row1.createCell(8);
            cell8.setCellValue(employeeList.get(i).getPoliticsstatus().getName());
            cell8.setCellStyle(cellStyle);
            Cell cell9 = row1.createCell(9);
            cell9.setCellValue(employeeList.get(i).getEmail());
            cell9.setCellStyle(cellStyle);
            Cell cell10 = row1.createCell(10);
            cell10.setCellValue(employeeList.get(i).getPhone());
            cell10.setCellStyle(cellStyle);
            Cell cell11 = row1.createCell(11);
            cell11.setCellValue(employeeList.get(i).getAddress());
            cell11.setCellStyle(cellStyle);
            Cell cell12 = row1.createCell(12);
            cell12.setCellValue(employeeList.get(i).getDepartment().getName());
            cell12.setCellStyle(cellStyle);
            Cell cell13 = row1.createCell(13);
            cell13.setCellValue(employeeList.get(i).getPosition().getName());
            cell13.setCellStyle(cellStyle);
            Cell cell14 = row1.createCell(14);
            cell14.setCellValue(employeeList.get(i).getjObLevel().getName());
            cell14.setCellStyle(cellStyle);
            Cell cell15 = row1.createCell(15);
            cell15.setCellValue(employeeList.get(i).getEngageForm());
            cell15.setCellStyle(cellStyle);
            Cell cell16 = row1.createCell(16);
            cell16.setCellValue(DateUtil.dateFormat(employeeList.get(i).getBegindate()));
            cell16.setCellStyle(cellStyle);
            Cell cell17 = row1.createCell(17);
            if (employeeList.get(i).getConversionTime() != null) {
                cell17.setCellValue(DateUtil.dateFormat(employeeList.get(i).getConversionTime()));
            }
            cell17.setCellStyle(cellStyle);
            Cell cell18 = row1.createCell(18);
            cell18.setCellValue(DateUtil.dateFormat(employeeList.get(i).getBeginContract()));
            cell18.setCellStyle(cellStyle);
            Cell cell19 = row1.createCell(19);
            cell19.setCellValue(DateUtil.dateFormat(employeeList.get(i).getEndContract()));
            cell19.setCellStyle(cellStyle);
            Cell cell20 = row1.createCell(20);
            cell20.setCellValue(employeeList.get(i).getContractTerm());
            cell20.setCellStyle(cellStyle);
            Cell cell21 = row1.createCell(21);
            cell21.setCellValue(employeeList.get(i).getTipTopdeGree());
            cell21.setCellStyle(cellStyle);
            Cell cell22 = row1.createCell(22);
            cell22.setCellValue(employeeList.get(i).getSchool());
            cell22.setCellStyle(cellStyle);
            Cell cell23 = row1.createCell(23);
            cell23.setCellValue(employeeList.get(i).getSpecialty());
            cell23.setCellStyle(cellStyle);
        }
        //创建一个新的字节数组，我们可以使用 ByteArrayOutputStream 类提供的方法将数据写入该字节数组中
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 将工作薄写入输入流中
        try {
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close();
                // 关闭输出流
                byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        // attachment 表示响应内容应该被下载并保存为文件，filename 是指定的文件名
        headers.setContentDispositionFormData("attachment",
                new String("员工信息表.xlsx".getBytes("utf-8"), "ISO-8859-1"));
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

     //引入(导入)Excel文件
    public static List<Employee> importExcel(MultipartFile file, EmpUtil emp) {
        //创建一个Employee对象
        Employee employee = null;
        // 创建一个List<employee>集合，将employee对象放入该集合中，对集合进行批量插入
        List<Employee> employees = new ArrayList<>();
        // 1.创建一个workbook对象
        XSSFWorkbook sheets = null;
        try {
            sheets = new XSSFWorkbook(file.getInputStream());
            // 2.获取workbook中表单工作表
            int numberOfSheets = sheets.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                // 3.获取一个sheet
                XSSFSheet sheetAt = sheets.getSheetAt(i);
                // 4.获取sheet的行数
                int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
                // 5.遍历每一行
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    // 6.跳过第一行
                    if (j == 0) {
                        continue;
                    }
                    // 7.如果中间有空行则跳过
                    if (sheetAt.getRow(j) == null) {
                        continue;
                    }
                    // 8.获取列数
                    int physicalNumberOfCells = sheetAt.getRow(j).getPhysicalNumberOfCells();
                    employee = new Employee();
                    // 9.遍历列数
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        // 获取当前行的每列值
                        XSSFCell cell = sheetAt.getRow(j).getCell(k);
//                        switch (cell.getCellType()) {
//                            // 先对cell里面的值类型进行判断
                        switch (k) {
                            case 0:
                                employee.setName(cell.getStringCellValue());
                                break;
                            case 1:
                                employee.setWorkId(cell.getStringCellValue());
                                break;
                            case 2:
                                employee.setGender(cell.getStringCellValue());
                                break;
                            case 3:
                               employee.setBirthday(DateUtil.stringFormat(cell.getStringCellValue()));
                                break;
                            case 4:
                                employee.setIdCard(cell.getStringCellValue());
                                break;
                            case 5:
                                employee.setWedlock(cell.getStringCellValue());
                                break;
                            case 6:
                                // 先获取到LIst<Employee>里面所有nation对象，然后遍历
                                List<Nation> nations = emp.getNations();
                                for (Nation e : nations) {
                                    if(e.getName().equals(cell.getStringCellValue())){
                                        employee.setNationId(e.getId());
                                        break;
                                    }
                                }
                                break;
                            case 7:
                                employee.setNativePlace(cell.getStringCellValue());
                                break;
                            case 8:
                                for (Politicsstatus e : emp.getPoliticsStatus()) {
                                    if(e.getName().equals(cell.getStringCellValue())){
                                        employee.setPoliticId(e.getId());
                                        break;
                                    }
                                }
                                break;
                            case 9:
                                employee.setEmail(cell.getStringCellValue());
                                break;
                            case 10:
                                employee.setPhone(cell.getStringCellValue());
                                break;
                            case 11:
                                employee.setAddress(cell.getStringCellValue());
                                break;
                            case 12:
                                //设置所属部门     employee.setDepartmentId(cell.getStringCellValue());
                                for (Department e : emp.getDepartments()) {
                                    if (e.getName().equals(cell.getStringCellValue())) {
                                        employee.setDepartmentId(e.getId());
                                        break;
                                    }
                                }
                                break;
                            case 13:
                                // 设置职位 employee.setJobLevelId();employee.setAddress(cell.getStringCellValue());
                                for (Position e : emp.getPositions()) {
                                    if (e.getName().equals(cell.getStringCellValue())) {
                                        employee.setPosId(e.getId());
                                        break;
                                    }
                                }
                                break;
                            case 14:
                                // 设置职称 employee.setPoliticId(cell.getStringCellValue());
                                for (JObLevel e : emp.getjObLevels()) {
                                    if (e.getName().equals(cell.getStringCellValue())) {
                                        employee.setJobLevelId(e.getId());
                                        break;
                                    }
                                }
                                break;
                            case 15:
                                employee.setEngageForm(cell.getStringCellValue());
                                break;
                            case 16:
                                employee.setBegindate(DateUtil.stringFormat(cell.getStringCellValue()));
                                break;
                            case 17:
                                employee.setConversionTime(DateUtil.stringFormat(cell.getStringCellValue()));
                                break;
                            case 18:
                                employee.setBeginContract(DateUtil.stringFormat(cell.getStringCellValue()));
                                break;
                            case 19:
                                employee.setEndContract(DateUtil.stringFormat(cell.getStringCellValue()));
                                break;
                            case 20:
                                employee.setContractTerm(cell.getNumericCellValue());
                                break;
                            case 21:
                                employee.setTipTopdeGree(cell.getStringCellValue());
                                break;
                            case 22:
                                employee.setSchool(cell.getStringCellValue());
                                break;
                            case 23:
                                employee.setSpecialty(cell.getStringCellValue());
                                break;
                        }
                    }
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
