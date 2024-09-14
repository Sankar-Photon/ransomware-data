package com.example.ransomware.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;


@Service
public class RansomwareService {    
    @Autowired
    private ElasticsearchClient elasticsearchClient;    
    private static final String INDEX_NAME = "ransomware_data";

    @SuppressWarnings("unchecked")
    public String createRansomware (Map<String, Object> ransomware) {        
        String id = String.join("~", (List<String>) ransomware.get("name"));
        IndexRequest<Map<String, Object>> request = new IndexRequest.Builder<Map<String, Object>>().index(INDEX_NAME)
                                                        .id(id).document(ransomware).build();
        try {
            elasticsearchClient.index(request);
        } catch (IOException e) {            
            throw new RuntimeException("Failed to index ransomware", e);
        }       
        return id;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, Object> getRansomwareById(String id) {        
        try {           
            GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id(id).build();
            GetResponse<Map> response = elasticsearchClient.get(getRequest, Map.class);
            return response.source();
        } catch(IOException e) {            
            throw new RuntimeException("Failed to retrieve ransomware", e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getAllRansomware(int page, int size) {        
        SearchRequest searchRequest = new SearchRequest.Builder().index(INDEX_NAME).from(page * size).size(size).build();        
        try {
            SearchResponse<Map> searchResponse = elasticsearchClient.search(searchRequest, Map.class);
            return searchResponse.hits().hits().stream()
                        .map(hit -> (Map<String,Object>) hit.source()).collect(Collectors.toList());
        }
        catch (IOException e) {            
            throw new RuntimeException("Failed to retrieve ransomware list", e);
        }
    }

    public void updateRansomware (String id, Map<String, Object> ransomware) {
        try {
            IndexRequest<Map<String, Object>> request = new IndexRequest.Builder<Map<String, Object>>()
                                                            .index(INDEX_NAME).id(id).document(ransomware).build();
            elasticsearchClient.index(request);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update ransomware", e);
        }
    }

    public void deleteRansomware (String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest.Builder()
                                            .index(INDEX_NAME).id(id).build();
            elasticsearchClient.delete(deleteRequest);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete ransomware", e);
        }
    }
}