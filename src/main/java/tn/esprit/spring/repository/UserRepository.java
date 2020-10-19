package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);
	
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
  

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
   
    List<User> findByRoles_Id(Long id);
    

    
//    @Query("select c from 	Command c join c.user e where e.id=:employeId")
//    public List<Command> getSalaireByEmployeIdJPQL(@Param("employeId")Long userid);

    
//	@Query("select DISTINCT m from Mission m join m.timesheets t join t.employe e where e.id=:employeId")
//	public List<Mission> findAllMissionByEmployeJPQL(@Param("employeId")int employeId);
//    
    
 
    
    @Query(value = "select sum(datediff(:d1,:d)*(u.salary/30)) from users u " ,nativeQuery = true)
    public Float  getsalaireparnmbrdejours(@Param("d") Date d,@Param("d1") Date d1 );
    
    
    @Query(value = "select sum(f.price) from facture_supplier f where f.date BETWEEN :d AND :d1 " ,nativeQuery = true)
    public Float  getAlldeponse(@Param("d") Date d,@Param("d1") Date d1 );
    
    @Query(value = "select *  from users u  inner join reporting r on u.reporting_id = r.id where r.codeparrinage= :CODE  " ,nativeQuery = true)
    public User  getuserbycodeparrinage(@Param("CODE") String CODE);
    
    @Query(value = "select r.name  from roles r  inner join user_roles ur on r.id = ur.role_id inner join users u on ur.user_id = u.id where u.id = :userid  " ,nativeQuery = true)
    public String  getuserrole(@Param("userid") Long userid);
    
    @Query(value = "select count(u.users_id)  from users_users u   where u.users_id = :userid  " ,nativeQuery = true)
    public int  nbrsignal(@Param("userid") Long userid);
    
    
//    stock ? /// debbou y fih stocks x -20 //idstock 1le 2021
//    		
//    		///debbou y fih stock x 200 bdate jdida id stock 1000
    		
    @Query(value = "select u.user_id  from users_users u   where u.users_id = :userid  " ,nativeQuery = true)
    public List<Long>  lesId(@Param("userid") Long userid);	
    		
    
    
    
    
	@Query(value = " select  users.username,count(c.id) from command c "
			+ " inner join users  on c.user_id = users.id "
			+ " inner join reporting r on r.id = users.reporting_id "
			+ "  where r.usrnameparrain=:usernameconnect group by users.username ", nativeQuery = true)
	public List<List<String>> nbrgaindesuser(@Param("usernameconnect") String usernameconnect);

	
	
    
}
