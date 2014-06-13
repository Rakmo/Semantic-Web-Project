package com.swproj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectLists {		
	 
	public SelectLists(){		
	}
	
	 public List<String> getStates() {
	        List<String> list = new ArrayList<String>();	        
			list.add("ALABAMA");
			list.add("ALASKA");
			list.add("ARIZONA");
			list.add("ARKANSAS");
			list.add("CALIFORNIA");
			list.add("COLORADO");
			list.add("CONNECTICUT");
			list.add("DELAWARE");
			list.add("DISTRICT OF COLUMBIA");
			list.add("FLORIDA");
			list.add("GEORGIA");
			list.add("HAWAII");
			list.add("IDAHO");
			list.add("ILLINOIS");
			list.add("INDIANA");
			list.add("IOWA");
			list.add("KANSAS");
			list.add("KENTUCKY");
			list.add("LOUISIANA");
			list.add("MAINE");
			list.add("MARYLAND");
			list.add("MASSACHUSETTS");
			list.add("MICHIGAN");
			list.add("MINNESOTA");
			list.add("MISSISSIPPI");
			list.add("MISSOURI");
			list.add("MONTANA");
			list.add("NEBRASKA");
			list.add("NEVADA");
			list.add("NEW  HAMPSHIRE");
			list.add("NEW JERSEY");
			list.add("NEW MEXICO");
			list.add("NEW YORK");
			list.add("NORTH CAROLINA");
			list.add("NORTH DAKOTA");
			list.add("OHIO");
			list.add("OKLAHOMA");
			list.add("OREGON");
			list.add("PENNSYLVANIA");
			list.add("RHODE ISLAND");
			list.add("SOUTH CAROLINA");
			list.add("SOUTH DAKOTA");
			list.add("TENNESSEE");
			list.add("TEXAS");
			list.add("UTAH");
			list.add("VERMONT");
			list.add("VIRGINIA");
			list.add("WASHINGTON");
			list.add("WEST VIRGINIA");
			list.add("WISCONSIN");
			list.add("WYOMING");
			list.add("PUERTO RICO");
	        return list;
	    }
	
	 public List<String> getAttributes() {
	        List<String> list = new ArrayList<String>();	        
			list.add("Veteran Population");
			list.add("Total Expenditure");
			list.add("Compension Pension");
			list.add("Construction");
			list.add("Education Vocational Rehabilitation Employment");
			list.add("Loan Guaranty");
			list.add("General Operating Expenses");
			list.add("Insurance Indemnities");
			list.add("Medical Care");
			list.add("Unique Patients");
			
	        return list;
	    }

	public String getActualParameter(String selectedAttr) {
		// TODO Auto-generated method stub
		HashMap<String, String> hMap = new HashMap<String, String>();
				
		hMap.put("Veteran Population", "veteran_population");
		hMap.put("Total Expenditure", "total_expenditure");
		hMap.put("Compension Pension", "compension_pension");
		hMap.put("Construction", "construction");
		hMap.put("Education Vocational Rehabilitation Employment", "education_vocational_rehabilitation_employment");
		hMap.put("Loan Guaranty", "loan_guaranty");
		hMap.put("General Operating Expenses", "general_operating_expenses");
		hMap.put("Insurance Indemnities", "insurance_indemnities");
		hMap.put("Medical Care", "medical_care");
		hMap.put("Unique Patients", "unique_patients");
		
		return hMap.get(selectedAttr);
	}
	
	public static void main(String args[])
	{
		SelectLists lb = new SelectLists();
		System.out.println("--->"+lb.getActualParameter("Loan Guaranty"));
	}


}
