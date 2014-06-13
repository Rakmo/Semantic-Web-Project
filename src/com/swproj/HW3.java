package com.swproj;

/** Aim: SW HW 3 - SPARQL Queries 
 * @author Omkar Kannav
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class HW3 {

	class Tuple
	{
		private String state;
		private String county;
		private String value;
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	class StatewiseData
	{
		private String state;
		private double sum;
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		public double getSum() {
			return sum;
		}
		public void setSum(double sum) {
			this.sum = sum;
		}	
		
		public List<StatewiseData> getStatewiseSum(ArrayList<Tuple> tuples)
		{
			String tempState = "";
			double sum = 0;
			
			List<StatewiseData> result = new ArrayList<StatewiseData>();
			
			for(int i=0; i<tuples.size(); i++)
			{
				Tuple temp = tuples.get(i);
				
				if(i==0)
				{
					tempState = temp.getState();
					sum = Double.parseDouble(temp.getValue());
				}
				else
				{
					if(tempState.equals(temp.getState()))
					{
						sum += Double.parseDouble(temp.getValue());
					}
					else
					{
						StatewiseData tempData = new StatewiseData();						
						tempData.setState(tempState);
						tempData.setSum(sum);
						result.add(tempData);
						tempState = temp.getState();
						sum = Double.parseDouble(temp.getValue());
						
						//tempState = temp.getState();
						
					}
				}
			}
			
			StatewiseData tempData = new StatewiseData();						
			tempData.setState(tempState);
			tempData.setSum(sum);
			result.add(tempData);
			
			return result;
			
		}
		
		
		public List<StatewiseData> getStatewiseCounties(String state, ArrayList<Tuple> tuples)
		{
			List<StatewiseData> result = new ArrayList<StatewiseData>();
			
			for(int i=0; i<tuples.size(); i++)
			{
				Tuple temp = tuples.get(i);
				
				if(state.equals(temp.getState()))
				{
						StatewiseData tempData = new StatewiseData();
						tempData.setState(temp.getCounty());
						tempData.setSum(Double.parseDouble(temp.getValue()));
						result.add(tempData);			
				}
			}
			return result;
	}

	}
	
	static String dataset1154NameSpace = "http://logd.tw.rpi.edu/source/data-gov/dataset/1154/version/1st-anniversary";
	static String dataset1155NameSpace = "http://logd.tw.rpi.edu/source/data-gov/dataset/1155/version/1st-anniversary";
	static Model model = null;

	public static List<StatewiseData> mainDesign(String dataset, String attr) {

		InputStream in = null;

		try {
			 File f = new File("./");
			 
			   System.out.println("---------> "+f.getCanonicalFile());
			   
			   String ls = "E://eclipse k jee//ek jee workspace//SemanticWebProject";
			   
			   if(dataset.equals("dataset1154"))
				   	in = new FileInputStream(new File(ls+"//dataset1154.rdf"));
			   else
				   in = new FileInputStream(new File(ls+"//dataset1155.rdf"));

		  

		// Create an empty in-memory model and populate it from the graph
		//model = ModelFactory.createMemModelMaker().createModel(null);
		model = ModelFactory.createOntologyModel();
		 if(dataset.equals("dataset1154"))
			 model.read(in,dataset1154NameSpace); // null base URI, since model URIs are absolute
		 else
			 model.read(in,dataset1155NameSpace); // null base URI, since model URIs are absolute
		
		in.close();


	   } catch (Exception e) {

          e.printStackTrace();
       }

		String queryString = new String();
		
		if(dataset.equals("dataset1154"))
		{
		queryString =
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> "
				+ "prefix xsd2: <http://www.w3.org/TR/xmlschema-2/#> "
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
				+ "prefix pmlp: <http://inference-web.org/2.0/pml-provenance.owl#> "
				+ "prefix pmlj: <http://inference-web.org/2.0/pml-justification.owl#> "
				+ "prefix tw_service: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/> "
				+ "prefix tw_converter: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/csv2rdf4lod/version/2011-Mar-01/> "
				+ "prefix provenance: <http://logd.tw.rpi.edu/source/data-gov/provenance/1154/version/1st-anniversary/conversion/raw/> "
				+ "prefix conversion: <http://purl.org/twc/vocab/conversion/> "
				+ "prefix ov: <http://open.vocab.org/terms/> "
				+ "prefix dcterms: <http://purl.org/dc/terms/> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
				+ "prefix void: <http://rdfs.org/ns/void#> "
				+ "prefix doap: <http://usefulinc.com/ns/doap#> "
				+ "prefix scovo: <http://purl.org/NET/scovo#> "
				+ "prefix sdmx: <http://purl.org/linked-data/cube#> "
				+ "prefix vann: <http://purl.org/vocab/vann/> "
				+ "prefix data-gov_vocab: <http://logd.tw.rpi.edu/source/data-gov/vocab/> "
				+ "prefix base_vocab: <http://logd.tw.rpi.edu/vocab/> "
				+ "prefix ds1154_vocab: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/vocab/> "
				+ "prefix raw: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/vocab/raw/> "
				+ "prefix ds1154: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/version/1st-anniversary/> "
				+ "prefix ds1154_global_value: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/> "
				+ "prefix sioc: <http://rdfs.org/sioc/ns#> " +

				"SELECT ?state ?county ?value" +
				" WHERE {" +
				"      ?s raw:State ?state ."
				+ "		?s raw:County ?x ."
				+ " ?x raw:Name ?county ."
				+ "?x raw:"+attr+" ?value "
				+ "    } ";
		}
		else
		{
		queryString =
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> "
				+ "prefix xsd2: <http://www.w3.org/TR/xmlschema-2/#> "
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
				+ "prefix pmlp: <http://inference-web.org/2.0/pml-provenance.owl#> "
				+ "prefix pmlj: <http://inference-web.org/2.0/pml-justification.owl#> "
				+ "prefix tw_service: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/> "
				+ "prefix tw_converter: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/csv2rdf4lod/version/2011-Mar-01/> "
				+ "prefix provenance: <http://logd.tw.rpi.edu/source/data-gov/provenance/1154/version/1st-anniversary/conversion/raw/> "
				+ "prefix conversion: <http://purl.org/twc/vocab/conversion/> "
				+ "prefix ov: <http://open.vocab.org/terms/> "
				+ "prefix dcterms: <http://purl.org/dc/terms/> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
				+ "prefix void: <http://rdfs.org/ns/void#> "
				+ "prefix doap: <http://usefulinc.com/ns/doap#> "
				+ "prefix scovo: <http://purl.org/NET/scovo#> "
				+ "prefix sdmx: <http://purl.org/linked-data/cube#> "
				+ "prefix vann: <http://purl.org/vocab/vann/> "
				+ "prefix data-gov_vocab: <http://logd.tw.rpi.edu/source/data-gov/vocab/> "
				+ "prefix base_vocab: <http://logd.tw.rpi.edu/vocab/> "
				+ "prefix ds1155_vocab: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/vocab/> "
				+ "prefix raw: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/vocab/raw/> "
				+ "prefix ds1155: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/version/1st-anniversary/> "
				+ "prefix ds1155_global_value: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/> "
				+ "prefix sioc: <http://rdfs.org/sioc/ns#> " +

				"SELECT ?state ?county ?value" +
				" WHERE {" +
				"      ?s raw:State ?state ."
				+ "		?s raw:congressional_district ?x ."
				+ " ?x raw:congressional_district ?county ."
				+ "?x raw:"+attr+" ?value "
				+ "    } ";
		}
		
		System.out.println("Issuing query #1...."+queryString);
		HW3 hw = new HW3();
		//call this method to execute your SPARQL query
		//issueSPARQL(queryString);
		
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet response = qe.execSelect();
		
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();

		while(response.hasNext())
		{
			
			Tuple tempTuple = hw.new Tuple();
			QuerySolution tempSolution = response.nextSolution();
			tempTuple.setState(tempSolution.get("?state").toString());
			tempTuple.setCounty(tempSolution.get("?county").toString());
			tempTuple.setValue(tempSolution.get("?value").toString());
			
			tuples.add(tempTuple);
			/*QuerySolution temp = response.nextSolution();
			System.out.println("\t"+temp.get("?state")+"\t"+temp.get("?county")+"\t"+temp.get("?value"));*/
			System.out.println("\t"+tempSolution.get("?state").toString()+"\t"+tempSolution.get("?county").toString()+"\t"+tempSolution.get("?value").toString());
		}
		
		
		List<StatewiseData> statewiseSum = hw.new StatewiseData().getStatewiseSum(tuples);
		
		return statewiseSum;
		
		/*System.out.println("*** Result ***");
		for(int i=0; i<statewiseSum.size(); i++)
		{
			StatewiseData temp = statewiseSum.get(i);
			System.out.println("\t"+temp.getState()+"\t"+temp.getSum());
		}
		return response;*/

	}

	public static  List<StatewiseData> mainDesign(String dataset, String attr, String state) {

		InputStream in = null;

		try {
			 File f = new File("./");
			 
			   System.out.println("---------> "+f.getCanonicalFile());
			   
			   String ls = "C://Users//Omkya//workspace//ekWorkspace//SWProject";
			   
			   if(dataset.equals("dataset1154"))
				   	in = new FileInputStream(new File(ls+"//dataset1154.rdf"));
			   else
				   in = new FileInputStream(new File(ls+"//dataset1155.rdf"));

		  

		// Create an empty in-memory model and populate it from the graph
		//model = ModelFactory.createMemModelMaker().createModel(null);
		model = ModelFactory.createOntologyModel();
		 if(dataset.equals("dataset1154"))
			 model.read(in,dataset1154NameSpace); // null base URI, since model URIs are absolute
		 else
			 model.read(in,dataset1155NameSpace); // null base URI, since model URIs are absolute
		
		in.close();


	   } catch (Exception e) {

          e.printStackTrace();
       }

		String queryString = new String();
		
		if(dataset.equals("dataset1154"))
		{
		queryString =
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> "
				+ "prefix xsd2: <http://www.w3.org/TR/xmlschema-2/#> "
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
				+ "prefix pmlp: <http://inference-web.org/2.0/pml-provenance.owl#> "
				+ "prefix pmlj: <http://inference-web.org/2.0/pml-justification.owl#> "
				+ "prefix tw_service: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/> "
				+ "prefix tw_converter: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/csv2rdf4lod/version/2011-Mar-01/> "
				+ "prefix provenance: <http://logd.tw.rpi.edu/source/data-gov/provenance/1154/version/1st-anniversary/conversion/raw/> "
				+ "prefix conversion: <http://purl.org/twc/vocab/conversion/> "
				+ "prefix ov: <http://open.vocab.org/terms/> "
				+ "prefix dcterms: <http://purl.org/dc/terms/> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
				+ "prefix void: <http://rdfs.org/ns/void#> "
				+ "prefix doap: <http://usefulinc.com/ns/doap#> "
				+ "prefix scovo: <http://purl.org/NET/scovo#> "
				+ "prefix sdmx: <http://purl.org/linked-data/cube#> "
				+ "prefix vann: <http://purl.org/vocab/vann/> "
				+ "prefix data-gov_vocab: <http://logd.tw.rpi.edu/source/data-gov/vocab/> "
				+ "prefix base_vocab: <http://logd.tw.rpi.edu/vocab/> "
				+ "prefix ds1154_vocab: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/vocab/> "
				+ "prefix raw: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/vocab/raw/> "
				+ "prefix ds1154: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/version/1st-anniversary/> "
				+ "prefix ds1154_global_value: <http://logd.tw.rpi.edu/source/data-gov/dataset/1154/> "
				+ "prefix sioc: <http://rdfs.org/sioc/ns#> " +

				"SELECT ?state ?county ?value" +
				" \nWHERE {" +
				" \n?s raw:State ?state ."
				+ "	\n?s raw:County ?x ."
				+ " \n?x raw:Name ?county ."
				+ " \n?x raw:"+attr+" ?value "
				+ "    } ";
		}
		else
		{
		queryString =
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> "
				+ "prefix xsd2: <http://www.w3.org/TR/xmlschema-2/#> "
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> "
				+ "prefix pmlp: <http://inference-web.org/2.0/pml-provenance.owl#> "
				+ "prefix pmlj: <http://inference-web.org/2.0/pml-justification.owl#> "
				+ "prefix tw_service: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/> "
				+ "prefix tw_converter: <http://logd.tw.rpi.edu/source/tw-rpi-edu/service/csv2rdf4lod/version/2011-Mar-01/> "
				+ "prefix provenance: <http://logd.tw.rpi.edu/source/data-gov/provenance/1154/version/1st-anniversary/conversion/raw/> "
				+ "prefix conversion: <http://purl.org/twc/vocab/conversion/> "
				+ "prefix ov: <http://open.vocab.org/terms/> "
				+ "prefix dcterms: <http://purl.org/dc/terms/> "
				+ "prefix foaf: <http://xmlns.com/foaf/0.1/> "
				+ "prefix void: <http://rdfs.org/ns/void#> "
				+ "prefix doap: <http://usefulinc.com/ns/doap#> "
				+ "prefix scovo: <http://purl.org/NET/scovo#> "
				+ "prefix sdmx: <http://purl.org/linked-data/cube#> "
				+ "prefix vann: <http://purl.org/vocab/vann/> "
				+ "prefix data-gov_vocab: <http://logd.tw.rpi.edu/source/data-gov/vocab/> "
				+ "prefix base_vocab: <http://logd.tw.rpi.edu/vocab/> "
				+ "prefix ds1155_vocab: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/vocab/> "
				+ "prefix raw: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/vocab/raw/> "
				+ "prefix ds1155: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/version/1st-anniversary/> "
				+ "prefix ds1155_global_value: <http://logd.tw.rpi.edu/source/data-gov/dataset/1155/> "
				+ "prefix sioc: <http://rdfs.org/sioc/ns#> " +

				"SELECT ?state ?county ?value" +
				" WHERE {" +
				"      ?s raw:State ?state ."
				+ "		?s raw:congressional_district ?x ."
				+ " ?x raw:congressional_district ?county ."
				+ "?x raw:"+attr+" ?value "
				+ "    } ";
		}
		
		System.out.println("Issuing query #1...."+queryString);
		HW3 hw = new HW3();
		//call this method to execute your SPARQL query
		//issueSPARQL(queryString);
		
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet response = qe.execSelect();

		//System.out.println("executed");
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		//System.out.println("tuples created");
		while(response.hasNext())
		{
			Tuple tempTuple = hw.new Tuple();
			QuerySolution tempSolution = response.nextSolution();
			tempTuple.setState(tempSolution.get("?state").toString());
			tempTuple.setCounty(tempSolution.get("?county").toString());
			tempTuple.setValue(tempSolution.get("?value").toString());
			
			tuples.add(tempTuple);
			//QuerySolution temp = response.nextSolution();
			System.out.println("\t"+tempSolution.get("?state").toString()+"\t"+tempSolution.get("?county").toString()+"\t"+tempSolution.get("?value").toString());
			
		}
		
		//System.out.println("before statewise");
		List<StatewiseData> statewiseCounties = hw.new StatewiseData().getStatewiseCounties(state, tuples);
		
		return statewiseCounties;
		
		/*System.out.println("*** Result ***");
		for(int i=0; i<statewiseSum.size(); i++)
		{
			StatewiseData temp = statewiseSum.get(i);
			System.out.println("\t"+temp.getState()+"\t"+temp.getSum());
		}
		return response;*/

	}
	
/*	public static void main(String args[])
	{
		//List<StatewiseData> statewiseCounties = mainDesign("dataset1154", "total_expenditure", "IOWA");
		List<StatewiseData> statewiseCounties = mainDesign("dataset1154", "total_expenditure");
		for(StatewiseData s : statewiseCounties)
		{
			System.out.println(s.getState()+"\t"+s.getSum());
		}
	}*/

}
  

