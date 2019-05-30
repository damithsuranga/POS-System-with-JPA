package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.DAOTypes;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.JPAUtil;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            customerDAO.setEntityManager(em);
            List<CustomerDTO> customerDTOStream = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
            em.getTransaction().commit();
            em.close();
            return customerDTOStream;

    }

    @Override
    public void saveCustomer(CustomerDTO dto) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            customerDAO.setEntityManager(em);
            customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
            em.getTransaction().commit();
            em.close();
    }

    @Override
    public void updateCustomer(CustomerDTO dto) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            customerDAO.setEntityManager(em);
            customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
            em.getTransaction().commit();
           em.close();
    }

    @Override
    public void removeCustomer(String id) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
                em.getTransaction().begin();
                customerDAO.setEntityManager(em);
                customerDAO.delete(id);
                em.getTransaction().commit();
                em.close();
    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
       EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
                em.getTransaction().begin();
                customerDAO.setEntityManager(em);
            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            em.getTransaction().commit();
        em.close();
            return customerDTO;


    }

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    //    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }


}
