package cn.edu.gdin.Aaction;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import cn.edu.gdin.Aservice.AdminCommonService;
import cn.edu.gdin.entity.SelectCourse;
import cn.edu.gdin.util.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

public class ChartBAction extends ActionSupport{

	private AdminCommonService adminCommonService;
	public void setAdminCommonService(AdminCommonService adminCommonService) {
		this.adminCommonService = adminCommonService;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		DefaultPieDataset dataType = new DefaultPieDataset();
		// ���ݲ��� ���ݣ�����
		List<SelectCourse> se = this.adminCommonService.findAll(SelectCourse.class);
		
		if(!se.isEmpty()){
			dataType.setValue("�޳ɼ�", this.adminCommonService.getResultTJ(SelectCourse.class, "result", 0, 0).size());
			dataType.setValue("������", this.adminCommonService.getResultTJ(SelectCourse.class, "result", 1, 59).size());
			dataType.setValue("�е�", this.adminCommonService.getResultTJ(SelectCourse.class, "result", 60, 79).size());
			dataType.setValue("����", this.adminCommonService.getResultTJ(SelectCourse.class, "result", 80, 89).size());
			dataType.setValue("����", this.adminCommonService.getResultTJ(SelectCourse.class, "result", 90, 100).size());
		}
	
		
		
		try {
			DefaultPieDataset data = dataType;
			// ������ͨ��״ͼ���� 3D ����
			// ����3D��״ͼ
			PiePlot3D plot = new PiePlot3D(data);
			JFreeChart chart = new JFreeChart(
					"�ɼ�ͳ��ͼ",            // ͼ�α���
					JFreeChart.DEFAULT_TITLE_FONT, // ��������
					plot,                          // ͼ��������
					true                           // �Ƿ���ʾͼ��
			);
			 //���ðٷֱ�  
	          PiePlot pieplot = (PiePlot) chart.getPlot();  
	          DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������  
	          NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����  
	          StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����  
	          pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�  
	          
	          //û�����ݵ�ʱ����ʾ������  
	          pieplot.setNoDataMessage("��������ʾ");  
	          pieplot.setCircular(false);  
	          pieplot.setLabelGap(0.02D);  
	        
	          pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ  
	          pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ 
	          
			// ��������ͼƬ�ı���ɫ
			chart.setBackgroundPaint(Color.PINK);
			// ����ͼƬ�б߿�
			chart.setBorderVisible(true);
			// ��������
			Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�
			Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����
			// ͼƬ����
			chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
			// �ײ�
			chart.getLegend().setItemFont(kfont);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("image/");
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
					chart, 500, 300, null);
			response.getOutputStream().close();
			/*-------��ͼƬ��������--------*/
			FileOutputStream fos = null;  
			String imagePath = "C://Users//Administrator//Desktop//chart4.jpg";
			StringBuffer str = Struts2Utils.getHttpServletRequest().getRequestURL();
			System.out.println("str:"+str);
			ChartRenderingInfo info = new ChartRenderingInfo(); 
			try {
				fos = new FileOutputStream(imagePath);
				//��ͳ��ͼ�������JPG�ļ� 
				ChartUtilities.writeChartAsJPEG( 
					fos, //������ĸ������ 
					1, //JPEGͼƬ��������0~1֮�� 
					chart, //ͳ��ͼ����� 
					800, //�� 
					600,//��
					info //ChartRenderingInfo ��Ϣ 
				);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
