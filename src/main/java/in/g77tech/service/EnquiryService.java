package in.g77tech.service;

import java.util.List;

import in.g77tech.dto.DashboardResponse;
import in.g77tech.dto.EnquiryFormDto;
import in.g77tech.dto.EnquirySearchCriteria;

public interface EnquiryService {
	
	public List<String> getCourseNames();
	public List<String> getEnqStatus();
	public DashboardResponse getDashboardData(Integer userId);
	public String addEnquiry(EnquiryFormDto formDto);
	
	public List<EnquiryFormDto> getEnquiries(Integer userId, EnquirySearchCriteria criteria);
	public EnquiryFormDto getEnquiry(Integer enqId);
}
