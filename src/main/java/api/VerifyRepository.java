package api;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface VerifyRepository extends JpaRepository<Verify,Long>{
    public Verify findByMobileNumber(long mobileNumber);
}
