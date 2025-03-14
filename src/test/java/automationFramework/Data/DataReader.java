package automationFramework.Data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonData() throws Exception {

		//read json to String
		String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//automationFramework//Data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		//String to Hash Map - Jackson DataBind dependency
		ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		 return data;
	}
}
