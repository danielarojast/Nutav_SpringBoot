package com.riwi.nutav.infraestructure.abstract_service;





import com.riwi.nutav.api.dto.request.ReservationRequest;
import com.riwi.nutav.api.dto.response.ReservationResp;

import java.util.List;

public interface IReservationService 
    extends CrudService<ReservationRequest, ReservationResp, Long>{
    
        public final String FIELD_BY_SORT = "dateTime";
        public List<ReservationResp> getByClientId(Long id);
        public  List<ReservationResp> getByTourId(Long id);

}
