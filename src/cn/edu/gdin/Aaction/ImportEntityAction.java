package cn.edu.gdin.Aaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.edu.gdin.Aservice.AdminCommonService;
import cn.edu.gdin.entity.Course;
import cn.edu.gdin.entity.Student;
import cn.edu.gdin.entity.Teacher;
import cn.edu.gdin.entity.UserLog;
import cn.edu.gdin.util.GetAgentUtil;
import cn.edu.gdin.util.GetIpUtil;
import cn.edu.gdin.util.Goals;
import cn.edu.gdin.util.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ImportEntityAction extends ActionSupport {
	public static final Logger logger = Logger.getLogger(ImportEntityAction.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
   private AdminCommonService adminCommonService;
   public void setAdminCommonService(AdminCommonService adminCommonService) {
	this.adminCommonService = adminCommonService;
}
	@SuppressWarnings("rawtypes")
	public String ImportData() throws Exception {
		// TODO Auto-generated method stub
		String en = Struts2Utils.getHttpServletRequest().getParameter("en");
		String fn = Struts2Utils.getHttpServletRequest().getParameter("fn");
		List list = new ArrayList();
		// ��ȡExcel�ĵ���·��
		try {
			// ������Excel�������ļ�������
			HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(fn));
			// ��Excel�ĵ��У���һ�Ź������ȱʡ������0
			// �����Ϊ��HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFSheet sheet = wookbook.getSheet("Sheet1");
			// ��ȡ��Excel�ļ��е���������
			int rows = sheet.getPhysicalNumberOfRows();
			// ������
			for (int i = 0; i < rows; i++) {
				// ��ȡ���϶˵�Ԫ��
				HSSFRow row = sheet.getRow(i);
				// �в�Ϊ��
				if (row != null) {
					// ��ȡ��Excel�ļ��е����е���
					int cells = row.getPhysicalNumberOfCells();
					String value = "";
					// ������
					for (int j = 0; j < cells; j++) {
						// ��ȡ���е�ֵ
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
					if ("Student".equals(en)) {
						Student entity = new Student();
						entity.setStunum(val[0]);
						entity.setStuname(val[1]);
						entity.setStusex(val[2]);
						entity.setStubirth(val[3]);
						entity.setStuadim(val[4]);
						entity.setStumajor(val[5]);
						entity.setStuclass(val[6]);
						entity.setStucollege(val[7]);
						
						// TestMethod method = new TestMethod();
						// method.Add(entity);
						if(this.adminCommonService.findById(Student.class, entity.getStunum())==null){
							this.adminCommonService.sava(entity);
							list.add(entity);
							
						}
						result.put("msg", "Student");
					}if("Course".equals(en)){
						Course c = new Course();
						Teacher t = new Teacher();
						c.setCno(val[0]);
						c.setCname(val[1]);
						t.setTenum(val[2]);
						c.setTeacher(t);
						c.setCtype(val[3]);
						c.setCtime(val[4]);
						c.setCplace(val[5]);
						c.setChours(val[6]);
						c.setCcredit(val[6]);
						
						if(this.adminCommonService.findById(Course.class, c.getCno())==null){
							if(this.adminCommonService.findById(Teacher.class, c.getTeacher().getTenum())!=null){
							    this.adminCommonService.sava(c);
							    list.add(c);
							}
						}
						result.put("msg", "Course");
					}if("Teacher".equals(en)){
						Teacher t = new Teacher();
						t.setTenum(val[0]);
						t.setTename(val[1]);
						t.setTesex(val[2]);
						t.setTetitle(val[3]);
						t.setTemajor(val[4]);
						
						if(this.adminCommonService.findById(Teacher.class, t.getTenum())==null){
							this.adminCommonService.sava(t);
							list.add(t);
						}
						result.put("msg", "Teacher");
					}
				}
			}
            result.put("list", list);
			result.put("success", true);
			 UserLog ul = new UserLog(Goals.getAName(), Goals.Log_Type_IMPORT, "��Ϣ����ɹ�", GetIpUtil.getIpAddr(), GetAgentUtil.getOperaSystem(), GetAgentUtil.getBrowser(), new Date());
			 this.adminCommonService.sava(ul);
			 logger.info(Goals.getAName()+":��Ϣ����ɹ�");
			System.gc();
			return "JSONRESULT";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result.put("success", false);
			UserLog ul = new UserLog(Goals.getAName(), Goals.Log_Type_IMPORT, "��Ϣ����ʧ��", GetIpUtil.getIpAddr(), GetAgentUtil.getOperaSystem(), GetAgentUtil.getBrowser(), new Date());
			 this.adminCommonService.sava(ul);
			 logger.error(Goals.getAName()+":��Ϣ����ʧ��"+" cause: "+Goals.getExceptionMessage(e));
			System.gc();
			return "JSONRESULT";
		} catch (IOException e) {
			e.printStackTrace();
			result.put("success", false);
			UserLog ul = new UserLog(Goals.getAName(), Goals.Log_Type_IMPORT, "��Ϣ����ʧ��", GetIpUtil.getIpAddr(), GetAgentUtil.getOperaSystem(), GetAgentUtil.getBrowser(), new Date());
			 this.adminCommonService.sava(ul);
			 logger.error(Goals.getAName()+":��Ϣ����ʧ��"+" cause: "+Goals.getExceptionMessage(e));
			System.gc();
			return "JSONRESULT";
		}

	}

	public String TestMethod() throws Exception{
		String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
		InputStream is = new FileInputStream(file);
		System.out.println(path);
		SaveFile(file);
		File file = new File(path, this.getFileFileName());
			OutputStream os = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int bs = 0;
			while ((bs = is.read(b)) > 0) {
				os.write(b, 0, bs);
			}
			is.close();
			os.close();
			result.put("message", "�ϴ��ɹ�����������");
			UserLog ul = new UserLog(Goals.getAName(), Goals.Log_Type_UPLOAD, "�ļ��ϴ��ɹ�", GetIpUtil.getIpAddr(), GetAgentUtil.getOperaSystem(), GetAgentUtil.getBrowser(), new Date());
			 this.adminCommonService.sava(ul);
			 logger.info(Goals.getAName()+":��Ϣ�ϴ��ɹ�");
			System.gc();
			return "JSONRESULT";
	}
	
	public void SaveFile(File file1) throws Exception{
		String path = "C:/fakepath"; 
		InputStream is = new FileInputStream(file1);
		System.out.println(path);
		File file = new File(path, this.getFileFileName());
			OutputStream os = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int bs = 0;
			while ((bs = is.read(b)) > 0) {
				os.write(b, 0, bs);
			}
			is.close();
			os.close();
	}
	
	
	private File file;
	private String fileFileName;
	private String fileContentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	
	
	
}
