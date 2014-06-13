package com.swproj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.swproj.HW3.StatewiseData;

/**
 * Servlet implementation class ReportHandlerServlet
 */
public class ReportHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReportHandlerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String selectedState = request.getParameter("state");
		String selectedAttr = request.getParameter("attribute");
		String dataset = request.getParameter("dataset").trim();
		String rType = request.getParameter("rType");
				
		SelectLists lBean = new SelectLists();
		selectedAttr = lBean.getActualParameter(selectedAttr.trim());
		String cd = "County";
		if(dataset.equals("dataset1155"))
			cd = "District";
			
	
		if(rType.equals("chart")){		
			List<StatewiseData> statewiseData = HW3.mainDesign(dataset, selectedAttr, selectedState);
							
			out.println("<html><head><title>Report - Chart</title><script type='text/javascript' src='https://www.google.com/jsapi'></script>"
				+ "<script type='text/javascript'>"
				+ "google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});"
				+ "google.setOnLoadCallback(drawChart);"
				+ "function drawChart() {"
				+ "var data = google.visualization.arrayToDataTable(["
				+ "['"+cd+"', '"+selectedAttr+"'],");
				for(int i=0; i<statewiseData.size(); i++)
				{
					StatewiseData tempSD = statewiseData.get(i);
				 out.println("['"+tempSD.getState()+"', "+tempSD.getSum()+"]");
				 System.out.println("['"+tempSD.getState()+"', "+tempSD.getSum()+"]");
				 if(i<statewiseData.size()-1)
					 out.print(",");
				}
						out.println("		        ]);"
						+ "		        var options = {"
						+ "		          title: '"+selectedAttr.toUpperCase()+" vs. "+cd+"',"
								+ "		          hAxis: {title: '"+cd+"', titleTextStyle: {color: 'blue'}}"
								+ "		        };"
								+ "var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));"
								+ "chart.draw(data, options);"
								+ "}</script></head><body>"
								+ "<br><b>Dataset:</b>"+dataset+ "&nbsp <b>State:</b> "+selectedState
								+ "&nbsp <b>Attribute:</b> "+selectedAttr
								+ "&nbsp <b>Report Type:</b> "+rType
								+ "<br><hr><div id='chart_div' style=\"width: 1600px; height: 600px;\"></div></body></html>");
		}
		else
		{
			List<StatewiseData> statewiseData = HW3.mainDesign(dataset, selectedAttr);
			out.println("<html><head><title>Report - Map</title><script type='text/javascript' src='https://www.google.com/jsapi'></script>"
				+ "  <script type='text/javascript'> "
				+ "   google.load('visualization', '1', {'packages': ['geomap']}); "
				+ "   google.setOnLoadCallback(drawMap); "
				+ "    function drawMap() { "
				+ "      var data = google.visualization.arrayToDataTable(["
				+ "        ['State', '"+selectedAttr+"'], ");
				for(int i=0; i<statewiseData.size(); i++)
				{
					StatewiseData tempSD = statewiseData.get(i);
				 out.println("['"+tempSD.getState()+"', "+tempSD.getSum()+"]");
				 
				 if(i<statewiseData.size()-1)
					 out.print(",");
				}
				out.println("      ]);"
				+ "      var options = {};       "
				+ "options['region'] = 'US';"
				+ "      options['colors'] = [0x0099FF, 0x000033];"
				+ "      var container = document.getElementById('map_canvas');"
				+ "      var geomap = new google.visualization.GeoMap(container);"
				+ "      geomap.draw(data, options);"
				+ "    };  </script></head> <body>");
		
				out.println("<br><b><u>Selected Parameters</u></b><br><b>Dataset:</b> "+dataset
								+ "&nbsp <b>Attribute:</b> "+selectedAttr
								+ "&nbsp <b>Report Type:</b> "+rType+"<br><hr><div id='map_canvas' style=\"width: 1600px; height: 600px;\"></div></body> </html>");
		
		}
	}

}
