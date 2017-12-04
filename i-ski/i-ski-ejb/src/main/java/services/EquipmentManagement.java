package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Equipment;

/**
 * Session Bean implementation class EquipmentManagement
 */
@Stateless
@WebService(name = "EquipmentServicePortType", portName = "EquipmentService", serviceName = "EquipmentService", targetNamespace = "http://iski.tn", endpointInterface = "services.EquipmentManagementRemote")
public class EquipmentManagement implements EquipmentManagementRemote, EquipmentManagementLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public EquipmentManagement() {
		// TODO Auto-generated constructor stub
	}

	@WebMethod
	@WebResult
	@Override
	public void addEquipment(Equipment equipment) {
		entityManager.persist(equipment);

	}

	@WebMethod
	@WebResult
	@Override
	public void updateEquipment(Equipment equipment) {
		entityManager.merge(equipment);

	}

	@WebMethod
	@WebResult
	@Override
	public void deleteEquipmentById(int id) {
		entityManager.remove(findEquipmentById(id));

	}

	@WebMethod
	@WebResult
	@Override
	public void deleteEquipment(Equipment equipment) {
		entityManager.remove(equipment);

	}

	@WebMethod
	@WebResult
	@Override
	public Equipment findEquipmentById(int id) {
		return entityManager.find(Equipment.class, id);
	}

	@WebMethod
	@WebResult
	@Override
	public List<Equipment> findAllEquipments() {
		String jpql = "SELECT e FROM Equipment e WHERE e.idUser=:User.idUser";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

}