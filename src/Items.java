import java.util.ArrayList;
import java.util.HashMap;

public class Items 
{
	protected HashMap<String,String> itemAndItsDesc;
	protected HashMap<String,String> itemAndItsprice;
	protected HashMap<String,String> itemAndItsOrderCount;
	
	public ArrayList<HashMap<String,String>> populateItems()
	{
		ArrayList<HashMap<String, String>> listOfMaps = new ArrayList<HashMap<String, String>>();
		
		itemAndItsDesc = new HashMap<String,String>();
		itemAndItsprice = new HashMap<String,String>();
		itemAndItsOrderCount = new HashMap<String,String>();
		
		itemAndItsDesc.put("XBox_Original", "XBox Original From Microsoft");
		itemAndItsDesc.put("xbox_1", "XBox One From Microsoft");
		itemAndItsDesc.put("xbox_360", "XBox 360 From Microsoft");
		itemAndItsDesc.put("PS2", "PlayStation 2 From Sony");
		itemAndItsDesc.put("PS3", "PlayStation 3 From Sony");
		itemAndItsDesc.put("PS4", "PlayStation 4 From Sony");
		itemAndItsDesc.put("NintendoWii", "Nintendo Wii From Nintendo");
		itemAndItsDesc.put("NintendoWiiU", "Nintendo Wii-U From Nintendo");
		
		itemAndItsDesc.put("EA", "EA Games - UFC Game");
		itemAndItsDesc.put("ACT", "Activision game - Grand Theft Auto - 2");
		itemAndItsDesc.put("T2", "Take two Interactive Game");
		
		itemAndItsprice.put("XBox_Original", "50");
		itemAndItsprice.put("xbox_1", "100");
		itemAndItsprice.put("xbox_360", "200");
		itemAndItsprice.put("PS2", "250");
		itemAndItsprice.put("PS3", "300");
		itemAndItsprice.put("PS4", "400");
		itemAndItsprice.put("NintendoWii", "500");
		itemAndItsprice.put("NintendoWiiU", "600");
		
		itemAndItsprice.put("EA", "45");
		itemAndItsprice.put("ACT", "86");
		itemAndItsprice.put("T2", "99");
		
		itemAndItsOrderCount.put("XBox_Original", "0");
		itemAndItsOrderCount.put("xbox_1", "0");
		itemAndItsOrderCount.put("xbox_360", "0");
		itemAndItsOrderCount.put("PS2", "0");
		itemAndItsOrderCount.put("PS3", "0");
		itemAndItsOrderCount.put("PS4", "0");
		itemAndItsOrderCount.put("NintendoWii", "0");
		itemAndItsOrderCount.put("NintendoWiiU", "0");		
		
		itemAndItsOrderCount.put("EA", "0");
		itemAndItsOrderCount.put("ACT", "0");
		itemAndItsOrderCount.put("T2", "0");
		
		listOfMaps.add(itemAndItsDesc);
		listOfMaps.add(itemAndItsprice);
		listOfMaps.add(itemAndItsOrderCount);
		
		return listOfMaps;
	}
}
