package api;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface FoundVehicleRepository extends CrudRepository<FoundVehicle,String>{

}
