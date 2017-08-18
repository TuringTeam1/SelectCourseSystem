package cn.edu.gdin.Aaction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import cn.edu.gdin.Aservice.AdminCommonService;
import cn.edu.gdin.entity.SelectCourse;
import cn.edu.gdin.entity.Student;

import com.opensymphony.xwork2.ActionSupport;

public class ChartLAction extends ActionSupport{
	private AdminCommonService adminCommonService;
	public void setAdminCommonService(AdminCommonService adminCommonService) {
		this.adminCommonService = adminCommonService;
	}

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		DefaultCategoryDataset chartDate = new DefaultCategoryDataset(); 
		List<Student> list = this.adminCommonService.findAll(Student.class);
		if(!list.isEmpty()){
			for(Student s : list){
				if(!this.adminCommonService.getByParamData(SelectCourse.class, "id.student.stunum", s.getStunum()).isEmpty()){
					 chartDate.addValue(this.adminCommonService.getGDTwoParam(SelectCourse.class, "id.student.stunum", s.getStunum()).size(), "ѡ����ô��ΰ�", s.getStuname());
				}
			}
		}
		JFreeChart loanRemainChart=ChartFactory.createLineChart(  
                "ÿ��ѡ��ͳ�����",   
                "ѧ������",  
                "��ѡ����",  
                chartDate,  
                PlotOrientation.VERTICAL,  
                false,  
                false,  
                false);  
		 loanRemainChart.setBorderVisible(true);  
         Font xfont=new Font("����",Font.PLAIN,12);    
         Font yfont=new Font("����",Font.PLAIN,12);  
         Font font= new Font("����",Font.PLAIN,12);  //�ײ�  
         Font titleFont = new Font("����",Font.BOLD,25);  //ͼƬ����  
         loanRemainChart.getTitle().setFont(titleFont); // ���ñ�������    
         loanRemainChart.setBackgroundPaint(Color.WHITE);// ���ñ���ɫ     
         //��ȡ��ͼ������    
         CategoryPlot loanRemianPlot = loanRemainChart.getCategoryPlot();    
         loanRemianPlot.setBackgroundPaint(Color.BLUE); // ���û�ͼ������ɫ    
         loanRemianPlot.setRangeGridlinePaint(Color.WHITE); // ����ˮƽ���򱳾�����ɫ    
         loanRemianPlot.setRangeGridlinesVisible(true);// �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue    
         loanRemianPlot.setDomainGridlinePaint(Color.WHITE); // ���ô�ֱ���򱳾�����ɫ    
         loanRemianPlot.setDomainGridlinesVisible(true); // �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse    


         CategoryAxis loanRemainDomainAxis = loanRemianPlot.getDomainAxis();       
         loanRemainDomainAxis.setLabelFont(font); // ���ú�������    
         loanRemainDomainAxis.setTickLabelFont(font);// ������������ֵ����    
         loanRemainDomainAxis.setLowerMargin(0.01);// ��߾� �߿����    
         loanRemainDomainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ�����ݿ����������ᡣ    
         loanRemainDomainAxis.setMaximumCategoryLabelLines(2);    

         ValueAxis loanRemainRangeAxis = loanRemianPlot.getRangeAxis();    
         loanRemainRangeAxis.setLabelFont(font);     
         loanRemainRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y����ʾ����    
         loanRemainRangeAxis.setAutoRangeMinimumSize(1);   //��С���    
         loanRemainRangeAxis.setUpperMargin(0.18);//�ϱ߾�,��ֹ����һ�����ݿ����������ᡣ       
         loanRemainRangeAxis.setLowerBound(0);   //��Сֵ��ʾ0    
         loanRemainRangeAxis.setAutoRange(false);   //���Զ�����Y������    
         loanRemainRangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // ���������Ǵ�С    
         loanRemainRangeAxis.setTickMarkPaint(Color.BLACK);     // ������������ɫ    


         // ��ȡ���߶���    
         LineAndShapeRenderer loanRemainRenderer = (LineAndShapeRenderer) loanRemianPlot.getRenderer();    
         loanRemainRenderer.setBaseItemLabelsVisible(true);  
         loanRemainRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(    
                 ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));    
         loanRemainRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  //��ʾ�ڵ����ֵ  
         loanRemainRenderer.setBaseItemLabelPaint(new Color(102, 102, 102));  
           
         BasicStroke realLine = new BasicStroke(1.8f); // ����ʵ��    
         // ��������    
         float dashes[] = { 5.0f };     
         BasicStroke brokenLine = new BasicStroke(2.2f, // ������ϸ    
                 BasicStroke.CAP_ROUND, // �˵���    
                 BasicStroke.JOIN_ROUND, // �۵���    
                 8f, dashes, 0.6f);     
         for (int i = 0; i < chartDate.getRowCount(); i++) {    
             if (i % 2 == 0)    
                 loanRemainRenderer.setSeriesStroke(i, realLine); // ����ʵ�߻���    
             else    
                 loanRemainRenderer.setSeriesStroke(i, brokenLine); // �������߻���    
         }    

        
         HttpServletResponse response=ServletActionContext.getResponse();
    	response.setContentType("image/");
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f, loanRemainChart, 800, 650, null);
			response.getOutputStream().close();
			/*-------��ͼƬ��������--------*/
			FileOutputStream fos = null;  
			String imagePath = "C://Users//Administrator//Desktop//chart3.jpg";
			ChartRenderingInfo info = new ChartRenderingInfo(); 
			try {
				fos = new FileOutputStream(imagePath);
				//��ͳ��ͼ�������JPG�ļ� 
				ChartUtilities.writeChartAsJPEG( 
					fos, //������ĸ������ 
					1, //JPEGͼƬ��������0~1֮�� 
					loanRemainChart, //ͳ��ͼ����� 
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
			return null;
	}
}
