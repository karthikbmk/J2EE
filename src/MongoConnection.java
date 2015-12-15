import com.mongodb.MongoClient;

public class MongoConnection
{
	public static MongoClient getConnection(String IPAddress,int port)
	{
		MongoClient mongo = new MongoClient(IPAddress, port);	
		return mongo;
		
	}
}
