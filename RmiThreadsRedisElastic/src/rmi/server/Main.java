package rmi.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class Main
{
    public static void main(String[] args) throws UnknownHostException
    {
	String jsonSource = "{\"user\" : \"test2\", \"group\" : \"test2\", \"dateOfBirth\" : 1723454321}";
	Settings settings = Settings.builder()
	        .put("client.transport.ignore_cluster_name", true).build();
	@SuppressWarnings("resource")
	Client client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	createDocument("students-ua", "group", jsonSource, client);
	
    }
    
    public static IndexResponse createDocument(String index, String type, String jsonSource, Client client) {
	if (client != null) {
		IndexResponse response = client.prepareIndex(index, type).setSource(jsonSource).get();
		return response;
	}
	return null;
}
}
