package com.train.schedule.controller;


import com.train.schedule.model.request.DeleteTrainRequest;
import com.train.schedule.model.request.TrainRequest;
import com.train.schedule.model.request.TrainSearchRequest;
import com.train.schedule.model.response.EntityResponse;
import com.train.schedule.service.ITrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TrainController {

    private ITrainService iTrainService;

    @PostMapping("/saveOrUpdateTrain")
    public ResponseEntity<?> saveOrUpdate(@RequestBody TrainRequest trainRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(iTrainService.saveOrUpdateTrainDetails(trainRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getTrainByTrainNumber")
    public ResponseEntity<?> getTrainDetailsByTrainNumber(@RequestParam String trainNumber) {
        return new ResponseEntity<>(new EntityResponse(iTrainService.getTrainByTrainNumnber(trainNumber), 0), HttpStatus.OK);
    }

    @GetMapping("/searchTrainBySourceAndDestination")
    public ResponseEntity<?> searchTrainBySourceAndDestination(@RequestBody TrainSearchRequest request) {
        return new ResponseEntity<>(new EntityResponse(iTrainService.searchTrainsBySourceAndDestenation(request), 0), HttpStatus.OK);
    }


    @DeleteMapping("/deleteByTrainNumber")
    public ResponseEntity<?>deleteByTrainNumber(@RequestBody DeleteTrainRequest deleteTrainRequest){
        return new ResponseEntity<>(new EntityResponse(iTrainService.deleteByTrainNumber(Optional.ofNullable(deleteTrainRequest.getTrainNumber()).orElse("0")),0),HttpStatus.OK);
    }

}
