package api;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Transactional
public interface LostVehicleRepository extends JpaRepository<LostVehicle,String>{
    //put any required data access methods here

    @Query("select new api.CountVehicle(v.lostPincode, count(v.lostPincode)) from LostVehicle v group by v.lostPincode order by count(v.lostPincode) desc")
    List<CountVehicle> findcount(Pageable pageable);
}
