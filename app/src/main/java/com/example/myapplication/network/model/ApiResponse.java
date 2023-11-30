package com.example.myapplication.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse  {
    @SerializedName("record")
    private Record record;

    @SerializedName("metadata")
    private Metadata metadata;

    public Record getRecord() {
        return record;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public static class Record {
        @SerializedName("result")
        private Result result;

        public Result getResult() {
            return result;
        }

        public static class Result {
            @SerializedName("index")
            private List<IndexItem> index;

            @SerializedName("collections")
            private Collections collections;

            public List<IndexItem> getIndex() {
                return index;
            }

            public Collections getCollections() {
                return collections;
            }
        }

        public static class IndexItem {
            @SerializedName("downloadid")
            private int downloadId;

            @SerializedName("cd_downloads")
            private int cdDownloads;

            @SerializedName("id")
            private int id;

            @SerializedName("title")
            private String title;

            @SerializedName("status")
            private int status;

            @SerializedName("release_date")
            private String releaseDate;

            @SerializedName("authorid")
            private int authorId;

            @SerializedName("video_count")
            private int videoCount;

            @SerializedName("style_tags")
            private List<String> styleTags;

            @SerializedName("skill_tags")
            private List<String> skillTags;

            @SerializedName("series_tags")
            private List<String> seriesTags;

            @SerializedName("curriculum_tags")
            private List<String> curriculumTags;

            @SerializedName("educator")
            private String educator;

            @SerializedName("owned")
            private int owned;

            @SerializedName("sale")
            private int sale;

            @SerializedName("purchase_order")
            private Object purchaseOrder; // Change Object to the appropriate type if needed

            @SerializedName("watched")
            private int watched;

            @SerializedName("progress_tracking")
            private int progressTracking;

            public int getDownloadId() {
                return downloadId;
            }

            public int getCdDownloads() {
                return cdDownloads;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public int getStatus() {
                return status;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public int getAuthorId() {
                return authorId;
            }

            public int getVideoCount() {
                return videoCount;
            }

            public List<String> getStyleTags() {
                return styleTags;
            }

            public List<String> getSkillTags() {
                return skillTags;
            }

            public List<String> getSeriesTags() {
                return seriesTags;
            }

            public List<String> getCurriculumTags() {
                return curriculumTags;
            }

            public String getEducator() {
                return educator;
            }

            public int getOwned() {
                return owned;
            }

            public int getSale() {
                return sale;
            }

            public Object getPurchaseOrder() {
                return purchaseOrder;
            }

            public int getWatched() {
                return watched;
            }

            public int getProgressTracking() {
                return progressTracking;
            }
        }

        public static class Collections {
            @SerializedName("smart")
            private List<SmartCollection> smart;

            @SerializedName("user")
            private List<UserCollection> user;

            @SerializedName("curated")
            private List<Object> curated; // Change Object to the appropriate type if needed

            public List<SmartCollection> getSmart() {
                return smart;
            }

            public List<UserCollection> getUser() {
                return user;
            }

            public List<Object> getCurated() {
                return curated;
            }
        }

        public static class SmartCollection {
            @SerializedName("id")
            private String id;

            @SerializedName("label")
            private String label;

            @SerializedName("smart")
            private String smart;

            @SerializedName("courses")
            private List<Integer> courses;

            @SerializedName("is_default")
            private int isDefault;

            @SerializedName("is_archive")
            private int isArchive;

            @SerializedName("description")
            private String description;

            public String getId() {
                return id;
            }

            public String getLabel() {
                return label;
            }

            public String getSmart() {
                return smart;
            }

            public List<Integer> getCourses() {
                return courses;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public int getIsArchive() {
                return isArchive;
            }

            public String getDescription() {
                return description;
            }
        }

        public static class UserCollection {
            @SerializedName("id")
            private int id;

            @SerializedName("label")
            private String label;

            @SerializedName("is_default")
            private int isDefault;

            @SerializedName("is_archive")
            private int isArchive;

            @SerializedName("description")
            private String description;

            @SerializedName("courses")
            private List<Integer> courses;

            public int getId() {
                return id;
            }

            public String getLabel() {
                return label;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public int getIsArchive() {
                return isArchive;
            }

            public String getDescription() {
                return description;
            }

            public List<Integer> getCourses() {
                return courses;
            }
        }
    }

    public static class Metadata {
        @SerializedName("id")
        private String id;

        @SerializedName("private")
        private boolean isPrivate;

        @SerializedName("createdAt")
        private String createdAt;

        public String getId() {
            return id;
        }

        public boolean isPrivate() {
            return isPrivate;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }
}

