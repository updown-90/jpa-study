package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("updown");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 비영속 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("HelloJPA");

			// 영속 상태 (entityManager)에 올라가있음
			em.persist(member);

			// 준영속 상태 (영속성 컨텍스트에서 분리)
			em.detach(member);

			// 객체를 삭제한 상태
			em.remove(member);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
