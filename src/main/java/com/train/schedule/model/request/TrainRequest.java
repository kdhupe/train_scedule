package com.train.schedule.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class TrainRequest {
    private Long trainId;
    private String trainNumber;

    private LinkedHashSet<String> stations;
    private String trainName;
}
