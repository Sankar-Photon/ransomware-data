package com.interview.ransomware_data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.json.JsonData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class RansomwareService {
    @Autowired
    private ElasticsearchClient client;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void insertRansomwareRecords() throws IOException {
        List<Map<String, Object>> jsonList = loadDataFromJson();
        System.out.println("Got data. Total records::"+jsonList.size());
        insertIntoElasticsearch(jsonList);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> loadDataFromJson() throws IOException {        
        ClassPathResource resource = new ClassPathResource("ransomware_overview.json");        
        List<Map<String, Object>> jsonList = objectMapper.readValue(resource.getInputStream(), List.class);        
        return jsonList;
    }

    @SuppressWarnings("unchecked")
    private void insertIntoElasticsearch(List<Map<String, Object>> jsonRecords) throws IOException {         BulkRequest.
        Builder bulkRequest = new BulkRequest.Builder();
        System.out.println("Bulk Operation Started");
        for (Map<String, Object> document : jsonRecords) {
            List<String> id = (List<String>) document.get("name");
            String identifier = String.join("~", id);
            bulkRequest.operations(op -> op.index(idx -> idx.index("ransomware_data")
                       .id(identifier)
                       .document(JsonData.of(document))));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest.build());
        if (bulkResponse.errors()) { 
            System.out.println("Bulk indexing failed: "+ bulkResponse.errors());
        }
        System.out.println("Bulk Operation Completed");
    }
}
