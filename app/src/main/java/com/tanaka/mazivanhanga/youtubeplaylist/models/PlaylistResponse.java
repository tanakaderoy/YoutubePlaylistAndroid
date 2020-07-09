package com.tanaka.mazivanhanga.youtubeplaylist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class PlaylistResponse {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistResponse that = (PlaylistResponse) o;
        return Objects.equals(kind, that.kind) &&
                Objects.equals(etag, that.etag) &&
                Objects.equals(nextPageToken, that.nextPageToken) &&
                Objects.equals(pageInfo, that.pageInfo) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, etag, nextPageToken, pageInfo, items);
    }

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public class ContentDetails {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContentDetails that = (ContentDetails) o;
            return Objects.equals(itemCount, that.itemCount);
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemCount);
        }

        @SerializedName("itemCount")
        @Expose
        private Integer itemCount;

        public Integer getItemCount() {
            return itemCount;
        }

        public void setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
        }

    }


    public class Default {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Default aDefault = (Default) o;
            return Objects.equals(url, aDefault.url) &&
                    Objects.equals(width, aDefault.width) &&
                    Objects.equals(height, aDefault.height);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, width, height);
        }

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }


    public class High {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            High high = (High) o;
            return Objects.equals(url, high.url) &&
                    Objects.equals(width, high.width) &&
                    Objects.equals(height, high.height);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, width, height);
        }

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }


    public class Item {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(kind, item.kind) &&
                    Objects.equals(etag, item.etag) &&
                    Objects.equals(id, item.id) &&
                    Objects.equals(snippet, item.snippet) &&
                    Objects.equals(contentDetails, item.contentDetails);
        }

        @Override
        public int hashCode() {
            return Objects.hash(kind, etag, id, snippet, contentDetails);
        }

        @SerializedName("kind")
        @Expose
        private String kind;
        @SerializedName("etag")
        @Expose
        private String etag;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("snippet")
        @Expose
        private Snippet snippet;
        @SerializedName("contentDetails")
        @Expose
        private ContentDetails contentDetails;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public void setContentDetails(ContentDetails contentDetails) {
            this.contentDetails = contentDetails;
        }

    }

    public class Localized {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Localized localized = (Localized) o;
            return Objects.equals(title, localized.title) &&
                    Objects.equals(description, localized.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description);
        }

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    public class Medium {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Medium medium = (Medium) o;
            return Objects.equals(url, medium.url) &&
                    Objects.equals(width, medium.width) &&
                    Objects.equals(height, medium.height);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, width, height);
        }

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }


    public class PageInfo {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PageInfo pageInfo = (PageInfo) o;
            return Objects.equals(totalResults, pageInfo.totalResults) &&
                    Objects.equals(resultsPerPage, pageInfo.resultsPerPage);
        }

        @Override
        public int hashCode() {
            return Objects.hash(totalResults, resultsPerPage);
        }

        @SerializedName("totalResults")
        @Expose
        private Integer totalResults;
        @SerializedName("resultsPerPage")
        @Expose
        private Integer resultsPerPage;

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public Integer getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(Integer resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }

    }


    public class Snippet {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Snippet snippet = (Snippet) o;
            return Objects.equals(publishedAt, snippet.publishedAt) &&
                    Objects.equals(channelId, snippet.channelId) &&
                    Objects.equals(title, snippet.title) &&
                    Objects.equals(description, snippet.description) &&
                    Objects.equals(thumbnails, snippet.thumbnails) &&
                    Objects.equals(channelTitle, snippet.channelTitle) &&
                    Objects.equals(localized, snippet.localized);
        }

        @Override
        public int hashCode() {
            return Objects.hash(publishedAt, channelId, title, description, thumbnails, channelTitle, localized);
        }

        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;
        @SerializedName("channelId")
        @Expose
        private String channelId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("thumbnails")
        @Expose
        private Thumbnails thumbnails;
        @SerializedName("channelTitle")
        @Expose
        private String channelTitle;
        @SerializedName("localized")
        @Expose
        private Localized localized;

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public void setChannelTitle(String channelTitle) {
            this.channelTitle = channelTitle;
        }

        public Localized getLocalized() {
            return localized;
        }

        public void setLocalized(Localized localized) {
            this.localized = localized;
        }

    }


    public class Thumbnails {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Thumbnails that = (Thumbnails) o;
            return Objects.equals(_default, that._default) &&
                    Objects.equals(medium, that.medium) &&
                    Objects.equals(high, that.high);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_default, medium, high);
        }

        @SerializedName("default")
        @Expose
        private Default _default;
        @SerializedName("medium")
        @Expose
        private Medium medium;
        @SerializedName("high")
        @Expose
        private High high;

        public Default getDefault() {
            return _default;
        }

        public void setDefault(Default _default) {
            this._default = _default;
        }

        public Medium getMedium() {
            return medium;
        }

        public void setMedium(Medium medium) {
            this.medium = medium;
        }

        public High getHigh() {
            return high;
        }

        public void setHigh(High high) {
            this.high = high;
        }

    }
}