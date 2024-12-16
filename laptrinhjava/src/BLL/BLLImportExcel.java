package BLL;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import DAL.DataHoiVien;
import DAL.DataNhanVien;
import DAL.DataTaiKhoan;
import DTO.DTOTaiKhoan;
import DTO.HoiVien;
import DTO.NhanVien;

public class BLLImportExcel {
    private ArrayList<String> tenCotHV = new ArrayList<String>();
    private ArrayList<String> tenCotNV = new ArrayList<String>();
    private BLLQuanLyDanhSach bllQuanLyDanhSach;
    private DataTaiKhoan dataTaiKhoan;
    public ArrayList<HoiVien> dsHV = new ArrayList<HoiVien>();
    public ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
    public ArrayList<DTOTaiKhoan> dsTaiKhoan = new ArrayList<DTOTaiKhoan>();
    public BLLImportExcel() {
    	tenCotHV = new ArrayList<String>();
    	tenCotHV.add("Mã hội viên");
        tenCotHV.add("Họ tên hội viên");
        tenCotHV.add("Giới tính");
        tenCotHV.add("Gmail");
        tenCotHV.add("Mã Tài khoản");
        tenCotHV.add("Số điện thoại");
        tenCotHV.add("Ngày sinh");
        tenCotHV.add("Tài khoản");
        tenCotHV.add("Mật khẩu");
        
    	tenCotNV = new ArrayList<String>();
    	tenCotNV.add("Mã nhân viên");
        tenCotNV.add("Họ và tên");
        tenCotNV.add("Giới tính");
        tenCotNV.add("Ngày sinh");
        tenCotNV.add("Số điện thoại");
        tenCotNV.add("Số căn cước");
        tenCotNV.add("Mã cơ sở");
        tenCotNV.add("Vai trò");
        tenCotNV.add("Lương");
        tenCotNV.add("Tài khoản");
        tenCotNV.add("Mật khẩu");
        tenCotNV.add("ID Tài Khoản");
        
    	bllQuanLyDanhSach = new BLLQuanLyDanhSach();
    	dataTaiKhoan = new DataTaiKhoan();
    }
    
