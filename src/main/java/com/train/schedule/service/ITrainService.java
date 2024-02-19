package com.train.schedule.service;

import com.train.schedule.model.request.TrainRequest;
import com.train.schedule.model.request.TrainSearchRequest;

public interface ITrainService {
    Object saveOrUpdateTrainDetails(TrainRequest trainRequest);

    Object getTrainByTrainNumnber(String trainNumber);

    Object searchTrainsBySourceAndDestenation(TrainSearchRequest request);

    Object deleteByTrainNumber(String trainNumber);
}
