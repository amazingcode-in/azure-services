package com.amazingcode.in.example.service;

import com.azure.ai.documentintelligence.DocumentIntelligenceClient;
import com.azure.ai.documentintelligence.models.*;
import com.azure.core.util.polling.SyncPoller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AzureDocumentIntellijenceService {

    private final DocumentIntelligenceClient client;

    public AzureDocumentIntellijenceService(DocumentIntelligenceClient client){
        this.client = client;
    }

    public Map<String, String> extractDataFromPrebuildIdDocument(byte [] document){
        String modelId = "prebuilt-idDocument";
        SyncPoller<AnalyzeOperationDetails, AnalyzeResult> analyzeIdentityDocumentPoller = client
                .beginAnalyzeDocument(modelId, new AnalyzeDocumentOptions(document));

        AnalyzeResult identityDocumentResults = analyzeIdentityDocumentPoller.getFinalResult();

        if (!identityDocumentResults.getDocuments().isEmpty()) {
            AnalyzedDocument analyzedIDDocument = identityDocumentResults.getDocuments().get(0);
            return extractFields(analyzedIDDocument);
        }
        return new HashMap<>();
    }

    private Map<String, String> extractFields(AnalyzedDocument analyzedIDDocument) {
        Map<String, DocumentField> fields = analyzedIDDocument.getFields();
        Map<String, String> data = new HashMap<>();

        for (Map.Entry<String, DocumentField> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            DocumentField field = entry.getValue();

            if (field != null && field.getContent() != null) {
                data.put(fieldName, field.getContent());
            }
        }

        return data;
    }
}
