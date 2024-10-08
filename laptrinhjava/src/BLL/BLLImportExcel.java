package BLL;

import java.lang.classfile.instruction.SwitchCase;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.IfFunc;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import DTO.HoiVien;
import GUI.CONTROLLER.informationCTR;

public class BLLImportExcel {
    private ArrayList<String> tenCot = new ArrayList<String>();
    private BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
    
    //Lấy tên cột
	public ArrayList<String> layTenCot(XSSFSheet sheet, String ds) {
		// Danh sách tên cột
	    if (ds.equals("Hội viên")) {
	        tenCot.add("Mã hội viên");
	        tenCot.add("Họ tên hội viên");
	        tenCot.add("Giới tính");
	        tenCot.add("Gmail");
	        tenCot.add("Mã Tài khoản");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	    } else if (ds.equals("Nhân viên")) {
	        tenCot.add("Mã nhân viên");
	        tenCot.add("Họ và tên");
	        tenCot.add("Giới tính");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Số căn cước");
	        tenCot.add("Mã cơ sở");
	        tenCot.add("Vai trò");
	        tenCot.add("Lương");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	        tenCot.add("ID Tài Khoản");
	    }
	    return tenCot;
	}
	public boolean kiemTraMaHV(String ma) {
		ArrayList<HoiVien> dsHoiVien = bllQuanLyDanhSach.getDataHoiVien();
		for(HoiVien hVien : dsHoiVien) {
			if(hVien.getMaHoiVien().equals(ma)) {
				return false;
			}
		}
		return true;
	}
	public String kiemTraTTHV(String text, Cell cell, int j) {
		switch (j) {
			case 0:
				String regex_maHV = "^HV\\d{3}$";
				Pattern p_maHV = Pattern.compile(regex_maHV);
				Matcher m_maHV = p_maHV.matcher(text);
				if(text.length() != 5) {
	            	return "Mã hội viên bắt buộc có 5 kí tự, lỗi tại ô: "+cell.getAddress();
				}
				else if(!m_maHV.matches()) {
					return "Mã hội viên phải bắt đầu bằng HV và 3 chữ số theo sau, lỗi tại ô: "+cell.getAddress();
				}
				else if(!kiemTraMaHV(text)) {
					return "Mã hội viên đã tồn tại vui lòng thêm mã hội viên khác, lỗi tại ô: "+cell.getAddress();
				}
				break;
			case 1:
				String regex_userName = "^[\\p{L}\\p{M}']+(?:[\\s][\\p{L}\\p{M}']+)*$";
		        Pattern p_userName = Pattern.compile(regex_userName);
	            Matcher m_userName = p_userName.matcher(text);
	            if(!(text.length() > 0 && text.length()<=50)) {
	            	return "Tên hội viên chỉ dài từ 1 đến 50 kí tự, lỗi tại ô: "+cell.getAddress();
	            }
	            else if(!m_userName.matches()) {
	            	return "Tên hội viên không được chứa kí tự đặc biệt và số, lỗi tại ô: " + cell.getAddress();
	            }
				break;
			case 2:
				if(!text.equals("Nam") || !text.equals("Nữ")) {
					return "Giới tình phải là Nam hoặc Nữ, lỗi tại ô: "+cell.getAddress();
				}
				break;
			case 3:
				String regex_email = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,7}$";
				Pattern p_email = Pattern.compile(regex_email);
				Matcher m_email = p_email.matcher(text);
				if (!(text.length() >= 1 && text.length() <= 345)) {
					return "Email dài từ 1 đến 345 kí tự, lỗi tại ô: "+cell.getAddress();
				}
				else if (!m_email.matches()) {
					return "Email không hợp lệ"+ cell.getAddress();
				}
				break;
			case 4:
				String regex_MaTK = "^TK\\d{3}$";
				Pattern p_MaTK = Pattern.compile(regex_MaTK);
				Matcher m_MaTK = p_MaTK.matcher(text);
				if (text.length()!=5) {
					return "Mã tài khoản bắt buộc phải có 5 kí tự, lỗi tại ô: " + cell.getAddress();
				}
				else if (!m_MaTK.matches()) {
					return "Mã tài khoản phải có TK và 3 số bất kì vd: TK001, TK999, lỗi tại ô: "+cell.getAddress();
				}
				break;
			case 5:
				if(!bllQuanLyDanhSach.kiemTraSDT(text)){
	                return "Số điện thoại không hợp lệ, lỗi tại ô: " + cell.getAddress();
	            }
				break;
			case 6:
				String regex_Date = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])$";
				Pattern p_Date = Pattern.compile(regex_Date);
				Matcher m_Date = p_Date.matcher(text);
				if(!m_Date.matches()) {
					return "Ngày sinh phải theo định dạng yyyy-mm-dd, lỗi tại ô: "+ cell.getAddress();
				}
				else {
					 String[] parts = text.split("-");
	
			        // Chuyển các phần thành số nguyên
			        int year = Integer.parseInt(parts[0]);
			        int month = Integer.parseInt(parts[1]);
			        int day = Integer.parseInt(parts[2]);
			        if(year < 1900) {
			        	return "Năm sinh phải lớn 1900, lỗi tại ô: " + cell.getAddress();
			        }
			        if(!isValidDate(day, month, year)) {
			        	return "Ngày tháng năm sinh không hợp lệ, lỗi tại ô: " + cell.getAddress();
			        }
				}
				break;
			case 7:
	        	String regex_account = "^[a-zA-Z0-9]{5,20}$";
				Pattern p_account = Pattern.compile(regex_account);
				Matcher m_account = p_account.matcher(text);
	        	if(!bllQuanLyDanhSach.kiemTraTenTK(text)){
	        		return "Tài khoản không được trùng lập, lỗi tại ô: "+cell.getAddress();
	        	}
	        	else if(!m_account.matches()) {
	        		return "Tài khoản không được chứa kí tự đặc biệt và phải dài từ 5 đến 20 kí tự!"+ cell.getAddress();
	        	}
				break;
			case 8:
				String regex_pass = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
	        	Pattern p_pass = Pattern.compile(regex_pass);
				Matcher m_pass = p_pass.matcher(text);
	        	if(!m_pass.matches()) {
	                return "Mật khẩu phải dài hơn 6 kí tự bao gồm cả chữ và số, lỗi tại ô: " + cell.getAddress();
	        	}
				break;
		default:
			break;
		}
		return "Done";
	}
	public String kiemTraDuLieu(XSSFSheet sheet, String ds) {
        tenCot.add("Mã hội viên");
        tenCot.add("Họ tên hội viên");
        tenCot.add("Giới tính");
        tenCot.add("Gmail");
        tenCot.add("Mã Tài khoản");
        tenCot.add("Số điện thoại");
        tenCot.add("Ngày sinh");
        tenCot.add("Tài khoản");
        tenCot.add("Mật khẩu");
        if(kiemTraCacCotNhapVao(sheet, ds).equals("Kiểm tra tên cột không thành công !")) {
        	for(int i=1;i<sheet.getLastRowNum()-1;i++) {
        		XSSFRow row = sheet.getRow(i);
        		for(int j=0;j<tenCot.size();j++) {
        			Cell cell = row.getCell(j);
        			if(cell == null) {
        				return "Có ô không có thông tin vui lòng kiểm tra lại";
        			}
        			else {
        				String text = cell.getStringCellValue();
        				return kiemTraTTHV(text, cell, j);
        			}
//        			System.out.print(" "+cell.getStringCellValue());
        		}
//        		System.out.println();
        	}
        }
        else {
			return kiemTraCacCotNhapVao(sheet, ds);
		}
        return "Kiểm tra dữ liệu thành công!";
	}
	//kiểm tra các tên cột
	public String kiemTraCacCotNhapVao(XSSFSheet sheet, String ds) {

	    // Danh sách tên cột
	    if (ds.equals("Hội viên")) {
	        tenCot.add("Mã hội viên");
	        tenCot.add("Họ tên hội viên");
	        tenCot.add("Giới tính");
	        tenCot.add("Gmail");
	        tenCot.add("Mã Tài khoản");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	    } else if (ds.equals("Nhân viên")) {
	        tenCot.add("Mã nhân viên");
	        tenCot.add("Họ và tên");
	        tenCot.add("Giới tính");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Số căn cước");
	        tenCot.add("Mã cơ sở");
	        tenCot.add("Vai trò");
	        tenCot.add("Lương");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	        tenCot.add("ID Tài Khoản");
	    }

	    // Lấy dòng đầu tiên từ sheet
	    int row = 0;
	    Row firstRow = sheet.getRow(row);
	    while (true) {
	    	if(firstRow == null) {
	    		row++;
	    		firstRow = sheet.getRow(row);
	    	}
	    	else {
	    		break;
	    	}
		}
	    // Kiểm tra số lượng cột trong file Excel có khớp với số cột mong đợi không
	    if (firstRow.getPhysicalNumberOfCells() != tenCot.size()) {
	    	return "Số lượng cột trong file Excel không khớp với yêu cầu.";
	    }

	    // Kiểm tra tên các cột
	    for (int i = 0; i < tenCot.size(); i++) {
	        Cell cell = firstRow.getCell(i);
	        if (cell == null || cell.getCellType() != CellType.STRING || !cell.getStringCellValue().equalsIgnoreCase(tenCot.get(i))) {
	            return "Tên cột không khớp: ô '" + cell.getAddress() + "' không khớp với '" + tenCot.get(i) + "'";
	        }
	    }

	    return "Kiểm tra tên cột không thành công !";
	}
	public boolean isValidDate(int day,int month,int year) {
		boolean isLeapYear = ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
		int maxDayinMonth = 0;
		if(month == 2 && isLeapYear) {
			maxDayinMonth = 29;
		}
		else {
			switch (month) {
			case 1,3,5,7,8,10,12:
				maxDayinMonth = 31;
				break;
			case 4,6,9,11:
				maxDayinMonth = 30;
				break;
			default:
				return false;
			}
		}
		return day >= 1 && day <= maxDayinMonth;
	}
}

