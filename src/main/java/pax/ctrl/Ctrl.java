package pax.ctrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class Ctrl {
	
	@RequestMapping("/repositories/{user}/{repo}")
	public String fetchRepo(@PathVariable String user, @PathVariable String repo) throws IOException
	{
		try {
			URL url = new URL("https://api.github.com/repos/"+user+"/"+repo);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
			conn.setRequestMethod("GET");
			Scanner sc = new Scanner(url.openStream());
			String inline="";
			while(sc.hasNext())
			{
			inline+=sc.nextLine();
			}
			sc.close();
			JsonParser gson = new JsonParser();
			JsonElement element = gson.parse(inline);
			String name,descr,cloneurl,date;
			int stars;
			if (element.isJsonObject()) {
	            JsonObject jobj = element.getAsJsonObject();
	            
	            name=jobj.get("full_name").getAsString(); 
	            descr=(jobj.get("description") instanceof JsonNull) ? "" : jobj.get("description").getAsString();
	            cloneurl=jobj.get("clone_url").getAsString();
	            date=jobj.get("created_at").getAsString();
	            stars=jobj.get("stargazers_count").getAsInt();  
	            
	            JsonObject obj = new JsonObject();
	            obj.addProperty("full_name", name);
	            obj.addProperty("description", descr);
	            obj.addProperty("cloneUrl", cloneurl);
	            obj.addProperty("stars", stars);
	            obj.addProperty("createdAt", date);
	           
	            Gson gsn = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	            return gsn.toJson(obj);
	            
	        }
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			return "Requested user or repository does not exist.";
		}
		return "Something went wrong";
	}

}
