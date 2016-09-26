package JSONHandler;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Models.Data;

public class JsonHandler {
	
	public List<Data> getDataFromJsonString(String json_string)
	{
		try
		{
			Gson gson = new Gson();
             System.out.println(json_string);
			String myCustom_JSONResponse="{\"master\":"+json_string+"}";
			List<Data> data_lst= new ArrayList<>();


			JSONObject json_obj = new JSONObject(myCustom_JSONResponse.trim());
			JSONArray jsonArray = json_obj.getJSONArray("master");

			for (int i = 0; i < jsonArray.length(); i++)
			{

				System.out.println("JSon object "+jsonArray.getJSONObject(i).toString());
				String to_be_converted=jsonArray.getJSONObject(i).toString();
				JSONObject json_obj_thumbs = new JSONObject(to_be_converted.trim());
				JSONArray jsonArray_thumb = json_obj_thumbs.getJSONArray("thumbs");
			    String thumb_nail=jsonArray_thumb.get(0).toString();
				String title=json_obj_thumbs.getString("title");

					System.out.println("JSon object "+jsonArray_thumb.get(0).toString());

				data_lst.add(new Data(title,thumb_nail));

			}



			return data_lst;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

}
