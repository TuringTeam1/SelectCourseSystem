package cn.edu.gdin.Aaction;

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
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.gdin.demo.TCS;
import cn.edu.gdin.service.TCSCountService;



public class ChartSCAction extends ActionSupport{
	private TCSCountService tCSCountService;
	public void settCSCountService(TCSCountService tCSCountService) {
		this.tCSCountService = tCSCountService;
	}

	
	
	public String execute() throws Exception{
		 //�������
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				List<TCS> list = this.tCSCountService.admin_count();
				
				for(int i=0;i<list.size();i++){
					if(list.get(i).getStucount()!=0){
				        dataset.addValue(list.get(i).getStucount(),"�γ���", list.get(i).getCname());
					}
				}
				//����һ����״ͼ
				
				JFreeChart chart = ChartFactory.createBarChart3D("ѧ��ѡ��ͳ��ͼ", "�γ���", "ѧ����",dataset,PlotOrientation.VERTICAL, true, false, false);
				chart.setTitle(new TextTitle("ѧ��ѡ��ͳ��ͼ", new Font("����", Font.ITALIC,22)));
				// ��������ͼƬ�ı���ɫ  
	            chart.setBackgroundPaint(Color.WHITE);  
	            // ����ͼƬ�б߿�  
	            chart.setBorderVisible(true); 
				LegendTitle legend = chart.getLegend(0);
				legend.setItemFont(new Font("����", Font.BOLD, 14));
				CategoryPlot plot = (CategoryPlot) chart.getPlot();
				CategoryAxis categoryAxis = plot.getDomainAxis();
				categoryAxis.setLabelFont(new Font("����", Font.BOLD, 22));
				categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
				categoryAxis.setTickLabelFont(new Font("����", Font.BOLD, 18));
				NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();  
	            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
	            BarRenderer3D barrenderer = (BarRenderer3D) plot.getRenderer();  
	            barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));  
	            barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12)); 
	                   barrenderer.setSeriesPaint(0,Color.BLUE);
	                   
	            CategoryAxis domainAxis = plot.getDomainAxis();           
	            /*------����X�������ϵ�����-----------*/  
	            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
	            /*------����X��ı�������------------*/  
	            domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
	            /*------����Y�������ϵ�����-----------*/  
	            numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
	            /*------����Y��ı�������------------*/  
	            numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
	            /*------���������˵ײ��������������-----------*/  
	            chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("image/");
				ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1, chart, 800, 650, null);
				response.getOutputStream().close();
				/*-------��ͼƬ��������--------*/
				FileOutputStream fos = null;  
				String imagePath = "C://Users//Administrator//Desktop//chart.jpg";
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
				return null;
		}
	
}
