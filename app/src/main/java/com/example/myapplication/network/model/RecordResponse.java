package com.example.myapplication.network.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecordResponse {
    private Record record;
    private Metadata metadata;

    public Record getRecord() {
        return record;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public static class Record {
        private Result result;

        public Result getResult() {
            return result;
        }
    }





    public static class Metadata {
        private String id;
        private boolean privateField;
        @SerializedName("createdAt")
        private String createdAt;

        // Getters for the fields
        // Add setters if needed
    }
}
