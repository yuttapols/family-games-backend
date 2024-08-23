package com.it.reservation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.reservation.dto.response.ReservationResDTO;
import com.it.reservation.payload.CustomerUserAttr;
import com.it.reservation.service.ReportService;
import com.it.reservation.service.ReservationService;
import com.it.reservation.util.Constants;
import com.it.reservation.util.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

	@Autowired
	ReservationService reservationService;
	
	@Override
	public File revGenExcel(CustomerUserAttr userAttr, String type) throws IOException, Exception {
		String template = "excel_template.xlsx";
        List<ReservationResDTO> dataset = this.setData(type);

        String basePath = System.getProperty("user.dir");
        String folderPath = basePath + File.separator + Constants.REPORT.PATH_FOLDER_REPORT_EXCEL;;

        File file = new File(folderPath);

        String outputPathStr;
        if (file.exists() && file.isDirectory()) {
            outputPathStr = file.getAbsolutePath() + File.separator + template;
        } else {
            return null;
        }

        InputStream in = new FileInputStream(new File(outputPathStr));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        this.setCellForData(dataset, spreadsheet);

        try {
            File tempFile = File.createTempFile("report", ".xlsx");
            FileOutputStream out = new FileOutputStream(tempFile);
            workbook.write(out);

            return tempFile;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
	}
	
	@Transactional(readOnly = true)
    public List<ReservationResDTO> setData(String type) throws Exception {
        List<ReservationResDTO> datas = new ArrayList<>();
        List<ReservationResDTO> revData = reservationService.getRevAll();

        List<String> statusCancel = new ArrayList<>(Arrays.asList("3","4")) ;
        
        if (CollectionUtils.isNotEmpty(revData)) {
            if (Constants.REPORT.TYPE_STATUS_ALL.equals(type)) {
            	datas = revData.stream().filter(x-> !"1".equals(x.getRevStatus())).collect(Collectors.toList());
            } else if(Constants.REPORT.TYPE_STATUS_SUCCESS.equals(type)){
            	datas = revData.stream().filter(x-> "2".equals(x.getRevStatus())).collect(Collectors.toList());
            }else if(Constants.REPORT.TYPE_STATUS_CANCEL.equals(type)){
            	datas = revData.stream().filter(x-> statusCancel.contains(x.getRevStatus())).collect(Collectors.toList());
            }
        }

        return datas;
    }
	
	public void setCellForData(List<ReservationResDTO> datas, XSSFSheet spreadsheet) {
        int i = 3;
        int index = 1;
        List<String> statusCancel = new ArrayList<>(Arrays.asList("3","4")) ;
        for (ReservationResDTO item : datas) {
            XSSFRow row = spreadsheet.createRow(i);

            Cell cellV1 = row.createCell(0);
            cellV1.setCellValue(index++);
            setHeaderCellStyle(spreadsheet, cellV1);

            Cell cellV2 = row.createCell(1);
            String revNoFullName = item.getRevNo().concat(String.format("%03d", item.getRevNoNumber()));
            cellV2.setCellValue(revNoFullName);
            setHeaderCellStyle(spreadsheet, cellV2);

            Cell cellV3 = row.createCell(2);
            cellV3.setCellValue(item.getSeatTypeName());
            setHeaderCellStyle(spreadsheet, cellV3);

            Cell cellV4 = row.createCell(3);
            cellV4.setCellValue(DateUtil.timeStampToString(item.getRevTime()));
            setHeaderCellStyle(spreadsheet, cellV4);

            Cell cellV5 = row.createCell(4);
            cellV5.setCellValue(item.getCreateBy());
            setHeaderCellStyle(spreadsheet, cellV5);

            Cell cellV6 = row.createCell(5);
            cellV6.setCellValue("2".equals(item.getRevStatus()) ? "จองคิวสำเร็จ" :  statusCancel.contains(item.getRevStatus()) ? "ยกเลิกคิวสำเร็จ" : "");
            setHeaderCellStyle(spreadsheet, cellV6);

            Cell cellV7 = row.createCell(6);
            cellV7.setCellValue(item.getReasonNameTh());
            setHeaderCellStyle(spreadsheet, cellV7);
            i++;

        }
    }

    private void setHeaderCellStyle(XSSFSheet sheet, Cell cell) {
        CellStyle s = null;

        s = sheet.getWorkbook().createCellStyle();
        cell.setCellStyle(s);

        Font f = sheet.getWorkbook().createFont();
        f.setFontName("TH SarabunPSK");

        s.setBorderBottom(CellStyle.BORDER_THIN);
        s.setBorderLeft(CellStyle.BORDER_THIN);
        s.setBorderRight(CellStyle.BORDER_THIN);
        s.setBorderTop(CellStyle.BORDER_THIN);
        s.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        s.setAlignment(CellStyle.ALIGN_CENTER);
        s.setFont(f);

    }
}
