package com.jayblog.weisite.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayblog.weisite.converter.MapToJsonConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
@Entity
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonProperty("event_type")
    private String eventType;

    @NotNull
    @JsonProperty("event_time")
    private String eventTime;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_role")
    private String userRole;

    @JsonProperty("page_url")
    private String pageUrl;

    @JsonProperty("page_title")
    private String pageTitle;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("operating_system")
    private String operatingSystem;

    private String browser;

    @JsonProperty("event_params")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> eventParams;

    private String source;
}