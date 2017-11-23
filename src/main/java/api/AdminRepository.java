package api;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AdminRepository extends JpaRepository<Admin,Long>{
    public Admin findByMobileNumber(long mobileNumber);
}