    public ArrayList<DTOTaiKhoan> getDSTaiKhoan(){
    	return this.dsTaiKhoan;
    }
    public ArrayList<HoiVien> getDSHoiVien(){
    	return this.dsHV;
    }
    public ArrayList<String> getTenCotHV(){
    	return tenCotHV;
    }
    public ArrayList<String> getTenCotNV(){
    	return tenCotNV;
    }
    public ArrayList<NhanVien> getDSNhanVien(){
    	return this.dsNV;
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
	
	public boolean kiemTraMaNV(String ma) {
		ArrayList<NhanVien> dsNhanVien = bllQuanLyDanhSach.getDataNhanVien();
		for(NhanVien nVien : dsNhanVien) {
			if(nVien.getMaNhanVien().equals(ma)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean kiemTraMaTK(String ma) {
		return dataTaiKhoan.kiemTraTonTaiMaTK(ma);
	}
	
	public String kiemTraDuLieuHV(XSSFSheet sheet, String ds) {
	        if(kiemTraCacCotNhapVao(sheet, ds).equals("Kiểm tra tên cột thành công !")) {
	        	for(int i=1;i<=sheet.getLastRowNum();i++) {
	        		HoiVien tempHoiVien = new HoiVien();
	        		DTOTaiKhoan tempTaiKhoan = new DTOTaiKhoan("","","","Q0001");
	        		XSSFRow row = sheet.getRow(i);
	        		for(int j=0;j<tenCotHV.size();j++) {
	        			if(row == null) {
	        				i++;
	        				continue;
	        			}
	        			Cell cell = row.getCell(j);
	        			if(cell == null) {
	        				return "Có ô không có thông tin vui lòng kiểm tra lại";
	        			}
	        			else {
	        				String text = cell.getStringCellValue().trim();
	        				System.out.print(text);
	        				switch (j) {
	        				case 0:
	        					String regex_maHV = "^HV\\d{3}$";
	        					Pattern p_maHV = Pattern.compile(regex_maHV);
	        					Matcher m_maHV = p_maHV.matcher(text);
	        					if(!m_maHV.matches()) {
	        						return "Mã hội viên phải bắt đầu bằng HV và 3 chữ số theo sau, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else if(!kiemTraMaHV(text)) {
	        						return "Mã hội viên đã tồn tại vui lòng thêm mã hội viên khác, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else {
	        						tempHoiVien.setMaHoiVien(text);
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
	        		            else {
	        		            	tempHoiVien.setHoten(text);
	        		            }
	        					break;
	        				case 2:
	        					String regex_GioiTinh = "(Nam|Nữ|nam|nữ)";
	        					Pattern p_GioiTinh = Pattern.compile(regex_GioiTinh);
	        					Matcher m_GioiTinh = p_GioiTinh.matcher(text);
	        					if(!m_GioiTinh.matches()) {
	        						return "Giới tình phải là Nam hoặc Nữ, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else {
	        						tempHoiVien.setGioitinh(text);
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
	        					else {
	        						tempHoiVien.setMail(text);
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
	        					else if (kiemTraMaTK(text)) {
	        						return "Mã tài khoản đã tồn tại, lỗi tại ô: "+ cell.getAddress();
	        					}
	        					else {
	        						tempTaiKhoan.setIDTaiKhoan(text);
	        						tempHoiVien.setIDTaiKhoan(text);
								}
	        					break;
	        				case 5:
	        					if(!bllQuanLyDanhSach.kiemTraSDT(text)){
	        		                return "Số điện thoại không hợp lệ, lỗi tại ô: " + cell.getAddress();
	        		            }
	        					else {
	        						tempHoiVien.setSdt(text);
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
	        				        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	                        		int currentDay = Calendar.getInstance().get(Calendar.DATE);
	                        		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
	        				        if(year < 1900 || year > currentYear) {
	        				        	return "Năm sinh phải không được nhỏ hơn 1900 và lớn hơn năm hiện tại, lỗi tại ô: " + cell.getAddress();
	        				        }
	        				        if(!isValidDate(day, month, year)) {
	        				        	return "Ngày tháng năm sinh không hợp lệ, lỗi tại ô: " + cell.getAddress();
	        				        }
	        				        else {
	        				        	if (currentYear - year < 18) {
	                    				    return "Tuổi của hội viên chưa đủ 18, vui lòng kiểm tra lại, lỗi tại ô: " + cell.getAddress();
	                    				} 
	                    				else if (currentYear - year == 18) {
	                    				    // Kiểm tra tháng và ngày
	                    				    if (currentMonth < month || (currentMonth == month && currentDay < day)) {
	                    				    	System.out.println((currentDay) + " " + day);				    
	                    				        return "Tuổi của hội viên chưa đủ 18, vui lòng kiểm tra lại!, lỗi tại ô: " + cell.getAddress();
	                    				    }
	                    				}
	                    				else {
	                    					@SuppressWarnings("deprecation")
											Date date = new Date(year-1900,month-1,day);
	                    					tempHoiVien.setNgaysinh(date);
	                    				}
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
	        		        	else {
	        		        		tempTaiKhoan.setTaiKhoan(text);
	        		        	}
	        					break;
	        				case 8:
	        					String regex_pass = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
	        		        	Pattern p_pass = Pattern.compile(regex_pass);
	        					Matcher m_pass = p_pass.matcher(text);
	        		        	if(!m_pass.matches()) {
	        		                return "Mật khẩu phải dài hơn 6 kí tự bao gồm cả chữ và số, lỗi tại ô: " + cell.getAddress();
	        		        	}
	        		        	else {
	        		        		tempTaiKhoan.setMatKhau(text);
	        		        	}
	        					break;
		        			default:
		        				break;
		        			}
	        			
	        			}
	        			
//	        			System.out.print(" "+cell.getStringCellValue());
	        		}
	        		dsHV.add(tempHoiVien);
	      	      	dsTaiKhoan.add(tempTaiKhoan);
//	        		System.out.println();
	        	}
	        }
	        else {
				return kiemTraCacCotNhapVao(sheet, ds);
			}
	      
		return "Kiểm tra dữ liệu thành công!";
	}
		//Kiểm tra dữ liệu nhân viên
		public String kiemTraDuLieuNV(XSSFSheet sheet, String ds) {
	        if(kiemTraCacCotNhapVao(sheet, ds).equals("Kiểm tra tên cột thành công !")) {
	        	for(int i=1;i<=sheet.getLastRowNum();i++) {
	        		NhanVien tempNhanVien = new NhanVien();
	        		DTOTaiKhoan tempTaiKhoan = new DTOTaiKhoan("","","","Q0001");
	        		XSSFRow row = sheet.getRow(i);
	        		for(int j=0;j<tenCotNV.size();j++) {
	        			if(row == null) {
	        				i++;
	        				continue;
	        			}
	        			Cell cell = row.getCell(j);
	        			if(cell == null) {
	        				return "Có ô không có thông tin vui lòng kiểm tra lại";
	        			}
	        			else {
	        				String text = cell.getStringCellValue().trim();
	        				switch (j) {
	        				case 0:
	        					String regex_maNV = "^NV\\d{3}$";
	        					Pattern p_maNV = Pattern.compile(regex_maNV);
	        					Matcher m_maNV = p_maNV.matcher(text);
	        					if(!m_maNV.matches()) {
	        						return "Mã nhân viên phải bắt đầu bằng NV và 3 chữ số theo sau, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else if(!kiemTraMaNV(text)) {
	        						return "Mã Nhân viên đã tồn tại vui lòng thêm mã hội viên khác, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else {
	        						tempNhanVien.setMaNhanVien(text);
	        					}
	        					break;
	        				case 1:
	        					String regex_userName = "^[\\p{L}\\p{M}']+(?:[\\s][\\p{L}\\p{M}']+)*$";
	        			        Pattern p_userName = Pattern.compile(regex_userName);
	        		            Matcher m_userName = p_userName.matcher(text);
	        		            if(!(text.length() > 0 && text.length()<=50)) {
	        		            	return "Tên nhân viên chỉ dài từ 1 đến 50 kí tự, lỗi tại ô: "+cell.getAddress();
	        		            }
	        		            else if(!m_userName.matches()) {
	        		            	return "Tên nhân viên không được chứa kí tự đặc biệt và số, lỗi tại ô: " + cell.getAddress();
	        		            }
	        		            else {
	        		            	tempNhanVien.setHoten(text);
	        		            }
	        					break;
	        				case 2:
	        					String regex_GioiTinh = "(Nam|Nữ|nam|nữ)";
	        					Pattern p_GioiTinh = Pattern.compile(regex_GioiTinh);
	        					Matcher m_GioiTinh = p_GioiTinh.matcher(text);
	        					if(!m_GioiTinh.matches()) {
	        						return "Giới tình phải là Nam hoặc Nữ, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else {
	        						tempNhanVien.setGioitinh(text);
	        					}
	        					break;
//	        				case 3:
//	        					String regex_email = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,7}$";
//	        					Pattern p_email = Pattern.compile(regex_email);
//	        					Matcher m_email = p_email.matcher(text);
//	        					if (!(text.length() >= 1 && text.length() <= 345)) {
//	        						return "Email dài từ 1 đến 345 kí tự, lỗi tại ô: "+cell.getAddress();
//	        					}
//	        					else if (!m_email.matches()) {
//	        						return "Email không hợp lệ"+ cell.getAddress();
//	        					}
//	        					else {
//	        						tempNhanVien.setMail(text);
//	        					}
//	        					break;
	        				case 3:
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
	        						int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	        						int currentDay = Calendar.getInstance().get(Calendar.DATE);
	        						int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
	        						if(year < 1900 || year > currentYear) {
	        							return "Năm sinh phải không được nhỏ hơn 1900 và lớn hơn năm hiện tại, lỗi tại ô: " + cell.getAddress();
	        						}
//	        						if(!isValidDate(day, month, year)) {
//	        							return "Ngày tháng năm sinh không hợp lệ, lỗi tại ô: " + cell.getAddress();
//	        						}
	        						else {
	        							if (currentYear - year < 18) {
	        								return "Tuổi của nhân viên chưa đủ 18, vui lòng kiểm tra lại, lỗi tại ô: " + cell.getAddress();
	        							} 
	        							else if (currentYear - year == 18) {
	        								// Kiểm tra tháng và ngày
	        								if (currentMonth < month || (currentMonth == month && currentDay < day)) {
	        									System.out.println((currentDay) + " " + day);				    
	        									return "Tuổi của nhân viên chưa đủ 18, vui lòng kiểm tra lại!, lỗi tại ô: " + cell.getAddress();
	        								}
	        							}
	        							else {
	        								@SuppressWarnings("deprecation")
	        								Date date = new Date(year-1900,month-1,day);
	        								tempNhanVien.setNgaysinh(date);
	        							}
	        						}
	        					}
	        					break;
	        				case 4:
	        					if(!bllQuanLyDanhSach.kiemTraSDT(text)){
	        						return "Số điện thoại không hợp lệ, lỗi tại ô: " + cell.getAddress();
	        					}
	        					else {
	        						tempNhanVien.setSdt(text);
	        					}
	        					break;
	        				case 5:
	        					//regex căn cước công dân
	                            String regex_cccd = "^[0-9]{12}$";
	                            Pattern p_cccd = Pattern.compile(regex_cccd);
	                            Matcher m_cccd = p_cccd.matcher(text);
	                            if(!m_cccd.matches() || text.length() > 12) {
	                            	return "Căn cước công dân không hợp lệ! Lỗi tại ô: "+cell.getAddress();
	                            }
	                            else {
	                            	tempNhanVien.setSocccd(text);
	                            }
	                            break;
	        				case 6:
	        					//regex mã cơ sở
	        					String regex_coso = "^CS\\d{3}$";
	        					Pattern p_coso = Pattern.compile(regex_coso);
	        					Matcher m_coso = p_coso.matcher(text);
	        					if(!m_coso.matches()) {
	        						return "Mã cơ sở không hợp lệ!, Lỗi tại ô: "+cell.getAddress();
	        					}
	        					else {
									tempNhanVien.setMacoso(text);
								};
								break;
	        				case 7:
	        					if(text.trim().equals("Nhân viên") || text.trim().equals("Quản lý")) {
	        						tempNhanVien.setVaitro(text);
	        						if(text.equals("Nhân viên")) {
	        							tempTaiKhoan.setIDQuyen("Q0002");
	        						}
	        						if(text.equals("Quản lý")) {
	        							tempTaiKhoan.setIDQuyen("Q0003");
	        						}
	        					}
	        					else {
	        						return "Vai trò của nhân viên phải là nhân viên hoặc quản lý, lỗi tại ô: "+cell.getAddress();
	        					}
	        					break;
	        				case 8:
	        					int numb;
	        					try {
	        						numb = Integer.parseInt(text);
	        					}
	        					catch (Exception e) {
									return "Lương phải là 1 số";
								}
	        					if(numb<=0) {
	        						return "Lương phải lớn hoặc bằng 1";
	        					}
	        					else {
									tempNhanVien.setLuong(numb);
								}
	        					break;
	        				case 9:
	        		        	String regex_account = "^[a-zA-Z0-9]{5,20}$";
	        					Pattern p_account = Pattern.compile(regex_account);
	        					Matcher m_account = p_account.matcher(text);
	        		        	if(!bllQuanLyDanhSach.kiemTraTenTK(text)){
	        		        		return "Tài khoản không được trùng lập, lỗi tại ô: "+cell.getAddress();
	        		        	}
	        		        	else if(!m_account.matches()) {
	        		        		return "Tài khoản không được chứa kí tự đặc biệt và phải dài từ 5 đến 20 kí tự!"+ cell.getAddress();
	        		        	}
	        		        	else {
	        		        		tempTaiKhoan.setTaiKhoan(text);
	        		        	}
	        					break;
	        				case 10:
	        					String regex_pass = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
	        		        	Pattern p_pass = Pattern.compile(regex_pass);
	        					Matcher m_pass = p_pass.matcher(text);
	        		        	if(!m_pass.matches()) {
	        		                return "Mật khẩu phải dài hơn 6 kí tự bao gồm cả chữ và số, lỗi tại ô: " + cell.getAddress();
	        		        	}
	        		        	else {
	        		        		tempTaiKhoan.setMatKhau(text);
	        		        	}
	        					break;
	        				case 11:
	        					String regex_MaTK = "^TK\\d{3}$";
	        					Pattern p_MaTK = Pattern.compile(regex_MaTK);
	        					Matcher m_MaTK = p_MaTK.matcher(text);
	        					if (text.length()!=5) {
	        						return "Mã tài khoản bắt buộc phải có 5 kí tự, lỗi tại ô: " + cell.getAddress();
	        					}
	        					else if (!m_MaTK.matches()) {
	        						return "Mã tài khoản phải có TK và 3 số bất kì vd: TK001, TK999, lỗi tại ô: "+cell.getAddress();
	        					}
	        					else if (kiemTraMaTK(text)) {
	        						return "Mã tài khoản đã tồn tại, lỗi tại ô: "+ cell.getAddress();
	        					}
	        					else {
	        						tempTaiKhoan.setIDTaiKhoan(text);
	        						tempNhanVien.setIDTaiKhoan(text);
	        					}
	        					break;
		        			default:
		        				break;
		        			}
	        			
	        			}
	        			
	        			System.out.print(" "+cell.getStringCellValue());
	        		}
	        		dsNV.add(tempNhanVien);
	      	      	dsTaiKhoan.add(tempTaiKhoan);
	        		System.out.println();
	        	}
	        }
	        else {
				return kiemTraCacCotNhapVao(sheet, ds);
			}
	      
		return "Kiểm tra dữ liệu thành công!";
	}
	//kiểm tra các tên cột
	public String kiemTraCacCotNhapVao(XSSFSheet sheet, String ds) {
	    // Lấy dòng đầu tiên từ sheet có dữ liệu
	    Row firstRow = null;
	    boolean isEmpty = true;
	    ArrayList<String> tenCot = new ArrayList<String>(); 
	    if(ds.equals("Hội viên")) {
	    	tenCot = tenCotHV;
	    }
	    else {
	    	tenCot = tenCotNV;                                                       
	    }
	    
	    for (Row row : sheet) {
	        // Duyệt qua các ô trong hàng
	        for (Cell cell : row) {
	            // Kiểm tra nếu ô không rỗng
	            if (cell.getCellType() != CellType.BLANK) {
	                firstRow = row;
	                isEmpty = false;
	                break;
	            }
	        }
	        if (!isEmpty) break;
	    }

	    // Kiểm tra sheet có dữ liệu không
	    if (isEmpty) {
	        return "Sheet không có dữ liệu";
	    }

	    // Kiểm tra số lượng cột trong dòng đầu tiên với số cột mong đợi
	    if (firstRow.getPhysicalNumberOfCells() != tenCot.size()) {
	    	System.out.println(tenCot.size());
	        return "Số lượng cột trong file Excel không khớp với yêu cầu.";
	    }

	    // Kiểm tra tên các cột trong dòng đầu tiên
	    for (int i = 0; i < tenCot.size(); i++) {
	        Cell cell = firstRow.getCell(i);
	        
	        // Kiểm tra nếu ô cột tên bị null hoặc không phải kiểu chuỗi, hoặc nội dung không khớp
	        if (cell == null || cell.getCellType() != CellType.STRING || !cell.getStringCellValue().equalsIgnoreCase(tenCot.get(i))) {
	            return "Tên cột không khớp: ô '" + (cell != null ? cell.getAddress() : "null") + "' không khớp với '" + tenCot.get(i) + "'";
	        }
	    }
	    return "Kiểm tra tên cột thành công !";
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
	
	//thêm dữ liệu vào csdl
	public void themDuLieuVaoHVCSDL() {
		DataHoiVien dataHoiVien = new DataHoiVien();
		for (int i = 0;i<dsHV.size();i++) {
			dataTaiKhoan.themTK(dsTaiKhoan.get(i));
			dataHoiVien.them(dsHV.get(i));
		}
	}
	
	public void themDuLieuVaoNVCSDL() {
		DataNhanVien dataNhanVien = new DataNhanVien();
		for (int i=0; i<dsNV.size();i++) {
			dataTaiKhoan.themTK(dsTaiKhoan.get(i));
			dataNhanVien.them(dsNV.get(i));
		}
	}
}

